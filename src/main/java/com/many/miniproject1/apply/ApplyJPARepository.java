package com.many.miniproject1.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("""
            SELECT DISTINCT a
            FROM Apply a
            JOIN FETCH a.resume r
            JOIN FETCH r.skills s
            JOIN FETCH r.user ru
            JOIN FETCH a.post p
            JOIN p.user pu
            WHERE a.id = :id
            """)
    Optional<Apply> findByResumeIdJoinSkillAndCompany(@Param("id") Integer id);

    @Query("""
            SELECT a
            FROM Apply a
            JOIN FETCH a.post p
            JOIN FETCH p.skillList s
            WHERE a.resume.user.id = :userId
            """)
    Optional<List<Apply>> findAllAppliesWithPostsAndSkills(@Param("userId") Integer userId);

    @Modifying
    @Query("""
            delete from Apply a 
            where a.resume.id = :resume_id
                    """)
    void deleteByResumeId(@Param("resume_id") Integer resumeId);

    @Modifying
    @Query("""
            delete from Apply a 
            where a.post.id = :post_id
                    """)
    void deleteByPostId(@Param("post_id") Integer postId);

    @Query("""
            select a
            from Apply a
            join fetch a.resume r
            join fetch r.user u
            join fetch r.skills s
            where a.id = :apply_id
               """)
    Optional<Apply> findResumeByApplyId(@Param("apply_id") Integer applyId);

    @Query("""
            select a
            from Apply a
            join fetch a.resume r
            join fetch r.user ru
            join fetch r.skills s
            join fetch a.post p
            join fetch p.user pu
            where pu.id = :company_id
                        """)
    Optional<List<Apply>> findByCompanyIdJoinResume(@Param("company_id") Integer companyId);

    @Query("""
            select a
            from Apply a
            join fetch a.post p
            join fetch p.user pu
            join fetch p.skillList s
            join fetch a.resume r
            join fetch r.user ru
            where ru.id = :person_id
                        """)
    Optional<List<Apply>> findByPersonIdJoinPost(@Param("person_id") Integer personId);

    @Query("""
            select a
            from Apply a
            join fetch a.post p
            join fetch p.user u
            join fetch p.skillList s
            where a.id = :apply_id
               """)
    Optional<Apply> findPostByApplyId(@Param("apply_id") Integer applyId);
}
