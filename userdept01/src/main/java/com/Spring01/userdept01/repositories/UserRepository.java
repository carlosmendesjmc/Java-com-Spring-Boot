package com.Spring01.userdept01.repositories;

import com.Spring01.userdept01.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
