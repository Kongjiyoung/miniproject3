package com.many.miniproject1.scrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapJPARepository extends JpaRepository<Scrap, Integer> {

//    @Modifying
//    @Query("delete from Scrap s where s.post.id = :post_id and s.resume.user.id= :resume_user_id")
//    void deleteScrapByPostId(@Param("post_id") Integer postId, @Param("resume_user_id") Integer resumeUserId);

    @Query("""
            select s
            from Scrap s
            JOIN FETCH s.resume r
            JOIN FETCH r.skillList rs
            join FETCH r.user ru
            JOIN FETCH s.user u
            where u.id = :user_id
            """)
    List<Scrap> findByUserIdJoinSkillAndResume (@Param("user_id") Integer userId);

}
