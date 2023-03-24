package com.dxjunkyard.ocr.service;

import com.dxjunkyard.ocr.domain.Rental;
import com.dxjunkyard.ocr.domain.RentalFlatten;
import com.dxjunkyard.ocr.domain.dto.RentalDto;
import com.dxjunkyard.ocr.domain.request.ReservationRequest;
import com.dxjunkyard.ocr.repository.dao.mapper.RentalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class RentalService {
    private Logger logger = LoggerFactory.getLogger(RentalService.class);

    @Autowired
    RentalMapper ocrMapper;

    public List<Rental> checkin(String counterId, String userId) {
        List<Rental> ocrList = new ArrayList<>();
        try {
            LocalDate today = LocalDate.now();
            LocalDateTime midnight = today.atStartOfDay();
            // カウンターID, 借り主のuserid, 貸出日付でレンタルする備品リストを取得
            List<Integer> reservationIdList = ocrMapper.getReservationIdList(
                    counterId
                    , userId
                    , midnight);

            for (Integer reservationId : reservationIdList) {
                List<RentalFlatten> flattenList = ocrMapper.getRental(reservationId);
                Rental ocr = RentalDto.ocr(flattenList);
                ocrList.add(ocr);
            }
            return ocrList;
        } catch (Exception e) {
            logger.info("error" + e.getMessage());
            return ocrList;
        }
    }

    public void reserve(ReservationRequest request) {
        try {
            // todo: 各備品について、その日の在庫があるかどうかをチェックする必要がる
            List<RentalFlatten> reservationList= RentalDto.reserve(request);
            ocrMapper.addReservation(reservationList);
        } catch (Exception e) {
            logger.info("error" + e.getMessage());
        }
    }
}
