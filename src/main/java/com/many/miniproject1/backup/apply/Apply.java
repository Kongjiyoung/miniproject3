package com.many.miniproject1.backup.apply;

import com.many.miniproject1.backup.post.Post;
import com.many.miniproject1.backup.resume.Resume;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "apply_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Apply { // 지원 테이블
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume; // 이력서 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post; // 채용공고 아이디


    private String isPass; // 불합격, 합격, 검토중
    @CreationTimestamp
    private Timestamp createdAt;
}
