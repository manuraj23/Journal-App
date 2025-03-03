package com.JournelApp.JournelApp.services;
import com.JournelApp.JournelApp.Entity.User;
import com.JournelApp.JournelApp.repositeries.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> getJournalEntryById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteJournalEntryById(ObjectId id){
        userRepository.deleteById(id);
    }
}
