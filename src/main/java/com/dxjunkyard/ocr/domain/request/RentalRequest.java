package com.dxjunkyard.ocr.domain.request;

import lombok.Data;

@Data
public class RentalRequest {
    private Integer counterId; //
    private Integer renterId; // 借り主の user id
}
