package com.Spring03.userdept03.repositories;

import com.Spring03.userdept03.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
