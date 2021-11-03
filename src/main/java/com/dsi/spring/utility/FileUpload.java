package com.dsi.spring.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.dsi.spring.utility.constants.ImageType;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

    public static String saveImage(ImageType imageType, Long id, MultipartFile file) {

        Path uploadPath;

        switch (imageType) {
        case USER_PROFILE: {
            uploadPath = Paths.get("src/main/resources/static/images/users/");
        }
        case MOVIE_POSTER: {
            uploadPath = Paths.get("src/main/resources/static/images/movies/");
        }
        case CAST_DP: {
            uploadPath = Paths.get("src/main/resources/static/images/casts/");
        }

        default:
            uploadPath = Paths.get("");
        }

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + id + fileExtension;

            Path filePath = uploadPath.resolve(newFileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return uploadPath + "/" + newFileName;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return "";
    }
}
