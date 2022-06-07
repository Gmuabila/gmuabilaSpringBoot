package com.gtechsb.gtechfirstspringboot.repositories;

import com.gtechsb.gtechfirstspringboot.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
