package com.dxjunkyard.ocr.domain.request;

import lombok.Data;

@Data
public class RentalConfirmationRequest {
    private String reservationId;
    private String renterId;
}
