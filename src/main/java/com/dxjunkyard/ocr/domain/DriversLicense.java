package com.dxjunkyard.ocr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriversLicense {
    private Integer driversLicenseId;
    private String userId;
    private String fullName;
    private String address;
    private String birthday;
    private String expirationDate;
    private String note;
}
