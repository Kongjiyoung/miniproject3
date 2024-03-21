package com.many.miniproject1.backup.skill;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "skill_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Skill {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;
    private String skill;
    private Integer resumeId; //null
    private Integer postId; // null 허용 되어야 한다
    private Timestamp createdAt;
}