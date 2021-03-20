package com.itstep.java.ageev.courseworkjavaspringrest.repository;

import com.itstep.java.ageev.courseworkjavaspringrest.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
