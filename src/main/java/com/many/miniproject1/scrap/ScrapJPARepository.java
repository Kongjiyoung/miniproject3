package com.many.miniproject1.scrap;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

public interface ScrapJPARepository extends JpaRepository<Scrap, Integer> {

//    @Modifying
//    @Query("delete from Scrap s where s.post.id = :post_id and s.resume.user.id= :resume_user_id")
//    void deleteScrapByPostId(@Param("post_id") Integer postId, @Param("resume_user_id") Integer resumeUserId);

    @Query("""
            select s
            from Scrap s
            join fetch s.post p
            join fetch p.skillList ps
            where s.user.id = :user_id
            """)
    List<Scrap> findByPostIdJoinSkills(@Param("user_id") Integer userId);

    @Query("""
            SELECT s
            from Scrap s
            JOIN FETCH s.resume r
            JOIN FETCH r.user u
            JOIN FETCH r.skillList rs
            WHERE s.id = :scrap_id
            """)
    Scrap findByResumeIdAndSkillAndUser(@Param("scrap_id") Integer scrapId);

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

    @Modifying
    @Query("""
            delete from Scrap s
            where s.resume.id = :resume_id
                    """)
    void deleteByResumeId(@Param("resume_id") Integer resumeId);

    @Modifying
    @Query("""
            delete from Scrap s
            where s.post.id = :post_id
                    """)
    void deleteByPostId(@Param("post_id") Integer postId);

    @Query("""
            select s
            from Scrap s
            join fetch s.post p
            join fetch p.skillList ps
            where s.id = :scrap_id
                        """)
    Scrap findByScrapIdJoinPostAndSkill(@Param("scrap_id") Integer scrapId);

    @Query("""
            select distinct s
            from Scrap s
            join fetch s.post p
            join fetch p.skillList ps
            join fetch p.user pu
            join fetch s.user u
            where u.id = :user_id
                """)
    List<Scrap> findByCompanyIdJoinSkills(@Param("user_id") Integer userId);
}
