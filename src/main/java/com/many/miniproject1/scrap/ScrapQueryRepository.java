package com.many.miniproject1.scrap;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScrapQueryRepository {
    private final EntityManager em;

}
