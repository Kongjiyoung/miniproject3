package com.many.miniproject1.offer;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OfferQueryRepository {
    private final EntityManager em;

}
