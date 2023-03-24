package com.dxjunkyard.ocr.domain.request;

import com.dxjunkyard.ocr.domain.EquipmentRental;
import lombok.Data;

import java.util.List;

@Data
public class ReservationRequest {
    private List<EquipmentRental> equipmentList;
    private Integer eventId;  // 利用目的のイベントid
    private String renterId; // 借り主の user id
    private String startDate; // 貸出開始
    private String endDate; //　貸出終了
    private String usageDate; // 使用日
    private String comment; // コメント
}
