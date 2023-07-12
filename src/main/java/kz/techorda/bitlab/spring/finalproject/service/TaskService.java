package kz.techorda.bitlab.spring.finalproject.service;

import kz.techorda.bitlab.spring.finalproject.model.Comment;
import kz.techorda.bitlab.spring.finalproject.model.Task;
import kz.techorda.bitlab.spring.finalproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentService commentService;

    public List<Task> getTasksByFolderId(Long id) {
        List<Task> taskList = taskRepository.findTasksByFolderId(id);
        return taskList;
    }

    public Task newTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return task;
    }

    public void deleteTask(Long taskId) {
        List<Comment> commentList = commentService.getComments(taskId);
        for(int i=0;i<commentList.size();i++) {
            commentService.unassignComment(commentList.get(i).getId());
        }
        taskRepository.deleteById(taskId);
    }

    public void deleteAllTasksInFolder(Long id){
        List<Task> taskList = taskRepository.findTasksByFolderId(id);
        for(Task task : taskList){
            List<Comment> commentList = commentService.getComments(task.getId());
            for(int i=0;i<commentList.size();i++) {
                commentService.unassignComment(commentList.get(i).getId());
                commentService.deleteTaskComment(commentList.get(i).getId());
            }
            taskRepository.deleteById(task.getId());
        }
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void addCommentToTask(Long taskId, String comment){
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setTask(getTask(taskId));
        commentService.updateComment(newComment);
    }

}
