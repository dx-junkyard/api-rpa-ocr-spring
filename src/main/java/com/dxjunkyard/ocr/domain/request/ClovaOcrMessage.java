package com.dxjunkyard.ocr.domain.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonSerialize
public class ClovaOcrMessage {
    private List<Image> images;
    private List<String> template_ids;
    private String lang;
    private String version;
    private String requestId;
    private Long timestamp;
    @Data
    @Builder
    @JsonSerialize
    public static class Image {
        private String format;
        private byte[] data;
        private String name;
    }
}
