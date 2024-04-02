package com.many.miniproject1.scrap;


import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.apply.ApplyRequest;
import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.offer.OfferResponse;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.post.PostRequest;
import com.many.miniproject1.post.PostResponse;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final PostJPARepository postJPARepository;
    private final OfferJPARepository offerJPARepository;

    public List<Scrap> personScrapForm (Integer userId){
        return scrapJPARepository.findByPostIdJoinSkills(userId);
    }

    @Transactional
    public Apply saveApply(int id, int  resumeChoice){
       Scrap scrap =scrapJPARepository.findById(id).orElseThrow(() -> new Exception404(""));
       Resume resume = resumeJPARepository.findById(resumeChoice).orElseThrow(()-> new Exception404(""));
       ApplyRequest.SaveDTO saveApply=new ApplyRequest.SaveDTO(resume, scrap.getPost());
       Apply apply=applyJPARepository.save(saveApply.toEntity());
       return apply;
    }

    @Transactional
    public void deleteScrapPost(int id){
        scrapJPARepository.deleteById(id);

    }
    public void deleteScrap(Integer id) {
        scrapJPARepository.deleteById(id);
    }

    public ScrapResponse.ScrapResumeDetailDTO getResumeDetail(Integer userId){
        Scrap scrap = scrapJPARepository.findByResumeIdAndSkillAndUser(userId);
        return new ScrapResponse.ScrapResumeDetailDTO(scrap);
    }

    public Scrap findById(int id) {
        Scrap scrap = scrapJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return scrap;
    }

    public List<ScrapResponse.ScrapPostListDTO> personScrapList(Integer userId){
        List<Scrap> scrapList = scrapJPARepository.findByPostIdJoinSkills(userId);
        return scrapList.stream().map(scrap -> new ScrapResponse.ScrapPostListDTO(scrap)).toList();
    }

    public List<ScrapResponse.ScrapResumeListDTO> companyScrapList(Integer userId){
        List<Scrap> scrapList = scrapJPARepository.findByUserIdJoinSkillAndResume(userId);
        return scrapList.stream().map(scrap -> new ScrapResponse.ScrapResumeListDTO(scrap)).toList();
    }

    public List<Post> companyPostList(int id) {
        return postJPARepository.findByPostId(id);
    }

    @Transactional
    public OfferResponse.ChoiceDTO sendPostToResume(Integer resumeId, Integer postChoice){
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404(""));
        Post post = postJPARepository.findById(postChoice)
                .orElseThrow(() -> new Exception404("존재하지 않는 공고입니다!" + postChoice));
        OfferRequest.ScrapOfferDTO scrapOfferDTO = new OfferRequest.ScrapOfferDTO(resume, post);
        Offer offer = offerJPARepository.save(scrapOfferDTO.toEntity());

        return new OfferResponse.ChoiceDTO(offer);
    }

    public Scrap getScrapPostDetail(Integer scrapId) {
        Scrap scrap = scrapJPARepository.findByScrapIdJoinPostAndSkill(scrapId);
        return scrap;
    }
}
