//package com.JournelApp.JournelApp.controller;
//
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/journel")
//public class JournalEntryController {
////    private Map<Long, JournelEntry> journelEnteries=new HashMap<>();
//
////    @GetMapping
////    public List<JournelEntry>getAll(){
////        return new ArrayList<>(journelEnteries.values());
////    }
////    @PostMapping
////    public boolean createEntry(@RequestBody JournelEntry myEntry){
////        journelEnteries.put(myEntry.getId(),myEntry);
////        return true;
////    }
////    @GetMapping("/id/{myId}")
////    public JournelEntry getJournerEntryById(@PathVariable Long myId){
////        return journelEnteries.get(myId);
////    }
////
////    @DeleteMapping("/id/{myId}")
////    public JournelEntry deleteJournerEntryById(@PathVariable Long myId){
////        return journelEnteries.remove(myId);
////    }
////
////    @PutMapping("/id/{id}")
////    public JournelEntry updateJournerEntryById(@PathVariable Long id, @RequestBody JournelEntry myEntry){
////        return journelEnteries.put(id,myEntry);
////    }
//
//}
