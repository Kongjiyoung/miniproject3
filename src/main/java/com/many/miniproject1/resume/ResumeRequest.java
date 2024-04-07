package com.many.miniproject1.resume;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeRequest {

    @Data
    public static class ResumeSaveDTO{
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;
        @NotEmpty(message = "사진은 공백일 수 없습니다")
        private String profile;
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName;
        @NotEmpty(message = "커리어는 공백일 수 없습니다")
        private String career;
        @NotEmpty(message = "간단자기소개는 공백일 수 없습니다")
        @Size(min = 4, max = 30, message = "4자이상 30자이하로 작성해주세요")
        private String simpleIntroduce;
        @NotEmpty(message = "포토폴리오는 공백일 수 없습니다")
        private String portfolio;
        @NotEmpty(message = "간단자기소개는 공백일 수 없습니다")
        @Size(min = 4, max = 300, message = "4자이상 300자이하로 작성해주세요")
        private String introduce;
        @NotEmpty(message = "스킬은 공백일 수 없습니다")
        private List<String> skills = new ArrayList<>();

        public Resume toEntity(User user){

            ProfileImageSaveUtil profileImageSaveUtil = new ProfileImageSaveUtil();
            String profilePathName = profileImageSaveUtil.convertToBase64(profile, profileName);

            return Resume.builder()
                    .user(user)
                    .title(title)
                    .profile(profilePathName)
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

        @NotEmpty(message = "제목은 공백일 수 없습니다")
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;
        @NotEmpty(message = "사진은 공백일 수 없습니다")
        private String profile;
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName; //
        @NotEmpty(message = "포트폴리오는 공백일 수 없습니다")
        private String portfolio;
        @NotEmpty(message = "자기소개는 공백일 수 없습니다")
        @Size(min = 4, max = 300, message = "4자이상 300자이하로 작성해주세요")
        private String introduce;
        @NotEmpty(message = "커리어는 공백일 수 없습니다")
        private String career;
        @NotEmpty(message = "간단자기소개는 공백일 수 없습니다")
        private String simpleIntroduce;
        @NotEmpty(message = "스킬은 공백일 수 없습니다")
        private List<String> skills;
    }
}