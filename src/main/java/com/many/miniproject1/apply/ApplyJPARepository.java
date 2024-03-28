package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("""
            select distinct a from Apply a join fetch a.post p join fetch p.user u join fetch p.skillList s where p.id= :id
            """)
    Optional<Apply> findByPostIdJoinPostAndSkillAndUser(@Param("id") int id);
}
