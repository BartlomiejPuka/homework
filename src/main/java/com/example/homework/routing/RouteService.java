package com.example.homework.routing;

import java.util.List;

public interface RouteService {

    List<String> findRoute(String origin, String destination) throws RouteNotFoundException;

}
