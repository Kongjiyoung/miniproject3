package com.many.miniproject1.post;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1._core.errors.exception.Exception403;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.user.SessionUser;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final UserJPARepository userJPARepository;

    //공고 목록보기
    public List<PostResponse.PostListDTO> getResumeList(Integer userId) {
        List<Post> postList = postJPARepository.findByPost(userId);

        return postList.stream().map(PostResponse.PostListDTO::new).toList();
    }

    // 공고 상세보기
    public PostResponse.DetailDTO postDetail(Integer postId) {
        Post post = postJPARepository.findByIdJoinSkillAndCompany(postId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        return new PostResponse.DetailDTO(post);
    }

    //공고 저장
    @Transactional
    public PostResponse.PostDTO save(PostRequest.SavePostDTO reqDTO, SessionUser sessionUser) {
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception401("로그인해주세요."));
        Post post = postJPARepository.save(reqDTO.toEntity(user));

        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillRequest.SavePostDTO skill = new SkillRequest.SavePostDTO();
            skills.add(skill.toEntity(skillName, post));
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);

        return new PostResponse.PostDTO(post, skillList);
    }

    //공고 삭제
    @Transactional
    public void postDelete(Integer postId, Integer sessionUserId) {
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

    //스킬 업데이트
    @Transactional
    public PostResponse.PostUpdateDTO updatePost(Integer postId, Integer sessionUserId, PostRequest.UpdatePostDTO reqDTO) {
        // 1. 이력서 찾기
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));

        // 2. 권한 처리
        if (sessionUserId != post.getUser().getId()) {
            throw new Exception403("공고를 수정할 권한이 없습니다");
        }

        //공고 업데이트
        post.setProfile(ProfileImageSaveUtil.convertToBase64(reqDTO.getProfile(), reqDTO.getProfileName()));
        post.updatePost(reqDTO);

        // 스킬 모두 삭제
        List<Skill> beforeSkill = skillJPARepository.findSkillsByPostId(post.getId());
        for (Skill skill : beforeSkill) {
            skillJPARepository.deleteSkillsByPostId(post.getId());
        }

        //스킬 등록
        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillRequest.SavePostDTO skill = new SkillRequest.SavePostDTO();
            skills.add(skill.toEntity(skillName, post));
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);

        return new PostResponse.PostUpdateDTO(post, skillList);
    }
}
