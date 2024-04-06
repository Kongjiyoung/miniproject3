package com.many.miniproject1._core.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfileImageSaveUtil {

    public static String convertToBase64(String profile, String profileName) {
        byte[] decodedBytes = Base64.getDecoder().decode(profile);
        String profilename= UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + "_" + profileName;
        try {
            Path path = Path.of("./images/" + profilename);
            Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profilename;
    }
}
