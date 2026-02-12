package com.mvp.parking.controller.request;

import jakarta.validation.constraints.NotBlank;

public record ParkerRequest(@NotBlank String plate) {}
