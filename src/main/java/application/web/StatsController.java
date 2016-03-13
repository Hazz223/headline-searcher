package application.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Harry on 13/03/2016.
 */
@RestController
public class StatsController {

    @RequestMapping("/articles/stats")
    public void getStats(){

    }

}
