package com.mysql.connection.services;

import com.mysql.connection.models.Assignee;
import com.mysql.connection.repositories.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneeServiceImpl implements AssigneeService{

    AssigneeRepository assigneeRepository;

    @Autowired
    public AssigneeServiceImpl(AssigneeRepository assigneeRepository){
        this.assigneeRepository = assigneeRepository;
    }

    @Override
    public void createAssignee(String name, String email) {
        Assignee assignee = new Assignee(name, email);
        this.assigneeRepository.save(assignee);
    }

    @Override
    public List<Assignee> getAllAssignees() {
        return this.assigneeRepository.findAll();
    }

    @Override
    public void deleteAssignee(long id) {
        this.assigneeRepository.deleteById(id);
    }

    @Override
    public Assignee getAssignee(long id) {
        return this.assigneeRepository.getOne(id);
    }

    @Override
    public void updateAssignee(long id, String name, String email) {
        Assignee assignee = new Assignee(name, email);
        assignee.setAssigneeId(id);
        this.assigneeRepository.save(assignee);
    }
}
