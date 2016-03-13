package application.web;

import application.crawlers.CrawlerStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Harry on 13/03/2016.
 */
@RestController
public class WebCrawlerController {

    @Autowired
    private CrawlerStartService crawlerStartService;

    @RequestMapping("/crawlers/run")
    public void startAllCrawlers(){

        try {
            this.crawlerStartService.startCrawler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
