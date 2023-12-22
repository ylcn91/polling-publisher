package com.doksanbir.pollingpublisher.controller;


import com.doksanbir.pollingpublisher.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
public class LocationController {


    //TODO: Implement the LocationController
    private final LocationService locationService;

    @GetMapping("/locations/all")
    public ResponseEntity<?> getLocations() {
        return ResponseEntity.ok(locationService.findAll());
    }

}
