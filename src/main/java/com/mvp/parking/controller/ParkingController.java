package com.mvp.parking.controller;

import com.mvp.parking.controller.request.ParkerRequest;
import com.mvp.parking.dto.TicketParkingDTO;
import com.mvp.parking.service.ParkingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/parkings")
@AllArgsConstructor
public class ParkingController {

    private ParkingService serviceParking;

    @PostMapping
    public ResponseEntity<TicketParkingDTO> parker(@Valid @RequestBody ParkerRequest request,
                                                    UriComponentsBuilder uri){

        var ticketParkingDTO = serviceParking.parker(request);
        var location = uri.path("/api/parkings/{id}")
                .buildAndExpand(ticketParkingDTO.plate())
                .toUri();
        return ResponseEntity.created(location).body(ticketParkingDTO);
    }

    @PutMapping("/{plate}")
    public ResponseEntity<Void> exit(@PathVariable String plate){
        serviceParking.exit(plate);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calculate/{plate}")
    public ResponseEntity<BigDecimal> calculate(@PathVariable String plate){
        return ResponseEntity.ok(serviceParking.calculete(plate));
    }

    @GetMapping
    public ResponseEntity<Map<String, TicketParkingDTO>> allParkers(){
        return ResponseEntity.ok(serviceParking.allParkers());
    }

    @GetMapping("/{plate}")
    public ResponseEntity<TicketParkingDTO> findByPlate(@PathVariable String plate){
        return ResponseEntity.ok(serviceParking.findByPlate(plate));
    }
}
