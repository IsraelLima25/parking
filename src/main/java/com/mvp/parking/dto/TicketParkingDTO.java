package com.mvp.parking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mvp.parking.enuns.StateParking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketParkingDTO(String hash, LocalDateTime moment, @JsonIgnore String plate, StateParking state, BigDecimal price) { }
