package com.dah.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dah.domain.User;
import com.dah.enums.Role;
import com.dah.records.RegisterUserData;
import com.dah.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountService {

  private UserRepository userRepo;
  private User currentUser;

  public User registerResearcher(RegisterUserData data) {
    Optional<User> user = userRepo.findByEmail(data.email());

    if (user.isPresent()) {
      throw new IllegalArgumentException(String.format("Usuário já existe com email \"%s\"", data.email()));
    }

    List<Role> roles = new ArrayList<>();
    roles.add(Role.RESEARCHER);

    return userRepo
        .save(new User(/* Id */ null, data.email(), data.name(), data.password(), data.institution(), roles));
  }

  public User authenticate(String email, String password) {
    Optional<User> user = userRepo.findByEmail(email);

    if (user.isEmpty()) {
      throw new IllegalArgumentException(String.format("Usuário não existe com email \"%s\"", email));
    }

    this.currentUser = user.get();
    return user.get();
  }

  public User getCurrentUser() {
    return this.currentUser;
  }
  
  public void logout() {
    this.currentUser = null;
  }

  public boolean isCurrentUserCoordinator() {
    if (this.currentUser == null) {
      return false;
    }

    if (!this.currentUser.hasRole(Role.COORDINATOR)) {
      return false;
    }

    return true;
  }
}
