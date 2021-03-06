package com.gtechsb.gtechfirstspringboot.repositories;

import com.gtechsb.gtechfirstspringboot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}