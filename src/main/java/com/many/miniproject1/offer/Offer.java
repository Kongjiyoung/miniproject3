package com.many.miniproject1.offer;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "offer_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Offer {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;         // 제안 ID

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;   // &#xC774;&#xB825;&#xC11C; ID
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;     // 공고 ID

    //    private String content;
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Offer(Integer id, Resume resume, Post post, Timestamp createdAt) {

        this.id = id;
        this.post = post;
        this.resume = resume;
        this.createdAt = createdAt;
    }
}