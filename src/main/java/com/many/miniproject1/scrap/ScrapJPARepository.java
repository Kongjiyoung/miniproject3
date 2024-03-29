package com.many.miniproject1.scrap;

import jakarta.persistence.criteria.CriteriaBuilder;
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
            join fetch s.post p
            join fetch p.skillList l
            where s.resume.user.id = :user_id
            """)
    List<Scrap> findByPostIdJoinskills(@Param("user_id") Integer userId);
}
