package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final EntityManager em;

    public User findById(int id){
        User user = em.find(User.class,id);
        return user;
    }
}
