package com.dxjunkyard.ocr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    private Integer reservationId; // 予約id
    private List<EquipmentRental> equipmentList;  // 借りる備品idと数量
    private Integer eventId;  // 利用目的のイベントid
    private String renterId; // 借り主の user id
    private String startDate; // 貸出開始
    private String endDate; //　貸出終了
    private String usageDate; // 使用日
    private String comment; // コメント
}
