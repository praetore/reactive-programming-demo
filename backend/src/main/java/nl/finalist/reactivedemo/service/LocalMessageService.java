package nl.finalist.reactivedemo.service;

import nl.finalist.reactivedemo.pojo.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

//@Service
public class LocalMessageService implements MessageService {
    private final List<Message> messages = new ArrayList<>();
    private final Sinks.Many<Message> messageFlux = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public Flux<Message> getMessages() {
        return Flux.merge(Flux.fromIterable(messages), messageFlux.asFlux());
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
        messageFlux.tryEmitNext(message);
    }
}
