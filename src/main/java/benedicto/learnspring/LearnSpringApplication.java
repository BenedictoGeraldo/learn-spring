package benedicto.learnspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LearnSpringApplication {
    public static void main (String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);

        System.out.println("=====================================");
        System.out.println("Program sedang berjalan");
        System.out.println("=====================================");

    }

}