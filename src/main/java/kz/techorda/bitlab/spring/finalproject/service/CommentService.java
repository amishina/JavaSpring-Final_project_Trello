package kz.techorda.bitlab.spring.finalproject.service;

import kz.techorda.bitlab.spring.finalproject.model.Comment;
import kz.techorda.bitlab.spring.finalproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments(Long taskId){
        List<Comment> commentList = commentRepository.findCommentsByTaskId(taskId);
        return commentList;
    }

    public void deleteTaskComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public void updateComment(Comment newComment){
        commentRepository.save(newComment);
    }

    public void unassignComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElse(null);
        comment.setTask(null); ;
        commentRepository.save(comment);
    }

}
