package com.JournelApp.JournelApp.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;

}