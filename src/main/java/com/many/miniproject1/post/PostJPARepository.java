package com.many.miniproject1.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostJPARepository extends JpaRepository<Post, Integer> {

    @Query("""
            select p from Post p join fetch p.skillList s join fetch p.user u where u.id= :id
            """)
    List<Post> findByUserIdJoinSkillAndUser(@Param("id") int id);
}
