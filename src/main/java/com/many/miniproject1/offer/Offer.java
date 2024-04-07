package com.many.miniproject1.offer;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "offer_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 제안 ID
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post; // 공고 ID
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