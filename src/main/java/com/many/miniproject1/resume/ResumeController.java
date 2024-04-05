package com.many.miniproject1.resume;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

    // 개인 이력서 목록
    @GetMapping("/api/person/resumes")
    public ResponseEntity<?> personResumes(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ResumeResponse.PersonResumesDTO> respDTO = resumeService.getResumeList(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 개인 이력서 상세
    @GetMapping("/api/person/resumes/{id}")
    public ResponseEntity<?> personResume(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.PersonResumeDetailDTO respDTO = resumeService.getResumeDetail(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 개인 이력서 작성
    @PostMapping("/api/person/resumes")
    public ResponseEntity<?> personSaveResume(@Valid @RequestBody ResumeRequest.ResumeSaveDTO reqDTO, Error error) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.SaveResumeDTO respDTO = resumeService.resumeSave(reqDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 개인 이력서 수정
    @PutMapping("/api/person/resumes/{id}")
    public ResponseEntity<?> personUpdateResume(@PathVariable Integer id, @Valid @RequestBody ResumeRequest.UpdateDTO reqDTO, Error error) {
        ResumeResponse.PersonResumeUpdateDTO respDTO = resumeService.resumeUpdate(id, reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @DeleteMapping("/api/person/resumes/{id}")
    public ResponseEntity<?> personDeleteResume(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        resumeService.deleteResume(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}