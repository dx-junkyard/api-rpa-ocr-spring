package com.dxjunkyard.ocr.service;

import com.dxjunkyard.ocr.controller.Controller;
import lombok.Value;
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
    private final String UPLOAD_DIR = "/tmp/rpa-ocr/uploads"; // ファイルを保存するディレクトリ

    /*
     *
     */

    public boolean createUserDir(String userDirPath) {

        logger.info("createUserDir: " + userDirPath);
        File userDir = new File(userDirPath);

        logger.info("check user dir : " + userDir.getAbsolutePath());
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
    public String putDocumentLocalDebug(String userId, String cpath, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            // userIdごとの保管ディレクトリを作成し、保管先のパスに含める
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

    /*
     * アップロードされたファイルを適切な場所に保管し、保管先のパスを返す
     */
    public String putDocument(String userId, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            // userIdごとの保管ディレクトリを作成し、保管先のパスに含める
            String userDir = UPLOAD_DIR + File.separator + "uploads" + File.separator + userId;
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
