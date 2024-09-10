package com.fauxtels.fauxtelhotels.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauxtels.fauxtelhotels.models.Location;
import com.fauxtels.fauxtelhotels.models.Room;
import com.fauxtels.fauxtelhotels.repository.LocationRepository;
import com.fauxtels.fauxtelhotels.repository.RoomRepository;

@RestController
@RequestMapping("/locations/{locationId}/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Room> getAllRooms(@PathVariable Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow();
        return location.getRooms();
    }

    @PostMapping
    public Room createRoom(@PathVariable Long locationId, @RequestBody Room room) {
        Location location = locationRepository.findById(locationId).orElseThrow();
        room.setLocation(location);
        return roomRepository.save(room);
    }

}
