package com.many.miniproject1.main;

import com.many.miniproject1.apply.ApplyRepository;
import com.many.miniproject1.apply.ApplyRequest;
import com.many.miniproject1.offer.OfferRepository;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.offer.OfferResponse;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostRepository;
import com.many.miniproject1.post.PostResponse;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeRepository;
import com.many.miniproject1.resume.ResumeResponse;
import com.many.miniproject1.skill.SkillRepository;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainRepository mainRepository;
    private final ResumeRepository resumeRepository;
    private final PostRepository postRepository;
    private final SkillRepository skillRepository;
    private final HttpSession session;
    private final OfferRepository offerRepository;
    private final ApplyRepository applyRepository;

    @GetMapping("/")
    public String indexPost(HttpServletRequest request){
        List<Post> postList=postRepository.findAll();
        System.out.println(postList.size());


        ArrayList<MainResponse.postDTO> postSkillList=new ArrayList<>();
        for(int i =0 ; i<postList.size(); i++){
            List<String> skills=skillRepository.findByPostId(postList.get(i).getId());
            System.out.println(skills);
            Post post=(postList.get(i));
            System.out.println(post);
            postSkillList.add(new MainResponse.postDTO(post,skills));
            System.out.println(postSkillList.get(i));
        }

        request.setAttribute("postSkillList", postSkillList);
        return "indexpost";
    }

    //메인 구직 공고
    @GetMapping("/company/main")
    public String resumeForm(HttpServletRequest request) {
        List<Resume> resumeList=resumeRepository.findAll();
        System.out.println(resumeList.size());


        ArrayList<MainResponse.resumeDTO> resumeSkillList=new ArrayList<>();
        for(int i =0 ; i<resumeList.size(); i++){
            List<String> skills=skillRepository.findByResumeId(resumeList.get(i).getId());
            System.out.println(skills);
            Resume resume=(Resume)resumeList.get(i);
            System.out.println(resume);
            resumeSkillList.add(new MainResponse.resumeDTO(resume,skills));
            System.out.println(resumeSkillList.get(i));
        }
        request.setAttribute("resumeSkillList", resumeSkillList);

        //기업인지 개인인지 구분
        User sessionUser=(User) session.getAttribute("sessionUser");
        String role=sessionUser.getRole();
        System.out.println(role);
        Boolean isCompany=false;
        if (role.equals("company")){
            isCompany=true;
        }
        request.setAttribute("isMatchingCompany", isCompany);
        return "company/main";
    }

    @GetMapping("/resume")
    public String indexResume(HttpServletRequest request){
        List<Resume> resumeList=resumeRepository.findAll();
        System.out.println(resumeList.size());


        ArrayList<MainResponse.resumeDTO> resumeSkillList=new ArrayList<>();
        for(int i =0 ; i<resumeList.size(); i++){
            List<String> skills=skillRepository.findByResumeId(resumeList.get(i).getId());
            System.out.println(skills);
            Resume resume=(Resume)resumeList.get(i);
            System.out.println(resume);
            resumeSkillList.add(new MainResponse.resumeDTO(resume,skills));
            System.out.println(resumeSkillList.get(i));
        }
        request.setAttribute("resumeSkillList", resumeSkillList);
        return "indexresume";
    }



    @GetMapping("/resume/detail/{id}")
    public String resumeDetailForm(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id: "+id);
        //내용작성
        // DTO에 ArrayList는 초기화 해두어도 된다.
        ResumeResponse.DetailDTO detailDTO = resumeRepository.findById(id);
        List<String> skills = skillRepository.findByResumeId(id);
        detailDTO.setSkill(skills);

        //이력서 선택
        User sessionUser=(User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer companyId=sessionUser.getId();
        System.out.println(companyId);
        List<Post> postList=mainRepository.findPost(companyId);
        System.out.println(postList);


        request.setAttribute("postList", postList);
        request.setAttribute("resume", detailDTO);
        return "company/resumeDetail";
    }

    @PostMapping("/resume/detail/{id}/offer")
    public String companyResumeOffer(@PathVariable int id, @RequestParam("postId") Integer postId) {
        System.out.println("======================"+postId);
        User sessionUser=(User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer companyId=sessionUser.getId();
        System.out.println(companyId);

        OfferRequest.SaveDTO saveDTO=new OfferRequest.SaveDTO();
        saveDTO.setResumeId(id);
        saveDTO.setPostId(postId);
        saveDTO.setCompanyId(companyId);
        saveDTO.setPersonId(mainRepository.findPersonId(id));


        System.out.println(saveDTO);
        offerRepository.save(saveDTO, id);

        return "redirect:/resume/detail/{id}";
    }
    @PostMapping("/resume/detail/{id}/scrap")
    public String companyResumeScrap() {

        return "redirect:/resume/detail/{id}";
    }
    //메인 채용 공고
    @GetMapping("/person/main")
    public String postForm(HttpServletRequest request) {
        List<Post> postList=postRepository.findAll();
        System.out.println(postList.size());


        ArrayList<MainResponse.postDTO> postSkillList=new ArrayList<>();
        for(int i =0 ; i<postList.size(); i++){
            List<String> skills=skillRepository.findByPostId(postList.get(i).getId());
            System.out.println(skills);
            Post post=(postList.get(i));
            System.out.println(post);
            postSkillList.add(new MainResponse.postDTO(post,skills));
            System.out.println(postSkillList.get(i));
        }
        request.setAttribute("postSkillList", postSkillList);
        //기업인지 개인인지 구분
        User sessionUser=(User) session.getAttribute("sessionUser");
        String role=sessionUser.getRole();
        System.out.println(role);
        Boolean isCompany=false;
        if (role.equals("company")){
            isCompany=true;
        }
        request.setAttribute("isMatchingCompany", isCompany);
        return "person/main";
    }



    @GetMapping("/post/detail/{id}")
    public String postDetailForm(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id: "+id);

        //내용작성
        PostResponse.DetailDTO detailDTO = postRepository.findById(id);
        List<String> skills = skillRepository.findByResumeId(id);

        detailDTO.setSkill(skills);

        //이력서 선택
        User sessionUser=(User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer personId=sessionUser.getId();
        System.out.println(personId);
        List<Resume> resumeList=mainRepository.findResume(personId);
        System.out.println(resumeList);


        request.setAttribute("resumeList", resumeList);
        request.setAttribute("post", detailDTO);
        return "person/postDetail";
    }
    @PostMapping("/post/detail/{id}/apply")
    public String personPostApply(@PathVariable int id, @RequestParam("resumeId") Integer resumeId) {
        System.out.println("======================"+resumeId);
        User sessionUser=(User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer personId=sessionUser.getId();
        System.out.println(personId);

        ApplyRequest.SaveDTO saveDTO=new ApplyRequest.SaveDTO();
        saveDTO.setResumeId(resumeId);
        saveDTO.setPostId(id);
        saveDTO.setCompanyId(postRepository.findCompanyId(id));
        saveDTO.setPersonId(personId);
        saveDTO.setIsPass("검토중");


        System.out.println("save : "+saveDTO);
        applyRepository.save(saveDTO);

        return "redirect:/post/detail/{id}";
    }
    @PostMapping("/post/detail/{id}/scrap")
    public String personPostScrap() {
        return "redirect:/post/detail/{id}";
    }
    //맞춤 공고 - 기업이 보는 매칭 이력서
    @GetMapping("/company/matching")
    public String matchingResumeForm(HttpServletRequest request) {


        //Boolean isCompany
        return "person/matching";
    }

    @GetMapping("/matching/resume/detail/{id}")
    public String matchingResumeDetailForm(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id: "+id);

        ResumeResponse.DetailDTO detailDTO = resumeRepository.findById(id);
        List<String> skills = skillRepository.findByResumeId(id);

        detailDTO.setSkill(skills);

        request.setAttribute("resume", detailDTO);
        return "person/resumeDetail";
    }
    @PostMapping("/matching/resume/detail/{id}/offer")
    public String matchingCompanyResumeOffer() {
        return "redirect:/matching/resume/detail/{id}";
    }
    @PostMapping("/matching/resume/detail/{id}/scrap")
    public String matchingCompanyResumeScrap() {
        return "redirect:/matching/resume/detail/{id}";
    }
    //맞춤 공고 - 개인이 보는 매칭 공고
    @GetMapping ("/person/matching")
    public String matchingPostForm(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer userId=sessionUser.getId();
        List<Post> postList=mainRepository.findPost(userId);
        request.setAttribute("postList", postList);


        return "company/matching";
    }

    @GetMapping("/matching/post/detail/{id}")
    public String matchingPostDetailForm(@PathVariable int id, HttpServletRequest request) {

        PostResponse.DetailDTO detailDTO = postRepository.findById(id);
        List<String> skills = skillRepository.findByResumeId(id);

        detailDTO.setSkill(skills);

        request.setAttribute("post", detailDTO);
        return "company/postDetail";
    }
    @PostMapping("/matching/post/detail/{id}/apply")
    public String matchingPersonPostapply() {
        return "/matching/post/detail/{id}";
    }
    @PostMapping("/matching/post/detail/{id}/scrap")
    public String matchingPersonPostScrap() {
        return "redirect:/matching/post/detail/{id}";
    }
}