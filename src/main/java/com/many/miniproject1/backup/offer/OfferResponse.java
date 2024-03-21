package com.many.miniproject1.backup.offer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class OfferResponse {

    @Data
    public static class OfferDTO{
        private Integer id;
        private Integer resumeId;
        private Integer postId;
        private Integer companyId;
        private Integer personId;
    }

    @Data
    public static class OfferBoardDTO{

        private Integer id;
        private String companyName;
        private Integer postId;
        private String title;
        private Timestamp createdAt;
        private String profile;
    }

    // resume_tb 전체 값과, user_tb.username담을 DTO
    @Data
    public static class OfferResumeDTO{
        // resume_tb
        private Integer id;
        private Integer personId; // resumeWriterId
        private String title;
        private String profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private Timestamp createdAt;
        // user_tb
        private String username;
    }

    // OfferResumeDTO + skill
    @Data
    public static class OfferResumeSkillDTO{
        private Integer id;
        private Integer personId;
        private String title;
        private String profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private Timestamp createdAt;
        private String username;
        // skill
        private List<String> skills;

        public OfferResumeSkillDTO(OfferResumeDTO offerResumeDTO, List<String> skills) {
            this.id = offerResumeDTO.getId();
            this.personId = offerResumeDTO.getPersonId();
            this.title = offerResumeDTO.getTitle();
            this.profile = offerResumeDTO.getProfile();
            this.portfolio = offerResumeDTO.getPortfolio();
            this.introduce = offerResumeDTO.getIntroduce();
            this.career = offerResumeDTO.getCareer();
            this.simpleIntroduce = offerResumeDTO.getSimpleIntroduce();
            this.createdAt = offerResumeDTO.getCreatedAt();
            this.username = offerResumeDTO.getUsername();
            this.skills = skills;
        }
    }
    // offer보낸 resume의 detail보기
    @Data
    public static class OfferResumeDetailDTO{
        private String title;
        private String username;
        private String birth;
        private String tel;
        private String address;
        private String email;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private String profile;
    }

    @Data
    public static class OfferResumeDetailPlusSkillDTO{
        public OfferResumeDetailPlusSkillDTO(OfferResumeDetailDTO offerResumeDetailDTO, List<String> skills) {
            this.title = offerResumeDetailDTO.getTitle();
            this.username = offerResumeDetailDTO.getUsername();
            this.birth = offerResumeDetailDTO.getBirth();
            this.tel = offerResumeDetailDTO.getTel();
            this.address = offerResumeDetailDTO.getAddress();
            this.email = offerResumeDetailDTO.getEmail();
            this.career = offerResumeDetailDTO.getCareer();
            this.simpleIntroduce = offerResumeDetailDTO.getSimpleIntroduce();
            this.portfolio = offerResumeDetailDTO.getPortfolio();
            this.introduce = offerResumeDetailDTO.getIntroduce();
            this.profile = offerResumeDetailDTO.getProfile();
            this.skills = skills;
        }

        private String title;
        private String username;
        private String birth;
        private String tel;
        private String address;
        private String email;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private String profile;
        private List<String> skills;
    }

    @Data
    public static class companyFindAllOfferDTO{
        private Integer id;

    }
}
