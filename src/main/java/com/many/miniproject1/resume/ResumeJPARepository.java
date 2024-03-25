package com.many.miniproject1.resume;

import com.many.miniproject1.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r join fetch r.user u where u.id = :userId")
    List<Object[]> findByUserIdJoinUser(@Param("userId") int userId);
}
