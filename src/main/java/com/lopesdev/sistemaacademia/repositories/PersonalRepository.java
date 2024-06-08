package com.lopesdev.sistemaacademia.repositories;

import com.lopesdev.sistemaacademia.entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
