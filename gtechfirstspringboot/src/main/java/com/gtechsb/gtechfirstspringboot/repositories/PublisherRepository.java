package com.gtechsb.gtechfirstspringboot.repositories;

import com.gtechsb.gtechfirstspringboot.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
