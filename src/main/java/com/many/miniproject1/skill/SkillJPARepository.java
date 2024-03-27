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

}
