package com.dxjunkyard.ocr.repository.dao.mapper;

import com.dxjunkyard.ocr.domain.DriversLicense;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DriversLicenseMapper {
    void addDriversLicense(DriversLicense license);
    List<DriversLicense> getDriversLicense(Integer driversLicenseId);
}
