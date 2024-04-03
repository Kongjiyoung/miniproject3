package com.many.miniproject1.user;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SessionUser {
    private Integer id;
    private String role;
    private String username;
    private Timestamp createdAt;

    public SessionUser(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.username = user.getUsername();
        this.createdAt = user.getCreatedAt();
    }
    @Builder
    public SessionUser(Integer id, String role, String username, Timestamp createdAt) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.createdAt = createdAt;
    }
}
