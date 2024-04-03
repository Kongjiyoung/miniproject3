package com.many.miniproject1.resume;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    // TODO: 이유 없으면 삭제하기 distinct

    @Query("""
            select distinct r
            from Resume r
            join fetch r.skillList s
            join fetch r.user u
            where r.id = :id
            """)
    Resume findByIdJoinSkillAndUser(@Param("id") int id);

    @Query("select r from Resume r join fetch r.skillList s where r.id = :id")
    Resume findByIdJoinSkill(@Param("id") int id);

    @Query("""
            SELECT r
            FROM Resume r
            JOIN FETCH r.user ru
            WHERE ru.id = :id
            """)
    List<Resume> findByUserId(@Param("id") int userId);

    // TODO: 이유 없으면 삭제하기 distinct
    @Query("""
            select distinct r
            from Resume r
            join fetch r.skillList s
            join fetch r.user u
            where u.id = :id
            """)
    List<Resume> findByUserIdJoinSkillAndUser(@Param("id") int id);

    @Query("""
            select r
            from  Resume r
            join User u on r.user.id = u.id
            where u.id=:user_id
            """)
    List<Resume> findBySessionUserId(@Param("user_id") Integer userId);

    @Query("""
            select r
            from Resume r
            join fetch r.user u
            join fetch r.skillList s
            where u.id=:user_id
            """)
    List<Resume> findAllResume(@Param("user_id") int userId);

    @Query("""
            select r
            from Resume r
            join fetch r.user u
            join fetch r.skillList s
            where r.id = :id
        """)
    Optional<Resume> findByIdJoinUser(@Param("id") int id);

    @Query("""
            SELECT r
            FROM Resume r
            JOIN FETCH r.skillList s
            ORDER BY r.id DESC
            """)
    List<Resume> mainAllResume();

    @Query("""
        select r
        from Resume r
        join fetch r.user u
        join fetch r.skillList s
        where r.id = :resume_id
        """)
    Resume findResumeById(@Param("resume_id")Integer resumeId);
}
