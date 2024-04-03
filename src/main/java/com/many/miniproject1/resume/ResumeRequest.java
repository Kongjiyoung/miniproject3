package com.many.miniproject1.resume;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Data
    public static class ResumeSaveDTO{
        @NotEmpty
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;
        @NotEmpty
        private String profile;
        @NotEmpty
        private String profileName;
        @NotEmpty
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        @NotEmpty
        @Size(min = 4, max = 300, message = "4자이상 300자이하로 작성해주세요")
        private String introduce;
        @NotEmpty
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
    public static class SaveDTO{

        private User user;
        private String title;
        private MultipartFile profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private List<String> skills;

        public Resume toEntity(User sessionUser){
            String profilePath= ProfileImageSaveUtil.save(profile);
            return Resume.builder()
                    .user(sessionUser)
                    .title(title)
                    .profile(profilePath)
                    .portfolio(portfolio)
                    .introduce(introduce)
                    .career(career)
                    .simpleIntroduce(simpleIntroduce)
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
        private MultipartFile profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private List<String> skills;
    }
}