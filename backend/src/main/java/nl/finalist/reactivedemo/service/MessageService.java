package nl.finalist.reactivedemo.service;

import nl.finalist.reactivedemo.pojo.Message;
import reactor.core.publisher.Flux;

public interface MessageService {
    Flux<Message> getMessages();

    void addMessage(Message message);
}
