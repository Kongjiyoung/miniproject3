package com.many.miniproject1.main;

import com.many.miniproject1.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainRepository {
    private final EntityManager em;


}
