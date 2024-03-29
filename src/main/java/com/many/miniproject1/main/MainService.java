package com.many.miniproject1.main;

import com.many.miniproject1._core.errors.exception.Exception401;

import com.many.miniproject1.apply.Apply;

import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.apply.ApplyRequest;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.resume.ResumeResponse;
import com.many.miniproject1.scrap.Scrap;

import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.scrap.ScrapRequest;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserService;
import com.sun.tools.javac.Main;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final PostJPARepository postJPARepository;
    private final SkillJPARepository skillJPARepository;
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

    public List<Resume> getResumeId(int id) {
        List<Resume> resumeList = resumeJPARepository.findByUserId(id);
        return resumeList;
    }
  
    public List<Post> findByUserIdPost(int userId){
        List<Post> postList=postJPARepository.findByUserIdJoinSkillAndUser(userId);
        return postList;
    }

    public List<Resume> matchingResume(int postchoice) {
        //매칭할 공고 스킬 가져와 리스트에 담기
        List<Skill> postSkills = skillJPARepository.findSkillsByPostId(postchoice);
        List<String> postSkill = postSkills.stream().map(skill -> skill.getSkill()).toList();
        System.out.println(postSkill);

        //전체 이력서 새로운 이력서점수리스트에 담기, 점수는 0으로 시작
        List<MainResponse.ResumeSkillDTO> resumeSkillScore = new ArrayList<>();
        for (int i = 0; i < resumeJPARepository.findAll().size(); i++) {
            int resumeId = resumeJPARepository.findAll().get(i).getId();
            resumeSkillScore.add(new MainResponse.ResumeSkillDTO(resumeId, 0));
            System.out.println("추가" + resumeSkillScore);
        }
        System.out.println(resumeSkillScore);

        //공고스킬만큼 반복문 돌리기
        for (int i = 0; i < postSkill.size(); i++) {
            System.out.println(i);
            System.out.println("공고스킬 : " + postSkill.get(i));
            //모든 스킬테이블에서 비교하기위해 반복문 돌리기
            for (int j = 0; j < skillJPARepository.findAll().size(); j++) {
                System.out.println(j);
                System.out.println("스킬테이블 : " + j);
                if (skillJPARepository.findAll().get(j).getResume() == null) {
                    break;
                }
                //스킬테이블과 공고스킬 비교하기
                if (postSkill.get(i).equals(skillJPARepository.findSkillsByResume().get(j).getSkill())) {
                    System.out.println("같은 스킬 이력서 아이디 : " + skillJPARepository.findSkillsByResume().get(j).getResume().getId());
                    //스킬테이블에서 같은 스킬 찾아서 거기 이력서아이디 가져오기
                    int resumeId = skillJPARepository.findSkillsByResume().get(j).getResume().getId();
                    //이력서점수리스트 만큼 반복문 돌리기
                    for (int k = 0; k < resumeSkillScore.size(); k++) {
                        System.out.println(k);
                        //이력서점수리스트의 이력서아이디와 스킬테이블 이력서 아이디와 같으면 이력서 점수리스트에 해당하는 점수 1점 올리기
                        if (resumeSkillScore.get(k).resumeId == resumeId) {
                            System.out.println(resumeSkillScore.get(k));
                            //이력서점수 1점 추가하기
                            resumeSkillScore.get(k).setScore(resumeSkillScore.get(k).getScore() + 1);
                            ;
                            System.out.println(resumeSkillScore);
                            break;
                        }
                    }
                }
            }

        }
        //2점이상 이력서아이디만 가져와 리스트 만들기
        ArrayList<Integer> finalResumeSkillScore = new ArrayList<>();
        for (int i = 0; i < resumeSkillScore.size(); i++) {
            if (resumeSkillScore.get(i).getScore() > 1) {
                int two = resumeSkillScore.get(i).getResumeId();
                finalResumeSkillScore.add(two);
            }
        }
        System.out.println("2점이상 이력서 아이디" + finalResumeSkillScore);

        Collections.sort(finalResumeSkillScore, Collections.reverseOrder());
        System.out.println("2점이상 이력서 아이디 정렬 : " + finalResumeSkillScore);

        List<Resume> matchingResumeList = new ArrayList<>();

        for (int i = 0; i < finalResumeSkillScore.size(); i++) {
            int resumeId = finalResumeSkillScore.get(i);
            matchingResumeList.add(resumeJPARepository.findById(resumeId).orElseThrow(() -> new Exception401("이력서없음")));
        }
        return matchingResumeList;
    }


    public List<Post> getPostList() {
        List<Post> postList = postJPARepository.findAllPost();
        return postList;
    }

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
  
    public Post getPostDetail(int postId) {
        Post post = postJPARepository.findByPostIdJoinUserAndSkill(postId);
        return post;
    }
}
