package com.many.miniproject1.post;


import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception403;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.skill.SkillResponse;
import com.many.miniproject1.user.SessionUser;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJPARepository postJPARepository;
    private final SkillJPARepository skillJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final UserJPARepository userJPARepository;

    @Transactional
    public void deletePost(Integer postId, Integer sessionUserId) {
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
    public PostResponse.SavePostDTO save(PostRequest.PostSaveDTO reqDTO, SessionUser sessionUser) {
        User user=userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception403("권한없음"));
        Post post = postJPARepository.save(reqDTO.toEntity(user));

        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillResponse.PostSaveDTO skill = new SkillResponse.PostSaveDTO();
            skill.setSkill(skillName);
            skill.setPost(post);
            skills.add(skill.toEntity());
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);
        return new PostResponse.SavePostDTO(post, skillList);
    }

    public List<PostResponse.CompanyPostsDTO> getResumeList(Integer userId) {
        List<Post> postList = postJPARepository.findByPost(userId);
        return postList.stream().map(PostResponse.CompanyPostsDTO::new).toList();
    }

    // 공고 상세보기
    public PostResponse.CompanyPostDetailDTO postDetail (Integer postId, SessionUser sessionUser){
        Post post = postJPARepository.findByIdJoinSkillAndCompany(postId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        return new PostResponse.CompanyPostDetailDTO(post, sessionUser);
    }

    @Transactional
    public PostResponse.PostUpdateDTO updatePost(Integer postId, Integer sessionUserId, PostRequest.UpdatePostDTO reqDTO) {
        // 1. 이력서 찾기
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));

        // 2. 권한 처리
        if (sessionUserId != post.getUser().getId()) {
            throw new Exception403("공고를 수정할 권한이 없습니다");
        }

        if (reqDTO.getTitle() != null) {
            post.setTitle(reqDTO.getTitle());
        }
        // 3. 이력서 업데이트
        if (reqDTO.getProfile() != null) {
            String encodedImageData = reqDTO.getProfile();
            byte[] decodedBytes = Base64.getDecoder().decode(encodedImageData);
            String profilename= UUID.nameUUIDFromBytes(decodedBytes).randomUUID()+"_" + reqDTO.getProfileName();
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
            post.setProfile(profilename);
        }

        if (reqDTO.getProfileName() != null) {
            post.setProfileName(reqDTO.getProfileName());
        }

        if (reqDTO.getPay() != null) {
            post.setPay(reqDTO.getPay());
        }
        if (reqDTO.getWorkStartTime() != null) {
            post.setWorkStartTime(reqDTO.getWorkStartTime());
        }
        if (reqDTO.getWorkEndTime() != null) {
            post.setWorkEndTime(reqDTO.getWorkEndTime());
        }
        if (reqDTO.getDeadline() != null) {
            post.setDeadline(reqDTO.getDeadline());
        }
        if (reqDTO.getTask() != null) {
            post.setTask(reqDTO.getTask());
        }
        if (reqDTO.getWorkingArea() != null) {
            post.setWorkingArea(reqDTO.getWorkingArea());
        }
        if (reqDTO.getWorkCondition() != null) {
            post.setWorkCondition(reqDTO.getWorkCondition());
        }
        // 4. 스킬 업데이트
        List<Skill> beforeSkill = skillJPARepository.findSkillsByPostId(post.getId());
        for (Skill skill : beforeSkill) {
            skillJPARepository.deleteSkillsByPostId(post.getId());
        }
        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillRequest.UpdatePostSkillsDTO skill = new SkillRequest.UpdatePostSkillsDTO();
            skill.setPost(post);
            skill.setSkill(skillName);
            skills.add(skill.toEntity());
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);
        return new PostResponse.PostUpdateDTO(post, skillList);
    }

    public Post findByPost(int id) {
        return postJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("해당하는 공고가 없습니다"));
    }
}