package com.JournelApp.JournelApp.services;
import com.JournelApp.JournelApp.Entity.User;
import com.JournelApp.JournelApp.repositeries.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    public UserRepository userRepository;


    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(User user){
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

    public Optional<User> makeAdmin(User user) {
        User newUser= userRepository.findByUsername(user.getUsername());
        newUser.setRoles(List.of("USER","ADMIN"));
        userRepository.save(newUser);
        return Optional.of(newUser);
    }
}