package com.many.miniproject1.scrap;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    private final ScrapQueryRepository scrapQueryRepository;

    @Transactional
    public void deleteScrapPost(int sessionUserId,int id){
        scrapJPARepository.deleteScrapByPostId(id,sessionUserId);
    }
}
