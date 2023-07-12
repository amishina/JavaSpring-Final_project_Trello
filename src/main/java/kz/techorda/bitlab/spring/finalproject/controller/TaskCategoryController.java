package kz.techorda.bitlab.spring.finalproject.controller;

import kz.techorda.bitlab.spring.finalproject.model.TaskCategory;
import kz.techorda.bitlab.spring.finalproject.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    @GetMapping(value = "/categories")
    public String allCategories(Model model){
        model.addAttribute("categories",taskCategoryService.getTaskCategories());
        return "categories";
    }

    @PostMapping(value = "/new-category")
    public String newCategory(TaskCategory taskCategory) {
        taskCategoryService.newTaskCategory(taskCategory);
        return "redirect:/categories";
    }

    @PostMapping(value = "/change-category-name")
    public String changeCategoryName(TaskCategory taskCategory){
        taskCategoryService.changeName(taskCategory);
        return "redirect:/categories";
    }

}
