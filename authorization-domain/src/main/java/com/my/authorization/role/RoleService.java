package com.my.authorization.role;

public interface RoleService {

    void addUser(String roleId, String userId);

    default Role getDefaulRole() {
        Role role = new Role();
        role.setId("-1");
        role.setRoleName("Default Role");
        return role;
    }
}
