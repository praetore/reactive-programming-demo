package nl.finalist.reactivedemo.service;

import lombok.RequiredArgsConstructor;
import nl.finalist.reactivedemo.pojo.Message;
import nl.finalist.reactivedemo.repo.MessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class DatabaseMessageService implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public Flux<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.save(message).subscribe();
    }

}
