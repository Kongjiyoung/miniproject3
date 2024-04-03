package com.many.miniproject1.user;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SessionUser {
    private Integer id;
    private String username;
    private String email;
    private Timestamp createdAt;
    @Builder
    public SessionUser(Integer id, String username, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }
    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
