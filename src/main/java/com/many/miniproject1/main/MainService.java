package com.many.miniproject1.main;

import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.apply.ApplyRequest;
import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.scrap.Scrap;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.scrap.ScrapRequest;
import com.many.miniproject1.scrap.ScrapResponse;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import com.many.miniproject1.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MainService {
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final PostJPARepository postJPARepository;
    private final UserJPARepository userJPARepository;
    private final SkillJPARepository skillJPARepository;
    private final UserService userService;

    // 04-02 YSH
    public List<MainResponse.MainResumesDTO> mainResumes() {
        List<Resume> mainResumes = resumeJPARepository.mainAllResume();

        return mainResumes.stream().map(resume -> new MainResponse.MainResumesDTO(resume)).toList();
    }

    @Transactional
    public ScrapResponse.MainResumeScrapDTO resumeScrap(int resumeId, int userId) {
        User user = userService.findByUser(userId);
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception401(""));
        ScrapRequest.ScrapResumeDTO saveScrap = new ScrapRequest.ScrapResumeDTO(resume, user);
        Scrap scrap = scrapJPARepository.save(saveScrap.toEntity());

        return new ScrapResponse.MainResumeScrapDTO(scrap);
    }

    @Transactional
    public ScrapResponse.PostScrapSaveDTO personPostScrap(Integer userId, Integer postId) {
        User user = userService.findByUser(userId);
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception401("공고를 찾을 수 없습니다."));
        ScrapRequest.SavePostDTO saveScrap = new ScrapRequest.SavePostDTO(user, post);
        Scrap scrap = scrapJPARepository.save(saveScrap.toEntity());
        return new ScrapResponse.PostScrapSaveDTO(scrap);
    }

    public ApplyResponse.PostApplyDTO personPostApply(int postId, int resumeId) {
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception401("공고를 찾을 수 없습니다."));
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception401(""));
        ApplyRequest.SaveDTO saveApply = new ApplyRequest.SaveDTO(resume, post);
        Apply apply = applyJPARepository.save(saveApply.toEntity());

        return new ApplyResponse.PostApplyDTO(apply);
    }

    public List<MainResponse.PostMatchingChoiceDTO> findByUserIdPost(int userId) {
        List<Post> postList = postJPARepository.findByUserIdJoinSkillAndUser(userId);
        return postList.stream().map(post -> new MainResponse.PostMatchingChoiceDTO(post)).toList();
    }

    public List<MainResponse.ResumeMatchingChoiceDTO> findByUserIdResume(int userId) {
        List<Resume> resumeList = resumeJPARepository.findByUserIdJoinSkillAndUser(userId);
        return resumeList.stream().map(resume -> new MainResponse.ResumeMatchingChoiceDTO(resume)).toList();
    }


    public List<MainResponse.MainPostsDTO> getPostList() {
        List<Post> postList = postJPARepository.findAllPost();
        return postList.stream().map(post -> new MainResponse.MainPostsDTO(post)).toList();
    }


    public MainResponse.PostDetailDTO getPostIsCompanyDetail(int postId, int userId, Boolean isCompany) {
        Post post = postJPARepository.findByPostIdJoinUserAndSkill(postId).orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));
        List<Resume> resumes=resumeJPARepository.findAllResume(userId);

        return new MainResponse.PostDetailDTO(post, resumes, isCompany);
    }

    public MainResponse.PostDetailDTO getPostDetail(int postId, Boolean isCompany) {
        Post post = postJPARepository.findByPostIdJoinUserAndSkill(postId).orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));
        return new MainResponse.PostDetailDTO(post, isCompany);
    }

    @Transactional
    public OfferRequest.OfferSaveDTO sendPostToResume(Integer resumeId, Integer postId) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("존재하지 않는 이력서입니다."));
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception404("존재하지 않는 공고입니다."));
        //OfferRequest.ScrapOfferDTO scrapOfferDTO = new OfferRequest.ScrapOfferDTO(resume, post);
        Offer offer = offerJPARepository.save(OfferRequest.OfferSaveDTO.toEntity(resume, post));
        return new OfferRequest.OfferSaveDTO(offer);
    }

    @Transactional
    public Scrap companyScrap(int id, Integer userId) {
        Resume resume = resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception401("존재하지 않는 이력서입니다...!" + id));
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new Exception401("띠용~?" + userId));
        ScrapRequest.ScrapResumeDTO mainScrapDTO = new ScrapRequest.ScrapResumeDTO(resume, user);
        Scrap scrap = scrapJPARepository.save(mainScrapDTO.toEntity());
        return scrap;
    }

    public MainResponse.MainResumeMatchDTO matchingPost(Integer resumechoice) {
        //매칭할 공고 스킬 가져와 리스트에 담기
        List<Skill> resumeSkills = skillJPARepository.findSkillsByResumeId(resumechoice);
        List<String> resumeSkill = resumeSkills.stream().map(skill -> skill.getSkill()).toList();

        //전체 이력서 새로운 이력서점수리스트에 담기, 점수는 0으로 시작
        List<MainResponse.PostSkillDTO> postSkillScore = new ArrayList<>();
        for (int i = 0; i < postJPARepository.findAll().size(); i++) {
            int postId = postJPARepository.findAll().get(i).getId();
            postSkillScore.add(new MainResponse.PostSkillDTO(postId, 0));
        }

        //공고스킬만큼 반복문 돌리기
        for (int i = 0; i < resumeSkill.size(); i++) {
            //모든 스킬테이블에서 비교하기위해 반복문 돌리기
            for (int j = 0; j < skillJPARepository.findAll().size(); j++) {
                if (skillJPARepository.findAll().get(j).getPost() != null) {
                    //스킬테이블과 공고스킬 비교하기
                    if (resumeSkill.get(i).equals(skillJPARepository.findAll().get(j).getSkill())) {
                        //스킬테이블에서 같은 스킬 찾아서 거기 이력서아이디 가져오기
                        int postId = skillJPARepository.findAll().get(j).getPost().getId();
                        //이력서점수리스트 만큼 반복문 돌리기
                        for (int k = 0; k < postSkillScore.size(); k++) {
                            //이력서점수리스트의 이력서아이디와 스킬테이블 이력서 아이디와 같으면 이력서 점수리스트에 해당하는 점수 1점 올리기
                            if (postSkillScore.get(k).getPostId() == postId) {
                                //이력서점수 1점 추가하기
                                postSkillScore.get(k).setScore(postSkillScore.get(k).getScore() + 1);
                                break;
                            }
                        }
                    }
                }
            }

        }
        //2점이상 이력서아이디만 가져와 리스트 만들기
        //2점이상 이력서아이디만 가져와 리스트 만들기
        List<MainResponse.PostSkillDTO> filteredList = postSkillScore.stream()
                .filter(dto -> dto.getScore() >= 2)
                .sorted(Comparator.comparing(MainResponse.PostSkillDTO::getScore).reversed())
                .collect(Collectors.toList());

        List<Post> matchingPostList = new ArrayList<>();

        for (int i = 0; i < filteredList.size(); i++) {
            int resumeId = filteredList.get(i).getResumeId();
            matchingResumeList.add(resumeJPARepository.findByIdJoinSkillAndUser(resumeId).orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다.")));
        }

        //선택한 이력서 확인
        Resume resume = resumeJPARepository.findById(resumechoice).orElseThrow(() -> new Exception401("권한이 없습니다"));
        List<MainResponse.MainPostsDTO> mainPostsDTO=matchingPostList.stream().map(post -> new MainResponse.MainPostsDTO(post)).toList();
        return new MainResponse.MainResumeMatchDTO(resume, mainPostsDTO);
    }



    // 나의 생각!! 서비스에서 두 개의 리스트를 만들었는데 이것을 하나의 서비스에 담아서 돌려주자.
    public List<MainResponse.PostTitleListDTO> getPostTitleListDTOs(Integer sessionUserId, Integer companyId) {
        List<Post> postList = postJPARepository.findPostListByCompanyId(sessionUserId, companyId);
        List<MainResponse.PostTitleListDTO> postTitleListDTOList = new ArrayList<>();

        postList.stream().map(post -> {
            return postTitleListDTOList.add(MainResponse.PostTitleListDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .build());
        }).collect(Collectors.toList());

        return postTitleListDTOList;
    }


    public MainResponse.MainResumeDetailDTO getResumeDetail(Integer resumeId) {
        Resume resume = resumeJPARepository.findResumeById(resumeId);

        return new MainResponse.MainResumeDetailDTO(resume, resume.getUser(), resume.getSkills());
    }

    public MainResponse.MainPostMatchDTO matchingResume(Integer postchoice) {
        //매칭할 공고 스킬 가져와 리스트에 담기
        List<Skill> postSkills = skillJPARepository.findSkillsByPostId(postchoice);
        List<String> postSkill = postSkills.stream().map(skill -> skill.getSkill()).toList();

        //전체 이력서 새로운 이력서점수리스트에 담기, 점수는 0으로 시작
        List<MainResponse.ResumeSkillDTO> resumeSkillScore = new ArrayList<>();
        for (int i = 0; i < resumeJPARepository.findAll().size(); i++) {
            int resumeId = resumeJPARepository.findAll().get(i).getId();
            resumeSkillScore.add(new MainResponse.ResumeSkillDTO(resumeId, 0));
        }

        //공고스킬만큼 반복문 돌리기
        for (int i = 0; i < postSkill.size(); i++) {
            //모든 스킬테이블에서 비교하기위해 반복문 돌리기
            for (int j = 0; j < skillJPARepository.findAll().size(); j++) {
                if (skillJPARepository.findAll().get(j).getResume() != null) {
                    //스킬테이블과 공고스킬 비교하기
                    if (postSkill.get(i).equals(skillJPARepository.findAll().get(j).getSkill())) {
                        //스킬테이블에서 같은 스킬 찾아서 거기 이력서아이디 가져오기
                        int resumeId = skillJPARepository.findAll().get(j).getResume().getId();
                        //이력서점수리스트 만큼 반복문 돌리기
                        for (int k = 0; k < resumeSkillScore.size(); k++) {
                            //이력서점수리스트의 이력서아이디와 스킬테이블 이력서 아이디와 같으면 이력서 점수리스트에 해당하는 점수 1점 올리기
                            if (resumeSkillScore.get(k).getResumeId() == resumeId) {
                                //이력서점수 1점 추가하기
                                resumeSkillScore.get(k).setScore(resumeSkillScore.get(k).getScore() + 1);
                                ;
                                break;
                            }
                        }
                    }
                }
            }

        }
        //2점이상 이력서아이디만 가져와 리스트 만들기
        List<MainResponse.ResumeSkillDTO> filteredList = resumeSkillScore.stream()
                .filter(dto -> dto.getScore() >= 2)
                .sorted(Comparator.comparing(MainResponse.ResumeSkillDTO::getScore).reversed())
                .collect(Collectors.toList());

        List<Resume> matchingResumeList = new ArrayList<>();

        for (int i = 0; i < filteredList.size(); i++) {
            int postId = filteredList.get(i).getPostId();
            matchingPostList.add(postJPARepository.findByPostIdJoinUserAndSkill(postId).orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다.")));

        }

        Post post = postJPARepository.findById(postchoice).orElseThrow(() -> new Exception404("권한이 없습니다"));
        List<MainResponse.MainResumesDTO> resumesDTO = matchingResumeList.stream().map(resume -> new MainResponse.MainResumesDTO(resume)).toList();
        return new MainResponse.MainPostMatchDTO(post, resumesDTO);
    }
}
