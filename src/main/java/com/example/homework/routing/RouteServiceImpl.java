package com.example.homework.routing;

import com.example.homework.routing.pojo.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RouteServiceImpl implements RouteService {

    private final RestTemplate restTemplate;
    private final CountryDataService countryDataService;

    @Override
    public List<String> findRoute(String origin, String destination) throws RouteNotFoundException {

        if (origin.equals(destination)) {
            return Collections.singletonList(origin);
        }

        return Optional.ofNullable(calculateRoute(origin, destination))
                .orElseThrow(RouteNotFoundException::new);
    }

    private List<String> calculateRoute(String origin, String destination) {
        Map<String, Country> countriesMap = CountryDataService.countriesMap;
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(Collections.singletonList(origin));
        visited.add(origin);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastCountry = path.get(path.size() - 1);
            Country country = countriesMap.get(lastCountry);

            if (country != null) {
                for (String neighbor : country.getBorders()) {
                    if (!visited.contains(neighbor)) {
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        if (neighbor.equals(destination)) {
                            return newPath;
                        }
                        queue.add(newPath);
                        visited.add(neighbor);
                    }
                }
            }
        }

        return null;
    }
}
