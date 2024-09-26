package com.gtechsb.gtechfirstspringboot.repositories;

import com.gtechsb.gtechfirstspringboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
