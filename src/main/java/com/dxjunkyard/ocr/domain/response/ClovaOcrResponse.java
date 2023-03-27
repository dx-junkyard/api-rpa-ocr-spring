package com.dxjunkyard.ocr.domain.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ClovaOcrResponse {
    private String version;
    private String requestId;
    private Long timestamp;
    private List<Image> images;
    @Data
    @JsonSerialize
    public static class Image {
        private String uid;
        private String name;
        private String inferResult;
        private String message;
        private MatchedTemplate matchedTemplate;
        private ValidationResult validationResult;
        private ConvertedImageInfo convertedImageInfo;
        private Title title;
        private List<Field> fields;
    }
    @Data
    @JsonSerialize
    public static class MatchedTemplate {
        private Integer id;
        private String name;
    }
    @Data
    @JsonSerialize
    public static class ValidationResult {
        private String result;
    }
    @Data
    @JsonSerialize
    public static class ConvertedImageInfo {
        private Integer width;
        private Integer height;
        private Integer pageIndex;
        private Boolean longImage;
    }
    @Data
    @JsonSerialize
    public static class Vertice {
        private Integer x;
        private Integer y;
    }
    @Data
    @JsonSerialize
    public static class BoundingPoly {
        private List<Vertice> vertices;
    }
    @Data
    @JsonSerialize
    public static class SubField {
        private BoundingPoly boundingPoly;
        private String inferText;
        private float inferConfidence;
        private boolean lineBreak;
    }
    @Data
    @JsonSerialize
    public static class Title {
        private String name;
        private BoundingPoly boundingPoly;
        private String inferText;
        private float inferConfidence;
        private List<SubField> subFields;
    }
    @Data
    @JsonSerialize
    public static class Field {
        private String name;
        private String valueType;
        private BoundingPoly boundingPoly;
        private String inferText;
        private float inferConfidence;
        private List<SubField> subFields;
    }
}
