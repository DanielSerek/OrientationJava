package com.mysql.connection.repositories;

import com.mysql.connection.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Task, Long> {

}
