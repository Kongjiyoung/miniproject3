package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("""
        SELECT a
        FROM Apply a
        JOIN FETCH a.post p
        JOIN FETCH p.skillList s
        WHERE a.resume.user.id = :userId
        """)
    List<Apply> findAllAppliesWithPostsAndSkills(@Param("userId") Integer userId);

}
