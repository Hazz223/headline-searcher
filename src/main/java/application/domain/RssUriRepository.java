package application.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RssUriRepository extends MongoRepository<RssUri, String>{

    Page<RssUri> findAll(Pageable pageable);

}
