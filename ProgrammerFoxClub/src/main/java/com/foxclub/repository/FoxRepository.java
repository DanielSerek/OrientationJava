package com.foxclub.repository;

import com.foxclub.models.Fox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoxRepository extends JpaRepository<Fox, Long> {
}
