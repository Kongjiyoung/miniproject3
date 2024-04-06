package com.many.miniproject1.user;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;


@Data
public class SessionUser {
    private Integer id;
    private String username;
    private String email;
    private String role;
    private Timestamp createdAt;

    @Builder
    public SessionUser(Integer id, String username, String email, String role,Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    //TODO: 유저 생성자 추가
    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.role = user.getRole();
    }

}



