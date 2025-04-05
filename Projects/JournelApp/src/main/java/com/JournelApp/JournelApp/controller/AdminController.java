package com.JournelApp.JournelApp.controller;


import com.JournelApp.JournelApp.Cache.AppCache;
import com.JournelApp.JournelApp.Entity.User;
import com.JournelApp.JournelApp.services.EmailService;
import com.JournelApp.JournelApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;
    @Autowired
    private EmailService emailService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(){
        List<User>all=userService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    //make admin to user present in database
    @PostMapping("/create-admin-user/{username}")
    public ResponseEntity<?> makeAdmin(@PathVariable String username){
        User user=userService.findByUsername(username);
        if(user!=null){
            Optional<User> newAdmin = userService.makeAdmin(user);
            return new ResponseEntity<>(newAdmin,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("clearCache")
    public void clearAppCache(){
        appCache.init();
    }

    @PostMapping("/sendMail")
    public void sendMail(@RequestParam String to,
                         @RequestParam String subject,
                         @RequestParam String body) {
        emailService.sendEmail(to,subject,body);
    }
}
