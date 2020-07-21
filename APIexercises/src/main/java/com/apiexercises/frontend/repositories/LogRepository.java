package com.apiexercises.frontend.repositories;

import com.apiexercises.frontend.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
