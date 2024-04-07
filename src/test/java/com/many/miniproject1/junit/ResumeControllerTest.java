package com.many.miniproject1.junit;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.many.miniproject1._core.utils.JwtUtil;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeController;
import com.many.miniproject1.resume.ResumeRequest;
import com.many.miniproject1.resume.ResumeService;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.user.SessionUser;
import com.many.miniproject1.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Sql("classpath:db/data.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ResumeControllerTest {

    @InjectMocks
    private ResumeController resumeController;

    @Mock
    private ResumeService resumeService;

    @Autowired
    private MockMvc mockMvc;

    private MockHttpSession mockSession;

    @Autowired
    private ObjectMapper om;

    // 공고 삭제
    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;
        Integer userId = 1;
        String username = "ssar";
        User user = User.builder()
                .id(userId)
                .username(username)
                .build();
        SessionUser sessionUser = new SessionUser(user);

        // when
        String jwt = JwtUtil.create(user); // jwt 생성

        ResultActions resultActions = mockMvc.perform(delete("/api/person/my-page/resumes/"+id)
                .header("Authorization", "Bearer " + jwt)
                .sessionAttr("sessionUser", sessionUser).contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString(); //MockMvc를 사용한 테스트에서 응답 본문을 문자열로 가져오기 위해
        System.out.println("delete_test : " + responseBody);


        //resultActions.andExpect(jsonPath("$.body").doesNotExist());
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("$.body.jobopenTitle").value("소프트웨어 개발자 채용"));
    }


    //이력서 저장
    @Test
    public void save_test() throws Exception {
        // given
        Integer userId = 1;
        String username = "ssar";
        User user = User.builder()
                .id(userId)
                .username(username)
                .build();
        SessionUser sessionUser = new SessionUser(user);
        List<String> skills= new ArrayList<>();
        skills.add("Java");
        ResumeRequest.ResumeSaveDTO saveDTO=new ResumeRequest.ResumeSaveDTO(
                "제목1",
                "ㄹㄹㄹㄹ",
                "ㄹㄹㄹㄹ.png",
                "ㄹㄹㄹㄹ",
                "dddddd",
                "feffef.com",
                "dddddddddddd",
                skills
                );
        // when
        String jwt = JwtUtil.create(user); // jwt 생성

        String requestbBody=om.writeValueAsString(saveDTO);

        ResultActions resultActions = mockMvc.perform(post("/api/person/my-page/resumes")
                .content(requestbBody)
                .header("Authorization", "Bearer " + jwt)
                .sessionAttr("sessionUser", sessionUser).contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString(); //MockMvc를 사용한 테스트에서 응답 본문을 문자열로 가져오기 위해
        System.out.println("save_test : " + responseBody);


        //resultActions.andExpect(jsonPath("$.body").doesNotExist());
        resultActions.andExpect(status().isOk());
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.body.Title").value("소프트웨어 개발자 채용"));
    }


    // 공고 목록보기
    @Test
    public void List_test() throws Exception {
        // given
        Integer userId = 1;
        String username = "ssar";
        User user = User.builder()
                .id(userId)
                .username(username)
                .build();
        SessionUser sessionUser = new SessionUser(user);

        // when
        String jwt = JwtUtil.create(user); // jwt 생성

        ResultActions resultActions = mockMvc.perform(get("/api/person/my-page/resumes")
                .header("Authorization", "Bearer " + jwt)
                .sessionAttr("sessionUser", sessionUser).contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString(); //MockMvc를 사용한 테스트에서 응답 본문을 문자열로 가져오기 위해
        System.out.println("delete_test : " + responseBody);


        //resultActions.andExpect(jsonPath("$.body").doesNotExist());
        resultActions.andExpect(status().isOk());
    }

    // 공고 목록보기
    @Test
    public void detail_test() throws Exception {
        // given
        int id =1;
        Integer userId = 1;
        String username = "ssar";
        User user = User.builder()
                .id(userId)
                .username(username)
                .build();
        SessionUser sessionUser = new SessionUser(user);

        // when
        String jwt = JwtUtil.create(user); // jwt 생성

        ResultActions resultActions = mockMvc.perform(get("/api/person/my-page/resumes/"+id)
                .header("Authorization", "Bearer " + jwt)
                .sessionAttr("sessionUser", sessionUser).contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString(); //MockMvc를 사용한 테스트에서 응답 본문을 문자열로 가져오기 위해
        System.out.println("delete_test : " + responseBody);


        resultActions.andExpect(status().isOk());
    }

}
