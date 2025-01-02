package de.fhdo.wegistweg.repository;

import de.fhdo.wegistweg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query to find a user by email (JpaRepository already provides this)
    Optional<User> findByEmail(String email);
}
