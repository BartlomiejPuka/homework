package com.example.homework.routing;

import com.example.homework.routing.pojo.RouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/routing/{origin}/{destination}")
    public RouteResponse findRoute(
            @PathVariable("origin") String origin,
            @PathVariable("destination") String destination) throws RouteNotFoundException {
        return new RouteResponse(routeService.findRoute(origin, destination));
    }
}
