package com.itstep.java.ageev.courseworkjavaspringrest.repository;

import com.itstep.java.ageev.courseworkjavaspringrest.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    @EntityGraph(attributePaths = { "subscriptions", "subscribers" })
    Optional<User> findById(String s);
}
