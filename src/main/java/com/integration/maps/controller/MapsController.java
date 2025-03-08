package com.integration.maps.controller;

import com.integration.maps.domain.Location;
import com.integration.maps.dto.MarkerDTO;
import com.integration.maps.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MapsController {

    private final LocationRepository repository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @GetMapping("/")
    public String homePage() {
        return "index.html";
    }

    @PostMapping("/save")
    public String save(@RequestBody List<MarkerDTO> markers) {
        for (MarkerDTO marker : markers) {
            Point point = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(marker.getLongitude(), marker.getLatitude()));
            Location location = new Location();
            location.setLocation(point);
            repository.save(location);
        }
        return "Pinos salvos com sucesso!";
    }

}
