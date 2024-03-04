package com.many.miniproject1.offer;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "offer_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Offer {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;
    private Integer resumeId;
    private Integer postId;
    private Integer postWriterId;
    private Integer resumeWriterId;
//    private String title;
    private String content;
    private Timestamp created_at;
}