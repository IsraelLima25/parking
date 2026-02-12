package com.mvp.parking.service;

import com.mvp.parking.controller.request.ParkerRequest;
import com.mvp.parking.dto.TicketParkingDTO;
import com.mvp.parking.enuns.StateParking;
import com.mvp.parking.mapper.TicketParkingMapper;
import com.mvp.parking.model.TicketParking;
import com.mvp.parking.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingService {

    private final IRepository<TicketParking> repository;

    public TicketParkingDTO parker(ParkerRequest request) {
        TicketParking modelTicketParking = new TicketParking(request.plate());
        repository.save(modelTicketParking);
        return new TicketParkingDTO(modelTicketParking.getHash(), modelTicketParking.getMoment(),
                modelTicketParking.getPlate(), modelTicketParking.getState(), modelTicketParking.getPrice());
    }

    public Map<String, TicketParkingDTO> allParkers(){
        return repository.findAll().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> TicketParkingMapper.toTicketParkingDTO(entry.getValue())
                ));
    }

    public TicketParkingDTO findByPlate(String plate){
        var ticketParking = getExistTicket(plate);
        return new TicketParkingDTO(ticketParking.getHash(), ticketParking.getMoment(),
                ticketParking.getPlate(), ticketParking.getState(), ticketParking.getPrice());
    }


    public BigDecimal calculete(String plate) {
        var ticketParking = getExistTicket(plate);
        return ticketParking.calculate();
    }

    public void exit(String plate) {
        var ticketParking = getExistTicket(plate);
        if(ticketParking.getState() != StateParking.PENDING){
            throw new IllegalArgumentException("Warning!! Ticket Resolved.");
        }
        ticketParking.pay();
        repository.update(ticketParking);
    }

    private TicketParking getExistTicket(String plate) {
       return repository.findByPlate(plate).orElseThrow(() -> new IllegalArgumentException("Not object to plate"));
    }
}
