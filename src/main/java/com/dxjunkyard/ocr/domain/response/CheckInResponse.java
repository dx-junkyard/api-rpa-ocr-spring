package com.dxjunkyard.ocr.domain.response;

import com.dxjunkyard.ocr.domain.Rental;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckInResponse {
    private List<Rental> ocrList;
    private String status;
}
