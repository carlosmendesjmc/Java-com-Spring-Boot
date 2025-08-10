package com.Spring02.userdept02.repositories;

import com.Spring02.userdept02.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
