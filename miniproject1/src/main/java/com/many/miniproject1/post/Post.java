package com.many.miniproject1.post;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "post_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Post {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
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
    private Timestamp createdAt;
}