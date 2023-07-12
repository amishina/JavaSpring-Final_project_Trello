package kz.techorda.bitlab.spring.finalproject.service;

import kz.techorda.bitlab.spring.finalproject.model.Folder;
import kz.techorda.bitlab.spring.finalproject.model.TaskCategory;
import kz.techorda.bitlab.spring.finalproject.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private TaskCategoryService taskCategoryService;

    public List<Folder> getFolders() {
        List<Folder> folderList = folderRepository.findAll();
        return folderList;
    }

    public List<TaskCategory> getFolderCategories(Long folderId){
        Folder folder = getFolder(folderId);
        List<TaskCategory> taskCategoryList = taskCategoryService.getTaskCategories();
        taskCategoryList.removeAll(folder.getCategories());
        return taskCategoryList;
    }

    public Folder newFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    public Folder getFolder(Long id) {
        return folderRepository.findById(id).orElse(null);
    }

    public void addCategory(Long folderId, Long catId){
        Folder folder = getFolder(folderId);
        TaskCategory taskCategory = taskCategoryService.getTaskCategory(catId);
        if (folder.getCategories()!=null){
            if (folder.getCategories().size()>0 && !folder.getCategories().contains(taskCategory)){
                folder.getCategories().add(taskCategory);
            } else {
                List<TaskCategory> taskCategoryList = new ArrayList<>();
                taskCategoryList.add(taskCategory);
                folder.setCategories(taskCategoryList);
            }
        }
        folderRepository.save(folder);
    }

    public void unassignCategory(Long folderId, Long catId){
        Folder folder = getFolder(folderId);
        TaskCategory taskCategory = taskCategoryService.getTaskCategory(catId);
        if (folder.getCategories()!=null && folder.getCategories().size()>0){
            folder.getCategories().remove(taskCategory);
        }
        folderRepository.save(folder);
    }

    public void unassignAllCategories(Long folderId){
        Folder folder = getFolder(folderId);
        List<Long> catIds = new ArrayList<>();
        for(int i=0;i<folder.getCategories().size();i++) {
            catIds.add(folder.getCategories().get(i).getId());
            unassignCategory(folderId,catIds.get(i));
        }
    }

    public void deleteFolder(Long id) {
        folderRepository.deleteById(id);
    }

}
