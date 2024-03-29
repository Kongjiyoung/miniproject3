package com.many.miniproject1.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferJPARepository extends JpaRepository<Offer, Integer> {


//    @Modifying
//    @Query("delete from Offer o where o.post.user.id = :post_user_id and o.resume.id= :resume_id")
//    void deleteOfferByPostId(@Param("post_user_id") Integer postCompanyId, @Param("resume_id") Integer resumeId);

    @Query("""
            select o
            from Offer o
            join fetch o.resume r
            join fetch o.post p
            join fetch r.user ru
            join fetch p.user pu
            where pu.id = :user_id
            """)
    List<Offer> findByUserId(@Param("user_id") int userId);



    @Query("""
         SELECT DISTINCT o
            FROM Offer o
            JOIN FETCH o.post p
            JOIN FETCH p.skillList
            WHERE o.id = :id
        """)
    Offer findByPostId(@Param("id") Integer id);

    @Query("""
            select o
            from Offer o
            join fetch o.resume r
            join fetch o.post p
            where p.id = :post_id
            """)
    List<Offer> findByPostIdJoinPost(@Param("post_id") int postId);

    @Query("""
        select o
        from Offer o
        join fetch o.resume r
        join fetch r.user u
        join fetch r.skillList s
        where o.id = :offer_id
        """)
    Offer findByIdJoinResumeAndSkillAndUser(@Param("offer_id") int offerId);

    @Modifying
    @Query("""
            delete from Offer o 
            where o.resume.id = :resume_id
                    """)
    void deleteByResumeId(@Param("resume_id") Integer resumeId);
}
