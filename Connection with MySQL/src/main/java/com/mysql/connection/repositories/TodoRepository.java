package com.mysql.connection.repositories;

import com.mysql.connection.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TodoRepository extends JpaRepository<Task, Long> {

}
