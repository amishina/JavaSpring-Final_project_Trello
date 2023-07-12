package kz.techorda.bitlab.spring.finalproject.service;

import kz.techorda.bitlab.spring.finalproject.model.Folder;
import kz.techorda.bitlab.spring.finalproject.model.TaskCategory;
import kz.techorda.bitlab.spring.finalproject.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public List<TaskCategory> getTaskCategories(){
        return taskCategoryRepository.findAll();
    }

    public TaskCategory getTaskCategory(Long id) {
        return taskCategoryRepository.findById(id).orElse(null);
    }

    public TaskCategory newTaskCategory(TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    public TaskCategory changeName(TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    public String deleteTaskCat(Long id, List<Folder> folderList) {
        boolean flag = false;
        String folderName = "";
        TaskCategory taskCategory = getTaskCategory(id);
        for(int i=0;i<folderList.size();i++)
            if (folderList.get(i).getCategories().size()>0)
                if (!folderList.get(i).getCategories().contains(taskCategory))
                    continue;
                else {
                    flag = true;
                    folderName = folderList.get(i).getName();
                }
        if (flag == false)
            taskCategoryRepository.deleteById(id);
        return folderName;
    }

}
