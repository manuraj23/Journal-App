package com.JournelApp.JournelApp.services;


import com.JournelApp.JournelApp.ApiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey;  //="91294cd1385507c7857a5f51ddd14bf5";

    private static final String api="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI= api.replace("API_KEY",apikey).replace("CITY",city);
        ResponseEntity<WeatherResponse>response= restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        return response.getBody();
    }
}
