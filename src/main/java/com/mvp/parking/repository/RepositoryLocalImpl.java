package com.mvp.parking.repository;

import com.mvp.parking.model.TicketParking;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class RepositoryLocalImpl implements IRepository<TicketParking>{

    private Map<String, TicketParking> mapData = new HashMap<>();

    @Override
    public void save(TicketParking ticketParking) {
        mapData.put(ticketParking.getPlate(), ticketParking);
    }

    @Override
    public Optional<TicketParking> findByPlate(String plate) {
        return Optional.ofNullable(mapData.get(plate));
    }

    @Override
    public Map<String, TicketParking> findAll() {
        return mapData;
    }

    @Override
    public void update(TicketParking ticketParking) {
        mapData.remove(ticketParking.getPlate());
        mapData.put(ticketParking.getPlate(), ticketParking);
    }
}
