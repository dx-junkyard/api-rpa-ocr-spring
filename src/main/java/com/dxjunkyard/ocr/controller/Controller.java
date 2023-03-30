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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
    @PostMapping("/drivers-license-upload")
    @ResponseBody
    public NormalResponse driversLicenseUpload(
            @RequestParam("file") MultipartFile file) {
        logger.info("ocr API");
        try {
            String user_id = "xxxxx_user_id_xxxxx";
            logger.info("pre resourceLoader.getResource");
            String cpath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();
            logger.info("after resourceLoader.getResource:" + cpath);
            logger.info("pre fileService.putDocument");
            String upldFile = fileService.putDocument(user_id,cpath,file);
            ocrService.scanDriversLicense(user_id, upldFile);
            return NormalResponse.builder().result("OK").build();
        } catch (Exception e) {
            logger.debug("ocr" + e.getMessage());
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
