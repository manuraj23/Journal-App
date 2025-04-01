package com.JournelApp.JournelApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String apikey="91294cd1385507c7857a5f51ddd14bf5";

    private static final String api="http://api,weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public String getWeather(String city){
        String finalAPI= api.replace("API_KEY",apikey).replace("CITY",city);

    }
}
