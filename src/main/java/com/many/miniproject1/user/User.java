package com.many.miniproject1.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "user_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class User {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Integer id;
    private String role;
    @Column(unique = true)
    private String username; //이제부터 아이디를 이메일로 안받을 예정
    private String email;
    private String password;
    private String name;
    private String tel;
    private String companyName;
    private String address;
    private String companyNum;
    private String profile;
    private Date birth;
    @CreationTimestamp
    private Timestamp createdAt;//

    @Builder
    public User(Integer id, String role, String username, String email, String password, String name, String tel, String companyName, String address, String companyNum, String profile, Date birth, Timestamp createdAt) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.companyName = companyName;
        this.address = address;
        this.companyNum = companyNum;
        this.profile = profile;
        this.birth = birth;
        this.createdAt = createdAt;
    }

    //    public void update(Integer personId, UserRequest.PersonInfoUpdateDTO reqDTO, String profileFilename) {
    public void update(Integer personId, UserRequest.PersonInfoUpdateDTO reqDTO) {
        // this.profile = profileFilename;
        this.name = reqDTO.getName();
        // this.birth = Date.valueOf(reqDTO.getName());
        this.tel = reqDTO.getTel();
        this.address = reqDTO.getAddress();
        this.email = reqDTO.getEmail();
        this.password = reqDTO.getPassword();
    }
}
