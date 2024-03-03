package com.many.miniproject1.skill;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "skill_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Skill {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;
    private String skillId;
    private String resumeId;
    private String postId;
    private Integer role;
}
