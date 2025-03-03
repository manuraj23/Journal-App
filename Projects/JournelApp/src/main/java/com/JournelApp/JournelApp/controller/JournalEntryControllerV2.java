package com.JournelApp.JournelApp.controller;
import com.JournelApp.JournelApp.Entity.JournalEntry;
import com.JournelApp.JournelApp.Entity.User;
import com.JournelApp.JournelApp.services.JournalEntryService;
import com.JournelApp.JournelApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    public JournalEntryService journalEntryService;

    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournelEntry() {
        List<JournalEntry> entries = journalEntryService.getAll();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntry>> getAllJournelEntryOfUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<JournalEntry> entries = user.getJournalEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(myEntry,username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournelEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        return journalEntryService.getJournalEntryById(myId).orElse(null);
    }

    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<Void> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String username) {
        try {
            journalEntryService.deleteJournalEntryById(myId,username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId myId,
                                                               @RequestBody JournalEntry newEntry,
                                                               @PathVariable String username) {
        Optional<JournalEntry> oldEntryOpt = journalEntryService.getJournalEntryById(myId);
        if (oldEntryOpt.isPresent()) {
            JournalEntry oldEntry = oldEntryOpt.get();
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());

            journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
