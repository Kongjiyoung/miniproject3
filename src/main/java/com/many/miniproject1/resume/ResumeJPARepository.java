package com.many.miniproject1.resume;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {
    @Query("""
            select r
            from Resume r
            join fetch r.skills s
            join fetch r.user u
            where r.id = :id
            """)
    Optional<Resume> findByIdJoinSkillAndUser(@Param("id") Integer id);

    @Query("""
            select r
            from Resume r
            join fetch r.skills s
            join fetch r.user u
            where u.id = :id
            """)
    List<Resume> findByUserIdJoinSkillAndUser(@Param("id") Integer id);

    @Query("""
            select r
            from Resume r
            join fetch r.user u
            join fetch r.skills s
            where u.id=:user_id
            """)
    List<Resume> findAllResume(@Param("user_id") Integer userId);

    @Query("""
            select r
            from Resume r
            join fetch r.user u
            join fetch r.skills s
            where r.id = :id
        """)
    Optional<Resume> findByIdJoinUser(@Param("id") Integer id);

    @Query("""
        select r
        from Resume r
        join fetch r.user u
        join fetch r.skills s
        where r.id = :resume_id
        """)
    Optional<Resume> findResumeById(@Param("resume_id")Integer resumeId);

    @Query("""
            SELECT r
            FROM Resume r
            JOIN FETCH r.skills s
            ORDER BY r.id DESC
            """)
    List<Resume> mainAllResume();
}
