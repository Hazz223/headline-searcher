package application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Harry on 13/03/2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {

        return "home";
    }

}
