package com.many.miniproject1.offer;



import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class OfferJPARepositoryTest {
    @Autowired
    private OfferJPARepository offerJPARepository;
    @Autowired
    private EntityManager em;

}
