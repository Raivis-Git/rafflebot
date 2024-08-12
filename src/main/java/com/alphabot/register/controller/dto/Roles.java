package com.alphabot.register.controller.dto;

public class Roles {

    String roleId;
    Long val;
    String name;
    Boolean stacking;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getVal() {
        return val;
    }

    public void setVal(Long val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStacking() {
        return stacking;
    }

    public void setStacking(Boolean stacking) {
        this.stacking = stacking;
    }
}
