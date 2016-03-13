package application.services;

import application.domain.RssUri;
import application.domain.RssUriRepository;
import application.services.dailymail.DailyMailCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 13/03/2016.
 */
@Service
public class CrawlerStartService {

    private DailyMailCrawler dailyMailCrawler;

    @Autowired
    public CrawlerStartService(DailyMailCrawler dailyMailCrawler) {
        this.dailyMailCrawler = dailyMailCrawler;
    }

    @PostConstruct
    public void startCrawler() throws IOException {

        this.dailyMailCrawler.StartCrawl();

    }
}
