package com.JournelApp.JournelApp.Schedular;

import com.JournelApp.JournelApp.Cache.AppCache;
import com.JournelApp.JournelApp.Entity.JournalEntry;
import com.JournelApp.JournelApp.Entity.User;
import com.JournelApp.JournelApp.repositeries.UserRepositoryImpl;
import com.JournelApp.JournelApp.services.EmailService;
import com.JournelApp.JournelApp.services.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSchedular {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    //    @Scheduled(fixedRate = 60000)
    @Scheduled(cron = "0 0 9 * * SUN")


    public void fetchUserAndSendSAMail(){
        List<User> users = userRepositoryImpl.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String>filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String entry= String.join(" ",filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment Analysis for last 7 days", "Your sentiment is: "+sentiment);

        }
    }

    @Scheduled(cron = "0 0 9 * * SUN")
    public void clearCache() {
        appCache.init();
    }
}
