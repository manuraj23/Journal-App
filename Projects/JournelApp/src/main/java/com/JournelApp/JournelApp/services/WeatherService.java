package com.JournelApp.JournelApp.services;


import com.JournelApp.JournelApp.ApiResponse.WeatherResponse;
import com.JournelApp.JournelApp.Cache.AppCache;
import com.JournelApp.JournelApp.Constants.PlaceHolder;
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

    @Autowired
    private AppCache appCache;

    @Autowired
    public RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI= appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolder.API_KEY,apikey).replace(PlaceHolder.CITY,city);
        ResponseEntity<WeatherResponse>response= restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        return response.getBody();
    }
}
