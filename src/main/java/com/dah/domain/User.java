package com.dah.domain;

import java.util.List;

import com.dah.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
  private Integer id;
  private String email;
  private String password;
  private String institution;
  private List<Role> roles;

  public boolean hasRole(Role role) {
    return roles.contains(role);
  }
}
