package com.many.miniproject1.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferJPARepository extends JpaRepository<Offer, Integer> {

    @Query("""
            select o
            from Offer o
            join fetch o.resume r
            join fetch r.user u
            where r.user.id = :user_id
            """)
    List<Offer> findByUserId(@Param("user_id") int userId);

    @Modifying
    @Query("delete from Offer o where o.post.user.id = :post_user_id and o.resume.id= :resume_id")
    void deleteOfferByPostId(@Param("post_user_id") Integer postCompanyId, @Param("resume_id") Integer resumeId);

    @Query("""
         SELECT DISTINCT o
            FROM Offer o
            JOIN FETCH o.post p
            JOIN FETCH p.skillList
            WHERE o.id = :id
        """)
    Offer findByPostId(@Param("id") Integer id);
}
