package com.github.ethancarter.web.jdbc;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRoleDTO {

    @NotEmpty(message = "Role name cannot be empty.")
    private String roleName;
    private String account;
    private Integer pageNum;
    private Integer pageSize;

}
