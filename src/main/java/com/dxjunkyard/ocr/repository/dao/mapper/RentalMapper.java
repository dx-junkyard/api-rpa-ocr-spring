package com.dxjunkyard.ocr.repository.dao.mapper;

import com.dxjunkyard.ocr.domain.RentalFlatten;
import com.dxjunkyard.ocr.domain.EquipmentRental;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RentalMapper {
    List<Integer> getReservationIdList(String counterId , String renterId, LocalDateTime today);
    void addReservation(@Param("reservationList")List<RentalFlatten> reservationList);
    void addIncludeSportRental(Integer sportRentalId);
    List<RentalFlatten> getRental(Integer reservationId);
    List<EquipmentRental> getIncludedSportRental(Integer eventId);
}
