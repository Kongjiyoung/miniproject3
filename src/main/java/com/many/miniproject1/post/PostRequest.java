package com.many.miniproject1.post;

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

public class PostRequest {

    @Data
    public static class PostSaveDTO {
        private String title;
        private String career;
        private String pay;
        private String workCondition;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String profile;
        private String profileName;
        private String workingArea;
        private List<String> skillList = new ArrayList<>();

        public Post toEntity(User user) {
            byte[] decodedBytes = Base64.getDecoder().decode(profile);
            String profilename = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + "_" + profileName;
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    .profile(profileName)
                    .profileName(profileName)
                    .build();
        }
    }

    @Data
    public static class SaveDTO {
        private Integer id;
        private Integer companyId;
        private String title;
        private String career;
        private String pay;
        private String workCondition;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String profile;
        private String workingArea;
        private List<String> skill;
        public Post toEntity(User user) {
//            String profilePath= ProfileImageSaveUtil.save(profile);
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
                    .build();
        }
    }


    @Data
    public static class UpdateDTO {
        private Integer id;
        private Integer companyId;
        private String title;
        private String career;
        private String pay;
        private String workCondition;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String profile;
        private String workingArea;
        private List<String> skill;
    }

    @Data
    public static class UpdatePostDTO {
        private Integer id;
        private Integer companyId;
        private String title;
        private String career;
        private String pay;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String workingArea;
        private String workCondition;
        private List<String> skills;
    }
}