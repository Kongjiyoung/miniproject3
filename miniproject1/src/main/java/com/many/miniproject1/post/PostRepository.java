package com.many.miniproject1.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public List<Post> findAll() {
        Query query = em.createNativeQuery("select * from post_tb order by id desc", Post.class);
        return query.getResultList();
    }

    public Post findById(int id) {
        Query query = em.createNativeQuery("select * from post_tb where id=?");
        query.setParameter(1, id);

        Post post = (Post) query.getSingleResult();

        return post;
    }

    @Transactional
    public void save(PostRequest.SaveDTO requestDTO, int id) {
        Query query = em.createNativeQuery("insert into post_tb() values()");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void update(PostRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update post_tb set where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from post_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}