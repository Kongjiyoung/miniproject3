package com.many.miniproject1.resume;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

    //개인 이력서 관리
    @GetMapping("/api/person/resumes")
    public ResponseEntity<?> personResumes() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Resume> resumeList = resumeService.findResumeList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(resumeList));
    }

    // TODO: detail을 넣을지 말지 이야기가 필요함. 선생님은 넣지으셨는데 굳이 안 넣어도 될 것 같아서
    @GetMapping("/api/person/resumes/{id}/detail")
    public ResponseEntity<?> personResume(@PathVariable int id) {
        Resume resume = resumeService.getResumeDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(resume));
    }

    @PostMapping("/api/person/resumes")
    public ResponseEntity<?> personSaveResume(ResumeRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //나중에 findById 해서 그 user 넣어야하는 거 아녀? 일단은 이렇게 둠
        Resume resume = resumeService.save(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(resume));
    }

    @PutMapping("/api/person/resumes/{id}")
    public ResponseEntity<?> personUpdateResume(@PathVariable int id, ResumeRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Resume resume = resumeService.update(id, requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(resume));
    }

    @DeleteMapping("/api/person/resumes/{id}")
    public ResponseEntity<?> personDeleteResume(@PathVariable Integer id) {
        resumeService.deleteResume(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}