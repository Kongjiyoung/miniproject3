package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("""
            SELECT DISTINCT a
            FROM Apply a
            JOIN FETCH a.resume r
            JOIN FETCH r.skillList s
            JOIN FETCH r.user ru
            JOIN FETCH a.post p
            JOIN p.user pu
            WHERE r.id = :resume_id and pu.id = :user_id
            """)
    Apply findByResumeIdJoinSkillAndCompany(@Param("resume_id") Integer resumeId, @Param("user_id") Integer userId );

    // 1. 어플리 디테일
    // 지원받은 이력서에 띄워져 있는 이력서의 디테일에 가서(어플라이드레저미디테일)
    // 개인정보 가져오고
    // 이력서 가져와야 하구요
    // 이력서 가져오면서 스킬도 가져오구요
}
