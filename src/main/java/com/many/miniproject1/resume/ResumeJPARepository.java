package com.many.miniproject1.resume;

import com.many.miniproject1.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {
//    @Query("""
//            select r, s
//            from Resume r
//            join fetch r.skillList s
//            where r.id = :id
//            """)
////    @Query("""
////            select r
////            from Resume r
////            where r.id = :id
////            """)
//    Optional<Resume> findByIdJoinSkill(@Param("id") int id);


}
