package com.many.miniproject1.main;

import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.scrap.ScrapJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final PostJPARepository postJPARepository;


    public List<Post> getPostList() {
        List<Post> postList = postJPARepository.findAllPost();
        return postList;
    }

    public Post getPostDetail(int postId) {
        Post post = postJPARepository.findByPostIdJoinUserAndSkill(postId);
        return post;
    }
}
