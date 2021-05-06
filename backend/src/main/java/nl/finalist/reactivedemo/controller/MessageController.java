package nl.finalist.reactivedemo.controller;

import lombok.RequiredArgsConstructor;
import nl.finalist.reactivedemo.pojo.Message;
import nl.finalist.reactivedemo.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    @GetMapping(path = "/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> getMessages() {
        return messageService.getMessages();
    }

    @PostMapping(path = "/messages", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
    }

}
