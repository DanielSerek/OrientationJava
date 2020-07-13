package com.dependencyinjection.foxclass;

import java.util.List;

public interface ClassServicesIF {
    List<String> findAll();

    void save(String student);

    int count();

    boolean checkStudent(String name);
}
