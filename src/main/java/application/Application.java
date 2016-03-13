package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Harry on 29/12/2015.
 */
@SpringBootApplication
@EnableWebMvc
public class Application {

    //region Public Methods

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    //endregion

}
