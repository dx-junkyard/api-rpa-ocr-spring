package com.dxjunkyard.ocr.api;

import com.dxjunkyard.ocr.domain.request.ClovaOcrMessage;
import com.dxjunkyard.ocr.domain.response.ClovaOcrResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

@Component
public class ClovaOcrRestClient {
    private Logger logger = LoggerFactory.getLogger(ClovaOcrRestClient.class);

    @Value("${clova-ocr.api_uri}")
    private String api_uri;

    @Value("${clova-ocr.template_ids}")
    private String template_id;

    @Value("${clova-ocr.version}")
    private String version;

    @Value("${clova-ocr.lang}")
    private String lang;

    @Value("${clova-ocr.requestId}")
    private String requestId;

    @Value("${clova-ocr.ocr-secret}")
    private String ocrSecret;

    public ClovaOcrResponse clovaOcr(String filepath) {
        try {
            String basefile = Files.getNameWithoutExtension(filepath);
            List<ClovaOcrMessage.Image> images = new ArrayList<>();
            images.add(ClovaOcrMessage.Image.builder()
                            .format("png")
                            .name(basefile)
                            .build());

            List<String> templateIds = new ArrayList<>();
            templateIds.add(template_id);
            ClovaOcrMessage ocrMessage = ClovaOcrMessage.builder()
                    .images(images)
                    .version(version)
                    .template_ids(templateIds)
                    .lang(lang)
                    .requestId(requestId)
                    .timestamp(Instant.now().toEpochMilli())
                    .build();


            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(ocrMessage);
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            //formData.add("message", ocrMessage.toString());
            formData.add("message", message);
            File file = new File(filepath);
            formData.add("file", new FileSystemResource(file));

            // HTTPリクエストヘッダを設定
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-OCR-SECRET", ocrSecret);

            // RestTemplateを作成
            RestTemplate restTemplate = new RestTemplate();

            // POSTリクエストを送信
            ResponseEntity<ClovaOcrResponse> response = restTemplate.exchange(
                    api_uri,
                    HttpMethod.POST,
                    new HttpEntity<>(formData, headers),
                    ClovaOcrResponse.class
            );
            return response.getBody();

        } catch (RestClientException e) {
            logger.info("RestClient error : {}", e.toString());
            return null;
        } catch (Exception e) {
            logger.info("error : {}", e.toString());
            return null;
        }
    }
}
