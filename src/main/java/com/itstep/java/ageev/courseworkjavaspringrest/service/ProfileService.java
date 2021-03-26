package com.itstep.java.ageev.courseworkjavaspringrest.service;

import com.itstep.java.ageev.courseworkjavaspringrest.domain.User;
import com.itstep.java.ageev.courseworkjavaspringrest.repository.UserRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final UserRepository userRepository;

    @Autowired
    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User changeSubscription(User channel, User subscriber) {
        Set<User> subscribers = channel.getSubscribers();

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            subscribers.add(subscriber);
        }

        return userRepository.save(channel);
    }
}
