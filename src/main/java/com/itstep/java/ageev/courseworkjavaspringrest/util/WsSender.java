package com.itstep.java.ageev.courseworkjavaspringrest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.itstep.java.ageev.courseworkjavaspringrest.dto.EventType;
import com.itstep.java.ageev.courseworkjavaspringrest.dto.ObjectType;
import com.itstep.java.ageev.courseworkjavaspringrest.dto.WsEventDto;
import java.util.function.BiConsumer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WsSender {
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;


    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload) -> {
            String value = null;
            try {
                value = writer.writeValueAsString(payload);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

            template.convertAndSend("/topic/activity",
                    new WsEventDto(objectType, eventType, value));
        };
    }
}
