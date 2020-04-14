package interview.skilling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToIgnoreCase;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Exercise1ClientTest {
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Autowired
    private Exercise1Client exercise1Client;

    @Test
    public void request_status_will_return_startTime_and_UP_status() throws Exception {
        Status expected = Status.builder()
                .startTime(Instant.now())
                .status(StatusValue.UP)
                .build();

        stubFor(get(urlEqualTo("/status"))
                .withHeader("Accept", equalToIgnoreCase(APPLICATION_JSON_VALUE))
                .willReturn(aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(expected))
                )
        );

        assertThat(exercise1Client.getStatus()).isEqualToComparingFieldByField(expected);
    }

    @ParameterizedTest(name = ParameterizedTest.DISPLAY_NAME_PLACEHOLDER + ": {0}")
    @ValueSource(strings = {"some text to echo", "{\"key\": \"value\"}"})
    public void request_echo_will_return_same_payload_as_in(String payload) throws Exception {
        stubFor(post(urlEqualTo("/echo"))
                .withRequestBody(equalTo(payload))
                .willReturn(aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(payload)
                )
        );

        assertThat(exercise1Client.doEcho(payload)).isEqualTo(payload);
    }

    @Configuration
    @EnableFeignClients(clients = {Exercise1Client.class})
    @ImportAutoConfiguration({
            HttpMessageConvertersAutoConfiguration.class,
            RibbonAutoConfiguration.class,
            FeignRibbonClientAutoConfiguration.class,
            FeignAutoConfiguration.class})
    static class ContextConfiguration {

        @Autowired
        private Environment env;

        @Bean
        ServletWebServerFactory servletWebServerFactory() {
            return new TomcatServletWebServerFactory();
        }

        @Bean
        public ServerList<Server> ribbonServerList() {
            return new StaticServerList<>(new Server("localhost", Integer.valueOf(this.env.getProperty("wiremock.server.port"))));
        }

    }

}
