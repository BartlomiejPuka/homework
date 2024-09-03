package com.example.homework;


import com.example.homework.routing.pojo.RouteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testExistingRoute() {
        ResponseEntity<RouteResponse> response = restTemplate.getForEntity("/routing/CZE/ITA", RouteResponse.class);
        RouteResponse routeResponse = response.getBody();

        assertThat(response.getStatusCode().value(), equalTo(HttpStatus.OK.value()));
        assertThat(routeResponse, notNullValue());
        assertThat(routeResponse.route(), notNullValue());
        assertThat(routeResponse.route(), equalTo(List.of("CZE", "AUT", "ITA")));
    }

    @Test
    public void testNotExistingRoute() {
        ResponseEntity<RouteResponse> response = restTemplate.getForEntity("/routing/ABW/ITA", RouteResponse.class);

        assertThat(response.getStatusCode().value(), equalTo(HttpStatus.NOT_FOUND.value()));
    }
}
