package com.many.miniproject1.post;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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