package interview.skilling;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Exercise1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void request_to_status_endpoint_should_return_startTime_and_status_UP() throws Exception {
        this.mockMvc.perform(get("/status")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.startTime", notNullValue()))
                .andExpect(jsonPath("$.status", is("UP")));

    }

    @ParameterizedTest(name = ParameterizedTest.DISPLAY_NAME_PLACEHOLDER + ": {0}")
    @ValueSource(strings = {"some text to echo", "{\"key\": \"value\"}"})
    public void request_to_echo_endpoint_should_return_same_payload_as_in(String payload) throws Exception {
        this.mockMvc.perform(post("/echo").content(payload)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(payload));

    }

}
