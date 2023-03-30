package com.dxjunkyard.ocr.service;

import com.dxjunkyard.ocr.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private Logger logger = LoggerFactory.getLogger(FileService.class);
    private final String UPLOAD_DIR = "/uploads"; // ファイルを保存するディレクトリ

    /*
     *
     */
    public boolean createUserDir(String userDirPath) {
        File userDir = new File(userDirPath);

        if (!userDir.exists()) {
            logger.info("create user dir : " + userDir.getAbsolutePath());
            boolean result = userDir.mkdirs();
            return result;
        } else {
            logger.info("already exist user dir : " + userDir.getAbsolutePath());
            return false;
        }
    }

    /*
     * アップロードされたファイルを適切な場所に保管し、保管先のパスを返す
     */
    public String putDocument(String userId, String cpath, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            //Path path = Paths.get(UPLOAD_DIR + "/" + file.getOriginalFilename());
            // todo : userIdごとの保管ディレクトリを作成し、保管先のパスに含める
            String userDir = cpath + File.separator + "uploads" + File.separator + userId;
            createUserDir(userDir);
            Path path = Paths.get(userDir + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (Exception e) {
            logger.debug("putDocument" + e.getMessage());
        }
        return null;
    }

}
