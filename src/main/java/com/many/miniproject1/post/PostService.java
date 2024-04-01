package com.many.miniproject1.post;


import com.many.miniproject1._core.errors.exception.Exception403;
import com.many.miniproject1._core.errors.exception.Exception404;

import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.skill.SkillResponse;
import com.many.miniproject1.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJPARepository postJPARepository;
    private final SkillJPARepository skillJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    @Transactional
    public void postDelete(int postId, int sessionUserId) {
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception404("공고글을 찾을 수 없습니다"));

        if (sessionUserId != post.getUser().getId()) {
            throw new Exception403("공고글을 삭제할 권한이 없습니다");
        }
        applyJPARepository.deleteByPostId(postId);
        offerJPARepository.deleteByPostId(postId);
        scrapJPARepository.deleteByPostId(postId);
        postJPARepository.deleteById(postId);
        skillJPARepository.deleteSkillsByPostId(postId);
    }

    @Transactional
    public Post save(PostRequest.PostSaveDTO reqDTO, User sessionUser){
        Post post = postJPARepository.save(reqDTO.toEntity(sessionUser));

        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkill()) {
            SkillResponse.PostSaveDTO skill = new SkillResponse.PostSaveDTO();
            skill.setSkill(skillName);
            skill.setPost(post);
            skills.add(skill.toEntity());
        }
        skillJPARepository.saveAll(skills);
        return post;
    }

    public List<PostResponse.PostListDTO> getResumeList(){
        List<Post> postList = postJPARepository.findAllPost();
        return postList.stream().map(post -> new PostResponse.PostListDTO(post)).toList();
    }

    // 공고 상세보기
    public PostResponse.DetailDTO postDetail (int postId, User sessionUser){
        Post post = postJPARepository.findByIdJoinSkillAndCompany(postId)
                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다."));
        return new PostResponse.DetailDTO(post,sessionUser);
    }

    @Transactional
    public Post updatePost(int postId, int sessionUserId, PostRequest.UpdatePostDTO reqDTO) {
        // 1. 이력서 찾기
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));

        // 2. 권한 처리
        if (sessionUserId != post.getUser().getId()) {
            throw new Exception403("공고를 수정할 권한이 없습니다");
        }

        // 3. 이력서 업데이트
        post.setTitle(reqDTO.getTitle());
        post.setCareer(reqDTO.getCareer());
        post.setPay(reqDTO.getPay());
        post.setWorkStartTime(reqDTO.getWorkStartTime());
        post.setWorkEndTime(reqDTO.getWorkEndTime());
        post.setDeadline(reqDTO.getDeadline());
        post.setTask(reqDTO.getTask());
        post.setWorkingArea(reqDTO.getWorkingArea());
        post.setWorkCondition(reqDTO.getWorkCondition());

        // 4. 스킬 업데이트
        List<Skill> skills = skillJPARepository.findSkillsByPostId(post.getId());
        System.out.println(skills);
//        for (Skill skill : skills) {
        skillJPARepository.deleteSkillsByPostId(post.getId());
//        }
        System.out.println(skills);
        List<Skill> skills1 = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillRequest.UpdatePostSkillsDTO skill = new SkillRequest.UpdatePostSkillsDTO();
            skill.setPost(post);
            skill.setSkill(skillName);
            skills1.add(skill.toEntity());
        }
        System.out.println(skills);
        List<Skill> skillList = skillJPARepository.saveAll(skills1);
        return post;
    }

    public Post findByPost(int id) {
        Post post = postJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("해당하는 공고가 없습니다"));
        return post;
    }
}
