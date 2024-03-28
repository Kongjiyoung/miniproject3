package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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


    @Query("""
            select distinct a 
            from Apply a 
            join fetch a.post p 
            join fetch p.user pu 
            join fetch p.skillList 
            join fetch a.resume r 
            join fetch r.user ru 
            where p.id= :postId and ru.id=:resumeUserId
            """)
    Optional<Apply> findByPostIdJoinPostAndSkillAndUser(@Param("postId") Integer postId, @Param("resumeUserId") Integer resumeUserId);
}
