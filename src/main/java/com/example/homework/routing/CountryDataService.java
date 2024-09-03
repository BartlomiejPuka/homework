package com.example.homework.routing;

import com.example.homework.routing.pojo.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CountryDataService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final String COUNTRIES_URL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";
    public static Map<String, Country> countriesMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(COUNTRIES_URL, String.class);
        String jsonResponse = response.getBody();
        List<Country> countries = objectMapper.readValue(jsonResponse, new TypeReference<>() {});

        if (countries == null) {
            return;
        }
        for (Country country : countries) {
            countriesMap.put(country.getCca3(), country);
        }
        System.out.println("countriesMap " + countriesMap);
    }
}
