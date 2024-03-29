package com.many.miniproject1.scrap;


import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.apply.ApplyRequest;
import com.many.miniproject1.resume.Resume;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    private final ScrapQueryRepository scrapQueryRepository;
    private final ApplyJPARepository applyJPARepository;

    public List<Scrap> personScrapForm (Integer userId){
        return scrapJPARepository.findByPostIdJoinskills(userId);
    }

    @Transactional
    public Apply saveApply(int id, Resume resume){
       Scrap scrap =scrapJPARepository.findById(id).orElseThrow(() -> new Exception401(""));
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
}
