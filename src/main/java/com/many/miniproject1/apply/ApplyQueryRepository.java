package com.many.miniproject1.apply;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplyQueryRepository {
    private final EntityManager em;
}
