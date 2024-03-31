package com.many.miniproject1.offer;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

//@Import(OfferQueryRepository.class)
@DataJpaTest
public class OfferQueryRepositoryTest {
//    @Autowired
//    OfferQueryRepository offerQueryRepository;
    @Autowired
    private EntityManager em;

}
