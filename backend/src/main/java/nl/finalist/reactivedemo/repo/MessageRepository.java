package nl.finalist.reactivedemo.repo;

import nl.finalist.reactivedemo.pojo.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
