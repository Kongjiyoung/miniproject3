package com.many.miniproject1.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferJPARepository extends JpaRepository<Offer, Integer> {
    //TODO : 삭제 예정
//    @Modifying
//    @Query("delete from Offer o where o.post.user.id = :post_user_id and o.resume.id= :resume_id")
//    void deleteOfferByPostId(@Param("post_user_id") Integer postCompanyId, @Param("resume_id") Integer resumeId);

    // 04-01 YSH
    @Query("""
            SELECT o
            FROM Offer o
            JOIN FETCH o.resume r
            JOIN FETCH r.user u
            JOIN FETCH r.skills s
            JOIN FETCH o.post p
            WHERE p.user.id = :company_id
            """)
    Optional<List<Offer>> companyFindAllOffers(@Param("company_id") Integer companyId);

    @Query("""
        select o
        from Offer o
        join fetch o.post p
        join fetch p.user pu
        join fetch o.resume r
        join fetch r.user ru
        join fetch p.skillList s
        where ru.id =:person_id
        """)
    Optional<List<Offer>> personFindAllOffers(@Param("person_id") Integer personId);

    @Query("""
        SELECT o
        FROM Offer o
        JOIN FETCH o.post p
        JOIN FETCH p.user u
        JOIN FETCH o.resume r
        JOIN FETCH p.skillList s
        WHERE o.id =:offer_id
        """)
    Optional<Offer> personFindByOfferId(@Param("offer_id") Integer  offer_id);

    @Query("""
        SELECT o
        FROM Offer o
        JOIN FETCH o.resume r
        JOIN FETCH r.user u
        JOIN FETCH r.skills s
        WHERE o.id = :offer_id
        """)
    Optional<Offer> companyFindByOfferId(@Param("offer_id") Integer  offer_id);

    @Modifying
    @Query("""
            delete from Offer o 
            where o.resume.id = :resume_id
                    """)
    void deleteByResumeId(@Param("resume_id") Integer resumeId);

    @Modifying
    @Query("""
            delete from Offer o
            where o.post.id = :post_id
                    """)
    void deleteByPostId(@Param("post_id") Integer postId);


}
