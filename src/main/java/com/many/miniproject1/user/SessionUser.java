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
}
