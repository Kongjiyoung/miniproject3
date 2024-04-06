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
        @NotEmpty
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;
        @NotEmpty
        private String career;
        private String pay;
        @NotEmpty
        private String workCondition;
        @NotEmpty
        private String workStartTime;
        @NotEmpty
        private String workEndTime;
        @NotEmpty
        private String deadline;
        @NotEmpty
        @Size(min = 4, max = 100, message = "4자이상 100자이하로 작성해주세요")
        private String task;
        @NotEmpty
        private String profile;
        @NotEmpty
        private String profileName;
        @NotEmpty
        private String workingArea;
        private List<String> skills = new ArrayList<>();

        public Post toEntity(User user) {

            ProfileImageSaveUtil profileImageSaveUtil = new ProfileImageSaveUtil();
            String profileName = profileImageSaveUtil.convertToBase64(user.getProfile(),user.getProfileName());

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
                    .profile(profile)
                    .profileName(profileName)
                    .build();
        }
    }

    @Data
    public static class UpdatePostDTO {
        @NotEmpty
        private String profile;
        @NotEmpty
        private String profileName;
        @NotEmpty
        @Size(min = 4, max = 50, message = "4자이상 50자이하로 작성해주세요")
        private String title;
        @NotEmpty
        private String career;
        @NotEmpty
        private String pay;
        @NotEmpty
        private String workStartTime;
        @NotEmpty
        private String workEndTime;
        @NotEmpty
        private String deadline;
        @NotEmpty
        @Size(min = 4, max = 100, message = "4자이상 100자이하로 작성해주세요")
        private String task;
        @NotEmpty
        private String workingArea;
        @NotEmpty
        private String workCondition;
        @NotEmpty
        private List<String> skills;
    }
}