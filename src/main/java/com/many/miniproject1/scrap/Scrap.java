package com.many.miniproject1.scrap;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "scrap_tb")
@Data
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;
    @JoinColumn(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Scrap(Integer id, Resume resume, Post post, User user, Timestamp createdAt) {
        this.id = id;
        this.resume = resume;
        this.post = post;
        this.user = user;
        this.createdAt = createdAt;
    }
}