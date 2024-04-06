package com.many.miniproject1.post;

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

public class PostRequest {

    @Data
    public static class SavePostDTO {
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;
        @NotEmpty(message = "커리어는 공백일 수 없습니다")
        private String career;
        @NotEmpty(message = "연봉은 공백일 수 없습니다")
        private String pay;
        @NotEmpty(message = "근무조건은 공백일 수 없습니다")
        private String workCondition;
        @NotEmpty(message = "근무시작시간은 공백일 수 없습니다")
        private String workStartTime;
        @NotEmpty(message = "근무끝시간은 공백일 수 없습니다")
        private String workEndTime;
        @NotEmpty(message = "마감일은 공백일 수 없습니다")
        private String deadline;
        @NotEmpty(message = "직무는 공백일 수 없습니다")
        @Size(min = 4, max = 100, message = "4자이상 100자이하로 작성해주세요")
        private String task;
        @NotEmpty(message = "사진은 공백일 수 없습니다")
        private String profile;
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName;
        @NotEmpty(message = "근무지는 공백일 수 없습니다")
        private String workingArea;
        @NotEmpty(message = "스킬은 공백일 수 없습니다")
        private List<String> skills = new ArrayList<>();

        public Post toEntity(User user) {

            ProfileImageSaveUtil profileImageSaveUtil = new ProfileImageSaveUtil();
            String profilePathName = profileImageSaveUtil.convertToBase64(profile,profileName);

            return Post.builder()
                    .user(user)
                    .title(title)
                    .career(career)
                    .pay(pay)
                    .workCondition(workCondition)
                    .workStartTime(workStartTime)
                    .workEndTime(workEndTime)
                    .deadline(deadline)
                    .task(task)
                    .workingArea(workingArea)
                    .profile(profilePathName)
                    .profileName(profileName)
                    .build();
        }
    }

    @Data
    public static class UpdatePostDTO {
        @NotEmpty(message = "사지는 공백일 수 없습니다")
        private String profile;
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName;
        @NotEmpty(message = "제목은 공백일 수 없습니다")
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;
        @NotEmpty(message = "커리어는 공백일 수 없습니다")
        private String career;
        @NotEmpty(message = "연봉은 공백일 수 없습니다")
        private String pay;
        @NotEmpty(message = "근무시작시간은 공백일 수 없습니다")
        private String workStartTime;
        @NotEmpty(message = "근무시끝시간은 공백일 수 없습니다")
        private String workEndTime;
        @NotEmpty(message = "마감시간은 공백일 수 없습니다")
        private String deadline;
        @NotEmpty(message = "직무는 공백일 수 없습니다")
        @Size(min = 4, max = 100, message = "4자이상 100자이하로 작성해주세요")
        private String task;
        @NotEmpty(message = "근무지는 공백일 수 없습니다")
        private String workingArea;
        @NotEmpty(message = "근무조건은 공백일 수 없습니다")
        private String workCondition;
        @NotEmpty(message = "스킬은 공백일 수 없습니다")
        private List<String> skills;
    }
}