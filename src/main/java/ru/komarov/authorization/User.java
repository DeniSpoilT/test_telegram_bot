package ru.komarov.authorization;

public class User {
    private String  username;
    private String company;
    private Role role;

    public User(String username, String company, Role role) {
        this.username = username;
        this.company = company;
        this.role = role;
        System.out.printf("created new %s username : %s from %s \n", role.name(), username, company);
    }

    public String getUsername() {
        return username;
    }

    public String getCompany() {
        return company;
    }

    public Role getRole() {
        return role;
    }
}
