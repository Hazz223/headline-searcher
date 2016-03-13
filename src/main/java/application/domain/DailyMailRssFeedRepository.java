package application.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by Harry on 13/03/2016.
 */
public interface DailyMailRssFeedRepository extends MongoRepository<DailyMailRssItem, String> {

    Optional<DailyMailRssItem> findById(String id);

    Page<DailyMailRssItem> findAll(Pageable pageable);

    Page<DailyMailRssItem> findByTitleContainingIgnoringCaseOrDescriptionContainingIgnoringCase(String term, Pageable pageable);

}
