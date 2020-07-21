package com.apiexercises.frontend.services;

import com.apiexercises.frontend.models.Log;
import com.apiexercises.frontend.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void addLog(Log log){
        logRepository.save(log);
    }

    public List<Log> getLogs(long count) {
        if (count == -1){
            count = this.logRepository.findAll().size();
        }
        return this.logRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public List<Log> getPage(Long pageInput) {
        int max = this.logRepository.findAll().size();
        int page = pageInput.intValue();

        if (page > (max / 10)) {
            return null;
        }

        if (page == 1) {
            return this.logRepository.findAll().stream().limit(10).collect(Collectors.toList());
        } else {
            return this.logRepository.findAll().stream().collect(Collectors.toList()).subList((page - 1) * 10, ((page * 10) > max) ? max : page * 10);
        }
    }
}
