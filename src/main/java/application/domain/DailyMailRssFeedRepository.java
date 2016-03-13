package application.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Harry on 13/03/2016.
 */
public interface DailyMailRssFeedRepository extends MongoRepository<DailyMailRssItem, String> {

}
