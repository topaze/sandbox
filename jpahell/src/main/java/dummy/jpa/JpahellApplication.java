package dummy.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("dummy.jpa.repo")
@ImportResource( { "classpath:spring/applicationContext.xml" } )
public class JpahellApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpahellApplication.class, args);
    }
}
