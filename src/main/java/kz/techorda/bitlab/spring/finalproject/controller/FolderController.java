package kz.techorda.bitlab.spring.finalproject.controller;

import kz.techorda.bitlab.spring.finalproject.model.Folder;
import kz.techorda.bitlab.spring.finalproject.service.FolderService;
import kz.techorda.bitlab.spring.finalproject.service.TaskCategoryService;
import kz.techorda.bitlab.spring.finalproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskCategoryService taskCategoryService;

    @GetMapping(value = "/")
    public String indexPage(Model model){
        model.addAttribute("folders", folderService.getFolders());
        return "index";
    }

    @PostMapping(value = "/new-folder")
    public String newFolder(Folder folder) {
        folderService.newFolder(folder);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{folderId}")
    public String folderDetails(@PathVariable(name="folderId") Long id, Model model){
        model.addAttribute("folder", folderService.getFolder(id));
        model.addAttribute("tasks",taskService.getTasksByFolderId(id));
        model.addAttribute("categories",folderService.getFolderCategories(id));
        return "folder-details";
    }

    @PostMapping(value="/add-category")
    public String addCategory(@RequestParam(name = "folder_id") Long folderId,
                              @RequestParam(name = "cat_id") Long catId){
        folderService.addCategory(folderId,catId);
        return "redirect:/details/"+folderId;
    }

    @PostMapping(value="/remove-category")
    public String removeCategory(@RequestParam(name = "folder_id") Long folderId,
                              @RequestParam(name = "cat_id") Long catId){
        folderService.unassignCategory(folderId,catId);
        return "redirect:/details/"+folderId;
    }

    @PostMapping(value="/delete-folder")
    public String deleteFolder(@RequestParam(name="folder_id") Long folderId){
        folderService.unassignAllCategories(folderId);
        taskService.deleteAllTasksInFolder(folderId);
        folderService.deleteFolder(folderId);
        return "redirect:/";
    }

    @PostMapping(value="/delete-category")
    public String deleteCategoryFromFolders(@RequestParam(name="cat_id") Long catId){
        if (taskCategoryService.deleteTaskCat(catId, folderService.getFolders())!="") {
            return "redirect:/categories?category-assigned-to-folder/"+
                    taskCategoryService.deleteTaskCat(catId, folderService.getFolders());
        }
        return "redirect:/categories";
    }

}
