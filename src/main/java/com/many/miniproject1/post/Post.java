package com.many.miniproject1.post;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Table(name = "post_tb")
@Data
@Entity
public class Post { // 공고테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Skill> skillList;
    private String title;
    private String career;
    private String pay;
    private String workCondition;
    private String workStartTime;
    private String workEndTime;
    private String deadline;
    private String task;
    private String profile;
    private String profileName;
    private String workingArea;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Post(Integer id, User user, List<Skill> skillList, String title, String career, String pay, String workCondition, String workStartTime, String workEndTime, String deadline, String task, String profile, String profileName, String workingArea, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.skillList = skillList;
        this.title = title;
        this.career = career;
        this.pay = pay;
        this.workCondition = workCondition;
        this.workStartTime = workStartTime;
        this.workEndTime = workEndTime;
        this.deadline = deadline;
        this.task = task;
        this.profile = profile;
        this.profileName = profileName;
        this.workingArea = workingArea;
        this.createdAt = createdAt;
    }

    public void updatePost(PostRequest.UpdatePostDTO reqDTO) {
        this.title = reqDTO.getTitle();
        this.career = reqDTO.getCareer();
        this.pay = reqDTO.getPay();
        this.profileName = reqDTO.getProfileName();
        this.workStartTime = reqDTO.getWorkStartTime();
        this.workEndTime = reqDTO.getWorkEndTime();
        this.deadline = reqDTO.getDeadline();
        this.task = reqDTO.getTask();
        this.workingArea = reqDTO.getWorkingArea();
        this.workCondition = reqDTO.getWorkCondition();
    }

}