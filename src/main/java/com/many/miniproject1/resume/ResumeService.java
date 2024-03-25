package com.many.miniproject1.resume;

import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;
    private final SkillJPARepository skillJPARepository;
    private final UserJPARepository userJPARepository;

    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        Resume resume = resumeJPARepository.save(requestDTO.toEntity());
        //Skill skill = skillJPARepository.saveAll()
        return resume;
    }

    @Transactional
    public Resume update(int id, ResumeRequest.UpdateDTO requestDTO) {

        Resume resume = resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        resume.setTitle(requestDTO.getTitle());
        resume.setPortfolio(requestDTO.getPortfolio());
        resume.setIntroduce(requestDTO.getIntroduce());
        resume.setCareer(requestDTO.getCareer());
        resume.setSimpleIntroduce(requestDTO.getSimpleIntroduce());

        // 사용자 ID를 사용하여 사용자 정보를 조회하고, Resume 엔티티에 설정
        User user = userJPARepository.findById(requestDTO.getPersonId())
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        resume.setUser(user);

        MultipartFile profile = requestDTO.getProfile();
        String profileFilename = UUID.randomUUID() + "_" + profile.getOriginalFilename();
        Path profilePath = Paths.get("./images/" + profileFilename);
        try {
            Files.write(profilePath, profile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resumeJPARepository.save(resume);
    }

    public Resume findByUser(int id){
        Resume resume = resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return resume;
    }
}