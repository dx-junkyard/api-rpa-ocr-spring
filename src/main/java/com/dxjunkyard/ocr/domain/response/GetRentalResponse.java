package com.dxjunkyard.ocr.domain.response;

import com.dxjunkyard.ocr.domain.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalResponse {
    private List<Rental> eventList;
}
