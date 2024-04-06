package com.many.miniproject1.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferJPARepository extends JpaRepository<Offer, Integer> {

    // TODO: Optional 추가

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
        SELECT o
        FROM Offer o
        JOIN FETCH o.post p
        JOIN FETCH p.user u
        JOIN FETCH o.resume r
        WHERE r.user.id =:person_id
        """)
    Optional<List<Offer>> personFindAllOffers(@Param("person_id") int person_id);

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

    // 04-02 YSH
    @Query("""
        SELECT o
        FROM Offer o
        JOIN FETCH o.resume r
        JOIN FETCH r.user u
        JOIN FETCH r.skills s
        WHERE o.id = :offer_id
        """)
    Optional<Offer> companyFindByOfferId(@Param("offer_id") Integer  offer_id);

//    @Query("""
//        DELETE
//        FROM Offer o
//        WHERE o.id = :offer_id
//        """)
//    Offer offerDelete(@Param("offer_id") int offerId);
//
//
//    @Query("""
//         SELECT DISTINCT o
//            FROM Offer o
//            JOIN FETCH o.post p
//            JOIN FETCH p.skillList
//            WHERE o.id = :id
//        """)
//    Offer findByIdWithPostAndSkillList(@Param("id") Integer id);
//
//    @Query("""
//            select o
//            from Offer o
//            join fetch o.resume r
//            join fetch o.post p
//            where p.id = :post_id
//            """)
//    List<Offer> findByPostIdJoinPost(@Param("post_id") int postId);
//
//    @Query("""
//        select o
//        from Offer o
//        join fetch o.resume r
//        join fetch r.user u
//        join fetch r.skills s
//        where o.id = :offer_id
//        """)
//    Offer findByIdJoinResumeAndSkillAndUser(@Param("offer_id") int offerId);

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
