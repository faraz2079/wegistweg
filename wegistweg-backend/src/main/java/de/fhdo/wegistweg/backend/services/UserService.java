package de.fhdo.wegistweg.backend.services;

import de.fhdo.wegistweg.backend.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
}
