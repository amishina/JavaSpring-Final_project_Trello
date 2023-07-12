package kz.techorda.bitlab.spring.finalproject.service;

import kz.techorda.bitlab.spring.finalproject.model.TaskStatus;
import kz.techorda.bitlab.spring.finalproject.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    public List<TaskStatus> getAllStatuses() {
        return taskStatusRepository.findAll();
    }
}
