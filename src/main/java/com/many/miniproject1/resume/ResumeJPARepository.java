package com.many.miniproject1.resume;

import com.many.miniproject1.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

}