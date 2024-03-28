package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("""
            select distinct a from Apply a join fetch a.post p join fetch p.user pu join fetch p.skillList join fetch a.resume r join fetch r.user ru where p.id= :postId and ru.id=:resumeUserId
            """)
    Optional<Apply> findByPostIdJoinPostAndSkillAndUser(@Param("postId") Integer postId, @Param("resumeUserId") Integer resumeUserId);
}
