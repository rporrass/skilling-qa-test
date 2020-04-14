package interview.skilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExercisesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExercisesApplication.class, args);
    }

}
