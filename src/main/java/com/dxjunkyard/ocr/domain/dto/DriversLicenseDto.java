package com.dxjunkyard.ocr.domain.dto;

import com.dxjunkyard.ocr.domain.DriversLicense;
import com.dxjunkyard.ocr.domain.request.ClovaOcrMessage;
import com.dxjunkyard.ocr.domain.response.ClovaOcrResponse;

public class DriversLicenseDto {
    private static String getValue(String fieldName, ClovaOcrResponse response) {
        ClovaOcrResponse.Image im = response.getImages().get(0);
        for (ClovaOcrResponse.Field f : response.getImages().get(0).getFields()) {
            if (f.getName().equals(fieldName)) {
                return f.getInferText();
            }
        }
        return null;
    }
    public static DriversLicense convertOcr(ClovaOcrResponse response) {
        try {
            return DriversLicense.builder()
                    .fullName(getValue("Field 01",response))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
