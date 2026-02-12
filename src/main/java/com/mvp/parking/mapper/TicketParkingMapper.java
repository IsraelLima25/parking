package com.mvp.parking.mapper;

import com.mvp.parking.dto.TicketParkingDTO;
import com.mvp.parking.model.TicketParking;


public class TicketParkingMapper {

    public static TicketParkingDTO toTicketParkingDTO(TicketParking ticketParking){
        return new TicketParkingDTO(ticketParking.getHash(), ticketParking.getMoment(),
                ticketParking.getPlate(), ticketParking.getState(), ticketParking.getPrice());
    }
}
