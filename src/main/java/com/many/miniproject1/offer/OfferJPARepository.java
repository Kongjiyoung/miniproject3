package com.many.miniproject1.offer;

import com.many.miniproject1.apply.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OfferJPARepository extends JpaRepository<Offer, Integer> {

    @Modifying
    @Query("delete from Offer o where o.post.user.id = :post_user_id and o.resume.id= :resume_id")
    void deleteOfferByPostId(@Param("post_user_id") Integer postCompanyId, @Param("resume_id") Integer resumeId);
}
