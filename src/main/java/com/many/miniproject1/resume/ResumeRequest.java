package com.many.miniproject1.resume;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class ResumeRequest {

    // 04-03 YSH
    @Data
    public static class ResumeSaveDTO{
        private String title;
        private String profile;
        private String profileName;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private List<String> skills = new ArrayList<>();

        public Resume toEntity(User user){
            byte[] decodedBytes = Base64.getDecoder().decode(profile);
            String profilename = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + "_" + profileName;
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }

            return Resume.builder()
                    .user(user)
                    .title(title)
                    .profile(profile)
                    .profileName(profileName)
                    .career(career)
                    .simpleIntroduce(simpleIntroduce)
                    .portfolio(portfolio)
                    .introduce(introduce)
                    .build();
        }
    }

    @Data
    public static class ResumeDTO {
        private Integer id;
        private Integer personId;
        private String title;
        private MultipartFile profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private String email;
        private String password;
        private String username;
        private String tel;
        private String address;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private Integer personId;
        private String title;
        private String profile;
        private String profileName;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private List<String> skills;
    }
}