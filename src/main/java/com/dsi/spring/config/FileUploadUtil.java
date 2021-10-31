package com.dsi.spring.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

    public static String saveFile(Long employeeId, String uploadDir, MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir);
        String newFileName = null;

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            newFileName = System.currentTimeMillis() + employeeId + fileExtension;
            Path filePath = uploadPath.resolve(newFileName);
            Files.copy( file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {

        }

        return newFileName;
    }
}
