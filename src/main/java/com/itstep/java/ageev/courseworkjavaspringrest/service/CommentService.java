package com.itstep.java.ageev.courseworkjavaspringrest.service;

import com.itstep.java.ageev.courseworkjavaspringrest.domain.Comment;
import com.itstep.java.ageev.courseworkjavaspringrest.domain.User;
import com.itstep.java.ageev.courseworkjavaspringrest.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        commentRepository.save(comment);
        return comment;
    }
}
