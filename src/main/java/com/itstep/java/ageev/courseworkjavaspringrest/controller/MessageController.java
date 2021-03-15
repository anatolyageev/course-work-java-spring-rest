package com.itstep.java.ageev.courseworkjavaspringrest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.itstep.java.ageev.courseworkjavaspringrest.domain.Message;
import com.itstep.java.ageev.courseworkjavaspringrest.domain.Views;
import com.itstep.java.ageev.courseworkjavaspringrest.dto.EventType;
import com.itstep.java.ageev.courseworkjavaspringrest.dto.ObjectType;
import com.itstep.java.ageev.courseworkjavaspringrest.exeption.MessageNotFoundException;
import com.itstep.java.ageev.courseworkjavaspringrest.repository.MessageRepository;
import com.itstep.java.ageev.courseworkjavaspringrest.util.WsSender;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepository messageRepository;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepository messageRepository, WsSender wsSender) {
        this.messageRepository = messageRepository;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE,Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> getMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Long id) {
        return messageRepository.findById(id)
                .orElseThrow(MessageNotFoundException::new);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());
        Message updatedMessage = messageRepository.save(message);
        wsSender.accept(EventType.CREATE,updatedMessage);
        return updatedMessage;
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updatedMessage = messageRepository.save(messageFromDb);
        wsSender.accept(EventType.UPDATE,updatedMessage);
        return updatedMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
        wsSender.accept(EventType.REMOVE,message);
    }
}
