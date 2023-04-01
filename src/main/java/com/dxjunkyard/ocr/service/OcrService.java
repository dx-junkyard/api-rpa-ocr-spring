package com.dxjunkyard.ocr.service;

import com.dxjunkyard.ocr.api.ClovaOcrRestClient;
import com.dxjunkyard.ocr.domain.DriversLicense;
import com.dxjunkyard.ocr.domain.dto.DriversLicenseDto;
import com.dxjunkyard.ocr.domain.response.ClovaOcrResponse;
import com.dxjunkyard.ocr.repository.dao.mapper.DriversLicenseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OcrService {
    private Logger logger = LoggerFactory.getLogger(OcrService.class);

    @Autowired
    DriversLicenseMapper ocrMapper;

    @Autowired
    ClovaOcrRestClient clovaOcrRestClient;

    public void scanDocument(String user_id, String filename) {
        try {
            ClovaOcrResponse ocrResponse = clovaOcrRestClient.clovaOcr(filename);
            DriversLicense license = DriversLicenseDto.convertOcr(ocrResponse);
            license.setUserId(user_id);
            ocrMapper.addDriversLicense(license);
        } catch (Exception e) {
            return;
        }
    }
}
