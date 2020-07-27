package com.example.orientationtestexample.repositories;

import com.example.orientationtestexample.models.URLAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLAliasRepository extends JpaRepository<URLAlias, Long> {
}
