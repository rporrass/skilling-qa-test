package interview.skilling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class Exercise1Controller {
    private final Instant startTime = Instant.now();

    @GetMapping(path = "/status", produces = APPLICATION_JSON_VALUE)
    public Status getStatus() {
        return Status.builder()
                .startTime(startTime)
                .status(StatusValue.UP)
                .build();
    }

    @PostMapping("/echo")
    public String doEcho(@RequestBody String payload) {
        return payload;
    }
}
