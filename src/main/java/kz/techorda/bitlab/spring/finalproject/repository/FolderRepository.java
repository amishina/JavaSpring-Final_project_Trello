package kz.techorda.bitlab.spring.finalproject.repository;

import kz.techorda.bitlab.spring.finalproject.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
}
