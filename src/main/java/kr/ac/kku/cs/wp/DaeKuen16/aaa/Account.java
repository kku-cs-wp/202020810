package kr.ac.kku.cs.wp.DaeKuen16.aaa;

import java.util.List;

import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRole;

public class Account {
    private String id;
    private String role;
    private List<UserRole> roles;
    private String name;
    private String email;

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
