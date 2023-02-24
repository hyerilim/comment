package com.example.demo;

import lombok.Getter;

@Getter
public enum UserRole {
	
// 권한 부여	
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
