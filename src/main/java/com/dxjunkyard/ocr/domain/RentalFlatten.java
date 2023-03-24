package com.dxjunkyard.ocr.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RentalFlatten {
    private Integer reservationId; // 予約id
    private Integer equipmentId; // 備品id
    private Integer equipmentN; // 借りる備品の数
    private Integer eventId;  // 利用目的のイベントid
    private String renterId; // 借り主の user id
    private LocalDateTime startDate; // 貸出開始
    private LocalDateTime endDate; //　貸出終了
    private LocalDateTime usageDate; // 使用日
    private String comment; // コメント
}
