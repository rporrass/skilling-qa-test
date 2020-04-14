package interview.skilling;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Builder
@Getter
@RequiredArgsConstructor
public class Status {
    private final Instant startTime;
    private final StatusValue status;
}
