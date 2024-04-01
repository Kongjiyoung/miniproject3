package com.many.miniproject1._core.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
            throw new RuntimeException("이미지가 제대로 저장되지 않았음", e);
        }
        return profileFilename;
    }

    public static String convertToBase64(MultipartFile profile) {
        try {
            byte[] fileBytes = profile.getBytes();
            String base64String = Base64.getEncoder().encodeToString(fileBytes);
            return base64String;
        } catch (IOException e) {
            throw new RuntimeException("멀티파트 파일을 Base64로 변환하는 중 오류 발생", e);
        }
    }
}
