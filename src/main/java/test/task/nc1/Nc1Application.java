package test.task.nc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Nc1Application {

    public static void main(String[] args) {
        SpringApplication.run(Nc1Application.class, args);
    }

}
