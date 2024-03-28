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
            JOIN FETCH r.user u
            WHERE a.id = :id
            """)
    Apply findByResumeIdJoinSkillAndCompany(@Param("id") Integer id);

}
