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
            WHERE r.id = :resumeid and pu.id = :userid
            """)
    Apply findByResumeIdJoinSkillAndCompany(@Param("resumeid") Integer resumeid, @Param("userid") Integer userid );

}
