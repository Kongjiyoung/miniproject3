package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@NoArgsConstructor
@Table(name = "apply_tb")
@Data
@Entity
public class Apply { // 지원 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume; // 이력서 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post; // 채용공고 아이디
    private String isPass; // 불합격, 합격, 검토중
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Apply(Integer id, Resume resume, Post post, String isPass, Timestamp createdAt) {
        this.id = id;
        this.resume = resume;
        this.post = post;
        this.isPass = isPass;
        this.createdAt = createdAt;
    }

    public void updateIsPass(ApplyRequest.UpdateIsPassDTO reqDTO) {
        this.isPass = reqDTO.getIsPass();
    }
}
