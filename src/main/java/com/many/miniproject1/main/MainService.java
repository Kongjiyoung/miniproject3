package com.many.miniproject1.main;

import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainService {
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final PostJPARepository postJPARepository;

    public Post personPostScrap(Integer postId){
        Post post = postJPARepository.findById(postId)
                .orElseThrow(()->new Exception404("공고를 찾을 수 없습니다."));
        return post;
    }
}
