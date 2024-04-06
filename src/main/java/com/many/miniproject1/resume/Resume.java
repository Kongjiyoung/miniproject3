package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "resume_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Resume {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;

    @JoinColumn(name = "person_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Skill> skills = new ArrayList<>();

    @Transient
    private boolean isResumeOwner;

    private String title;
    private String profile;
    private String profileName;

    //널 허용
    private String portfolio;
    private String introduce;
    private String career;

    //널 허용
    private String simpleIntroduce;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Resume(Integer id, User user, List<Skill> skills, boolean isResumeOwner, String title, String profile, String profileName, String portfolio, String introduce, String career, String simpleIntroduce, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.skills = skills;
        this.isResumeOwner = isResumeOwner;
        this.title = title;
        this.profile = profile;
        this.profileName = profileName;
        this.portfolio = portfolio;
        this.introduce = introduce;
        this.career = career;
        this.simpleIntroduce = simpleIntroduce;
        this.createdAt = createdAt;
    }

    public void updateResume(ResumeRequest.UpdateDTO reqDTO) {
        this.title = reqDTO.getTitle();
        this.portfolio = reqDTO.getPortfolio();
        this.introduce = reqDTO.getIntroduce();
        this.profileName = reqDTO.getProfileName();
        this.career = reqDTO.getCareer();
        this.simpleIntroduce = reqDTO.getSimpleIntroduce();
    }
    
    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", profile='" + profile + '\'' +
                ", portfolio='" + portfolio + '\'' +
                ", introduce='" + introduce + '\'' +
                ", career='" + career + '\'' +
                ", simpleIntroduce='" + simpleIntroduce + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}