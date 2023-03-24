package com.dxjunkyard.ocr.domain.response;

import com.dxjunkyard.ocr.domain.Participant;
import lombok.Data;

import java.util.List;

@Data
public class GetParticipantsResponse {
    private List<Participant> participantList;
}
