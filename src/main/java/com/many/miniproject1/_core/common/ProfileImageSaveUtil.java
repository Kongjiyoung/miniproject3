package com.many.miniproject1._core.common;

import com.many.miniproject1._core.errors.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfileImageSaveUtil {
    public static String save(MultipartFile profile) {

        String profileFilename = UUID.randomUUID() + "_" + profile.getOriginalFilename();
        Path profilePath = Paths.get("./images/" + profileFilename);
        try {
            Files.write(profilePath, profile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("이미지가 제대로 저장되지 않았음",e);
        }
        return profileFilename;
    }

}
