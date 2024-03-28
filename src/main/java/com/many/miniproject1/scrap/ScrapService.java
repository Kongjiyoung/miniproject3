package com.many.miniproject1.scrap;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    private final ScrapQueryRepository scrapQueryRepository;

    public void deleteScrap(Integer id) {
        scrapJPARepository.deleteById(id);
    }
}
