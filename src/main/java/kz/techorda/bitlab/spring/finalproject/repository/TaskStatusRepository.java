package kz.techorda.bitlab.spring.finalproject.repository;

import kz.techorda.bitlab.spring.finalproject.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}
