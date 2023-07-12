package kz.techorda.bitlab.spring.finalproject.controller;

import kz.techorda.bitlab.spring.finalproject.model.Task;
import kz.techorda.bitlab.spring.finalproject.service.CommentService;
import kz.techorda.bitlab.spring.finalproject.service.TaskService;
import kz.techorda.bitlab.spring.finalproject.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/new-task")
    public String newTask(Task task) {
        taskService.newTask(task);
        return "redirect:/details/" + task.getFolder().getId();
    }

    @GetMapping(value = "/task-details/{taskId}")
    public String taskDetails(@PathVariable(name="taskId") Long taskId, Model model){
        model.addAttribute("task", taskService.getTask(taskId));
        model.addAttribute("statuses", taskStatusService.getAllStatuses());
        model.addAttribute("comments", commentService.getComments(taskId));
        return "task-details";
    }

    @PostMapping(value="/delete-task")
    public String deleteMusic(@RequestParam(name="task_id") Long taskId,
                              @RequestParam(name="folder_id") Long folderId){
        taskService.deleteTask(taskId);
        return "redirect:/details/" + folderId;
    }

    @PostMapping(value = "/edit-task")
    public String editTask(Task task){
        taskService.updateTask(task);
        return "redirect:/details/"+task.getFolder().getId();
    }

}
