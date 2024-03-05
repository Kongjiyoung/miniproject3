package com.many.miniproject1.post;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class PostResponse {
    @Data
    public static class DetailDTO{
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
        private List<String> skill;
        private String workingArea;
    }

    @Data
    public static class UpdateDTO{
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
        private List<String> skills; // 기존 스킬을 삭제하고, 인서트하기
        private String workingArea;
    }
}
