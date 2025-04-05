package com.JournelApp.JournelApp.Schedular;

import com.JournelApp.JournelApp.Cache.AppCache;
import com.JournelApp.JournelApp.Entity.JournalEntry;
import com.JournelApp.JournelApp.Entity.User;
import com.JournelApp.JournelApp.Enum.Sentiment;
import com.JournelApp.JournelApp.repositeries.UserRepositoryImpl;
import com.JournelApp.JournelApp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedular {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private EmailService emailService;


    @Autowired
    private AppCache appCache;

    //    @Scheduled(fixedRate = 60000)
    //for sat at 23:35
    @Scheduled(cron = "0 34 23 * * SAT")
//    @Scheduled(cron = "0 0 9 * * SUN")


    public void fetchUserAndSendSAMail(){
        List<User> users = userRepositoryImpl.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).toList();
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment Analysis", "Your most frequent sentiment in the last week is: " + mostFrequentSentiment);
            }

        }
    }

    @Scheduled(cron = "0 0 9 * * SUN")
    public void clearCache() {
        appCache.init();
    }
}
