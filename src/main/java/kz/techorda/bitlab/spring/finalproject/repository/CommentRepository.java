package kz.techorda.bitlab.spring.finalproject.repository;

import kz.techorda.bitlab.spring.finalproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByTaskId(Long id);

}
