package com.many.miniproject1.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostJPARepository extends JpaRepository<Post, Integer> {

    @Query("""
            select distinct p from Post p join fetch p.skillList s join fetch p.user u where u.id= :id
            """)
    List<Post> findByUserIdJoinSkillAndUser(@Param("id") int id);

    @Query("""
            SELECT p
            FROM Post p
            JOIN FETCH p.user pu
            WHERE pu.id = :id
            """)
    List<Post> findByPostId(@Param("id") int id);

    // 공고에 스킬 유저(기업) 쪼인 YSH
    @Query("""
            SELECT DISTINCT p
            FROM Post p
            JOIN FETCH p.skillList ps
            JOIN FETCH p.user pu
            WHERE p.id = :id
            """)
    Post findByIdJoinSkillAndCompany(@Param("id") Integer id);

    @Query("""
            select p
            from Post p
            join fetch p.skillList s
            """)
    List<Post> findAllPost();

    @Query("""
            select p
            from Post p
            join p.user u
            join p.skillList s
            where p.id =:post_id
            """)
    Post findByPostIdJoinUserAndSkill(@Param("post_id") Integer postId);

}
