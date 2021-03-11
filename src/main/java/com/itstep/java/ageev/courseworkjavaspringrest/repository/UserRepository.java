package com.itstep.java.ageev.courseworkjavaspringrest.repository;

import com.itstep.java.ageev.courseworkjavaspringrest.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
