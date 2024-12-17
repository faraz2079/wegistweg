package de.fhdo.wegistweg.Repository;

import de.fhdo.wegistweg.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
