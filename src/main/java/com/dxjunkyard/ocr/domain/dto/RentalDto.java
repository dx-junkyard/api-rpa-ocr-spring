package com.dxjunkyard.ocr.domain.dto;


import com.dxjunkyard.ocr.domain.EquipmentRental;
import com.dxjunkyard.ocr.domain.Rental;
import com.dxjunkyard.ocr.domain.RentalFlatten;
import com.dxjunkyard.ocr.domain.request.ReservationRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RentalDto {
    public static List<RentalFlatten> reserve(ReservationRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(request.getStartDate(), formatter).toLocalDate().atStartOfDay();
        LocalDateTime endDate = LocalDateTime.parse(request.getEndDate(), formatter).toLocalDate().atStartOfDay();
        LocalDateTime usageDate = LocalDateTime.parse(request.getUsageDate(), formatter).toLocalDate().atStartOfDay();

        List<RentalFlatten> flattenList = new ArrayList<>();
        try {
            for (EquipmentRental equipment : request.getEquipmentList()) {
                flattenList.add(RentalFlatten.builder()
                                .equipmentId(equipment.getEquipmentId())
                                .equipmentN(equipment.getEquipmentN())
                                .eventId(request.getEventId())
                                .renterId(request.getRenterId())
                                .startDate(startDate)
                                .endDate(endDate)
                                .usageDate(usageDate)
                                .comment(request.getComment())
                        .build());
            }
            return flattenList;
        } catch (Exception e) {
            return flattenList;
        }
    }


    public static Rental ocr(List<RentalFlatten> ocrList) {
        List<EquipmentRental> equipmentList = new ArrayList<>();
        try {
            for (RentalFlatten flatten : ocrList) {
                equipmentList.add(EquipmentRental.builder()
                        .equipmentId(flatten.getEquipmentId())
                        .equipmentN(flatten.getEquipmentN())
                        .build());
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 0:00:00");
            return Rental.builder()
                    .reservationId(ocrList.get(0).getReservationId())
                    .equipmentList(equipmentList)
                    .eventId(ocrList.get(0).getReservationId())
                    .renterId(ocrList.get(0).getRenterId())
                    .startDate(ocrList.get(0).getStartDate().format(formatter))
                    .endDate(ocrList.get(0).getEndDate().format(formatter))
                    .usageDate(ocrList.get(0).getUsageDate().format(formatter))
                    .comment(ocrList.get(0).getComment())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
