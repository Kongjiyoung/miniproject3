package com.many.miniproject1.offer;

import com.many.miniproject1.apply.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OfferJPARepository extends JpaRepository<Offer, Integer> {


}
