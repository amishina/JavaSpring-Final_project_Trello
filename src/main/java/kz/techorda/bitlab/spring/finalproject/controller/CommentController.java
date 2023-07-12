package kz.techorda.bitlab.spring.finalproject.controller;

import kz.techorda.bitlab.spring.finalproject.service.CommentService;
import kz.techorda.bitlab.spring.finalproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TaskService taskService;

    @PostMapping(value="/add-comment")
    public String addComment(@RequestParam(name="task.id") Long taskId,
                             @RequestParam(name="comment") String comment){
        taskService.addCommentToTask(taskId, comment);
        return "redirect:/task-details/"+taskId;
    }

    @PostMapping(value="/delete-comment")
    public String deleteComment(@RequestParam(name="task_id") Long taskId,
                                @RequestParam(name="comment_id") Long commentId){
        commentService.deleteTaskComment(commentId);
        return "redirect:/task-details/"+taskId;
    }

}
