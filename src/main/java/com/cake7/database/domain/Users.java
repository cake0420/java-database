package com.cake7.database.domain;

import java.util.UUID;

public class Users {

    private final UUID id;
    private final String name;
    private final String email;
    private final String password;
    private final String salt;

    // 회원가입 시 사용
    public Users(UUID id, String name, String email, String password, String salt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    // 회원정보 조회 시 사용 (비밀번호 제외)
    public Users(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = null;
        this.salt = null;
    }

    // Getter
    public UUID getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

}
