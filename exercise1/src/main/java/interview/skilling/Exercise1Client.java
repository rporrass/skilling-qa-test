package interview.skilling;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient("exercise1")
public interface Exercise1Client {
    @RequestMapping(method = GET, value = "/status", produces = APPLICATION_JSON_VALUE)
    Status getStatus();

    @RequestMapping(method = POST, value = "/echo")
    String doEcho(String payload);
}
