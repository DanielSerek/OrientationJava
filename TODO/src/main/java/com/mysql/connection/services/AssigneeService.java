package com.mysql.connection.services;

import com.mysql.connection.models.Assignee;

import java.util.List;


public interface AssigneeService {

    void createAssignee(String name, String email);

    List<Assignee> getAllAssignees();

    void deleteAssignee(long id);

    Assignee getAssignee(long id);

    void updateAssignee(long id, String name, String email);
}
