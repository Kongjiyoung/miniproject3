package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public List<User> findAll() {
        Query query = em.createNativeQuery("select * from user_tb", User.class);

        return query.getResultList();
    }

    public User findById(int id) {
        Query query = em.createNativeQuery("select * from user_tb where id=?");
        query.setParameter(1, id);

        User user = (User) query.getSingleResult();

        return user;
    }

    @Transactional
    public void save(UserRequest.JoinDTO requestDTO, int id) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, email, created_at) values(?,?,?, now()))");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void update(UserRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update user_tb set where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from user_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    public User findByEmailAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("select * from user_tb where email=? and password=?", User.class);
        query.setParameter(1, requestDTO.getEmail());
        query.setParameter(2, requestDTO.getPassword());

        try {
           User user = (User) query.getSingleResult();
           return user;
        } catch (Exception e) {
            return null;
        }
    }
}