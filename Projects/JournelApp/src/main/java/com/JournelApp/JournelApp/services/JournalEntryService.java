package com.JournelApp.JournelApp.services;

import com.JournelApp.JournelApp.Entity.JournalEntry;
import com.JournelApp.JournelApp.Entity.User;
import com.JournelApp.JournelApp.repositeries.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    public JournalEntryRepository journalEntryRepository;

    @Autowired
    public UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String username){
        try {
            User user = userService.findByUsername(username);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }
        catch (Exception e) {
//            System.out.println(e);
            throw new RuntimeException("Error saving journal entry", e);
        }

    }

    public void saveJournalEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteJournalEntryById(ObjectId id, String username){
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
