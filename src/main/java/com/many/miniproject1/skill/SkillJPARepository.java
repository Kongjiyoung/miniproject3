package com.many.miniproject1.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillJPARepository extends JpaRepository<Skill, Integer> {

    @Query("SELECT s FROM Skill s WHERE s.resume.id = :resumeId")
    List<Skill> findSkillsByResumeId(@Param("resumeId") Integer resumeId);

    @Modifying
    @Query("DELETE FROM Skill s WHERE s.resume.id = :resumeId")
    void deleteSkillsByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select s from Skill s where s.post.id=:postId")
    List<Skill> findSkillsByPostId(@Param("postId") Integer postId);

    @Modifying
    @Query("delete from Skill s where s.post.id = :postId")
    void deleteSkillsByPostId(@Param("postId") Integer postId);

    @Query("select s from Skill s WHERE s.resume IS NOT NULL")
    List<Skill> findSkillsByResume();

    @Query("select s from Skill s WHERE s.post IS NOT NULL")
    List<Skill> findSkillsByPost();
}
