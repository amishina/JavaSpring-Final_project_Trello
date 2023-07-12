package kz.techorda.bitlab.spring.finalproject.repository;

import kz.techorda.bitlab.spring.finalproject.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
}
