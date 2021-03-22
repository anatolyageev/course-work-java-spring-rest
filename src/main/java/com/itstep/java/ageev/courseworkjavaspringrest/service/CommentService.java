package com.itstep.java.ageev.courseworkjavaspringrest.service;

import com.itstep.java.ageev.courseworkjavaspringrest.domain.Comment;
import com.itstep.java.ageev.courseworkjavaspringrest.domain.Message;
import com.itstep.java.ageev.courseworkjavaspringrest.domain.User;
import com.itstep.java.ageev.courseworkjavaspringrest.domain.Views;
import com.itstep.java.ageev.courseworkjavaspringrest.dto.EventType;
import com.itstep.java.ageev.courseworkjavaspringrest.dto.ObjectType;
import com.itstep.java.ageev.courseworkjavaspringrest.repository.CommentRepository;
import com.itstep.java.ageev.courseworkjavaspringrest.util.WsSender;
import java.util.function.BiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepository commentRepository, WsSender wsSender) {
        this.commentRepository = commentRepository;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        commentRepository.save(comment);
        return comment;
    }
}
