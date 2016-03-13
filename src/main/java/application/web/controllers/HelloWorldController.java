package application.web.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Harry on 29/12/2015.
 */
@Controller
public class HelloWorldController {

    //region Public Methods

    @RequestMapping("/")
    public String home() {

        return "home";
    }

    //endregion

    //region Private Methods

    //endregion

}
