package com.mvp.parking.model;

import com.mvp.parking.enuns.StateParking;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class TicketParking {

    private String hash;
    private String plate;
    private LocalDateTime moment;
    private StateParking state;
    private BigDecimal price;

    public TicketParking(String plate) {
        this.plate = plate;
        this.hash = UUID.randomUUID().toString();
        this.moment = LocalDateTime.now();
        this.state = StateParking.PENDING;
        this.price = BigDecimal.ZERO;
    }
    
    public BigDecimal calculate(){

        LocalDateTime end = LocalDateTime.now();
        long totalHours = Duration.between(end, moment).toHours();

        if(totalHours <= 1){
            return new BigDecimal("9.00");
        }else{
            return new BigDecimal("14.00");
        }
    }

    public void pay(){
       this.price = calculate();
       this.state = StateParking.RESOLVED;
    }
}
