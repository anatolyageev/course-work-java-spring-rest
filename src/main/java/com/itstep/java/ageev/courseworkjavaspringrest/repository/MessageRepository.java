package com.itstep.java.ageev.courseworkjavaspringrest.repository;

import com.itstep.java.ageev.courseworkjavaspringrest.domain.Message;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @EntityGraph(attributePaths = { "comments" })
    List<Message> findAll();
}
