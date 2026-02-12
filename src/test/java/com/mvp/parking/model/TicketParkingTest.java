package com.mvp.parking.model;

import com.mvp.parking.enuns.StateParking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class TicketParkingTest {

    @Test
    @DisplayName("calculate price by hour")
    void calculete_shouldCalculeteParkingWhenCalculeteCall(){
        var ticketParking = new TicketParking("ABC-2036");
        ticketParking.pay();
        assertThat(ticketParking.getState()).isEqualTo(StateParking.RESOLVED);
        assertThat(ticketParking.getPrice()).isEqualTo(new BigDecimal("9.00"));
    }
}