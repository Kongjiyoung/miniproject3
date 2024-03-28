package com.many.miniproject1.scrap;

import com.many.miniproject1.offer.OfferQueryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(ScrapQueryRepository.class)
@DataJpaTest
public class ScrapQueryRepositoryTest {
    @Autowired
    ScrapQueryRepository scrapQueryRepository;
    @Autowired
    private EntityManager em;

}
