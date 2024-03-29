package com.many.miniproject1.main;

import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.apply.ApplyController;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.apply.ApplyRequest;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.scrap.Scrap;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.scrap.ScrapRequest;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserService;
import com.sun.tools.javac.Main;
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
    private final UserService userService;

    public Scrap personPostScrap(Integer userId, Integer postId){
        User user = userService.findByUser(userId);
        Post post = postJPARepository.findById(postId)
                .orElseThrow(()->new Exception401("공고를 찾을 수 없습니다."));
        ScrapRequest.SavePostDTO saveScrap= new ScrapRequest.SavePostDTO(user, post);
        Scrap scrap = scrapJPARepository.save(saveScrap.toEntity());
        return scrap;
    }
    public Apply personPostApply(Integer postId, Integer resumeId){
        Post post = postJPARepository.findById(postId)
                .orElseThrow(()->new Exception401("공고를 찾을 수 없습니다."));
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(()->new Exception401(""));
        ApplyRequest.SaveDTO saveApply = new ApplyRequest.SaveDTO(resume,post);
        Apply apply = applyJPARepository.save(saveApply.toEntity());
        return apply;
    }

    public List<Resume> personResumeList(Integer id){
        List<Resume> resumeList = resumeJPARepository.findByUserId(id);
        return resumeList;
    }
}
