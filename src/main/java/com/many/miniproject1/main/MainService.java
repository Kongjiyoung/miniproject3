package com.many.miniproject1.main;

import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.resume.ResumeResponse;
import com.many.miniproject1.scrap.ScrapJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

    public List<Resume> resumeForm(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return resumeJPARepository.findAll(sort);
    }

    public Resume resumeDetailForm(Integer resumeId){
        return resumeJPARepository.findById(resumeId).orElse(null);
    }

    public List<Post> getPostsByCompanyId(Integer companyId) {
        return postJPARepository.findByUserIdJoinSkillAndUser(companyId);
    }
}
