package com.dxjunkyard.ocr.controller;

import com.dxjunkyard.ocr.domain.response.NormalResponse;
import com.dxjunkyard.ocr.service.FileService;
import com.dxjunkyard.ocr.service.OcrService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private OcrService ocrService;

    @Autowired
    private FileService fileService;

    @Autowired
    protected ResourceLoader resourceLoader;

    /**
     *
     */
    //@PostMapping("/drivers-license-upload")
    @PostMapping("/health-insurance-card")
    @ResponseBody
    public NormalResponse driversLicenseUpload(
            @RequestParam("file") MultipartFile file) {
        logger.info("ocr API");
        try {
            String user_id = "xxxxx_user_id_xxxxx";
            String upldFile = fileService.putDocument(user_id,file);
            ocrService.scanDocument(user_id, upldFile);
            return NormalResponse.builder().result("OK").build();
        } catch (Exception e) {
            logger.info("ocr" + e.getMessage());
            return NormalResponse.builder().result("NG").build();
        }
    }

    /**
     *
     */
    @PostMapping("/drivers-license-upload-local")
    @ResponseBody
    public NormalResponse driversLicenseUploadLocalDebug(
            @RequestParam("file") MultipartFile file) {
        logger.info("ocr API local debug");
        try {
            String user_id = "xxxxx_user_id_xxxxx";
            String cpath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();
            String upldFile = fileService.putDocumentLocalDebug(user_id,cpath,file);
            ocrService.scanDocument(user_id, upldFile);
            return NormalResponse.builder().result("OK").build();
        } catch (Exception e) {
            logger.info("ocr" + e.getMessage());
            return NormalResponse.builder().result("NG").build();
        }
    }

    @GetMapping("/hello")
    @ResponseBody
    public NormalResponse checkin(){
        logger.info("疎通確認 URL");
        return NormalResponse.builder().result("OK").build();
    }

}
