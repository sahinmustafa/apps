package com.my.authorization.role;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
//@DynamoDBTable(tableName = "role")
public class Role {

    private String id;
    private String roleName;
    private Set<String> privileges = new HashSet<>();

}
