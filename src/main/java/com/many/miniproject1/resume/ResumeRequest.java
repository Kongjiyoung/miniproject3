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
        //TODO: NotEmpty, null, 공백처리
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

            ProfileImageSaveUtil profileImageSaveUtil = new ProfileImageSaveUtil();
            String profileName = profileImageSaveUtil.convertToBase64(user.getProfile(),user.getProfileName());

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
    public static class UpdateDTO {

        //TODO: NotEmpty, null, 공백처리
        @NotEmpty
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;

        @NotEmpty
        private String profile;

        @NotEmpty
        private String profileName; //

        //널 허용
        private String portfolio;

        @NotEmpty
        @Size(min = 4, max = 300, message = "4자이상 300자이하로 작성해주세요")
        private String introduce;

        @NotEmpty
        private String career;

        //널 허용
        private String simpleIntroduce;

        @NotEmpty
        private List<String> skills;
    }
}