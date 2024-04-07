package com.many.miniproject1.scrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScrapJPARepository extends JpaRepository<Scrap, Integer> {

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
            JOIN FETCH r.skills rs
            WHERE s.id = :scrap_id
            """)
    Optional<Scrap> findByResumeIdAndSkillAndUser(@Param("scrap_id") Integer scrapId);

    @Query("""
            select s
            from Scrap s
            JOIN FETCH s.resume r
            JOIN FETCH r.skills rs
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
    Optional<Scrap> findByScrapIdJoinPostAndSkill(@Param("scrap_id") Integer scrapId);

    @Query("""
            select s
            from Scrap s
            join fetch s.post p
            left join fetch p.skillList
            join fetch p.user pu
            join fetch s.user u
            where u.id = :user_id
            """)
    List<Scrap> findByCompanyIdJoinSkills(@Param("user_id") Integer userId);

    @Query("""
            select s
            from Scrap s
            join fetch s.post p
            join fetch p.user pu
            join fetch p.skillList ps
            where s.id = :scrapId
            """)
    Optional<Scrap> findByScrapIdJoinPost(@Param("scrapId") Integer scrapId);
}
