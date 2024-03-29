package com.many.miniproject1.resume;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

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
            JOIN FETCH r.user
            WHERE r.id = :id
            """)
    List<Resume> findByUserId(@Param("id") int userId);

    @Query("""
            select distinct r
            from Resume r
            join fetch r.skillList s
            join fetch r.user u
            where u.id = :id
            """)
    List<Resume> findByuserIdJoinSkillAndUser(@Param("id") int id);


}
