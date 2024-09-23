package com.example.demo.controllers.admin;

import com.example.demo.models.Category;
import com.example.demo.services.admin.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category/list")
    public String list() {
        return "admin/category/list";
    }

    @GetMapping("/admin/category/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add";
    }

    @PostMapping("/admin/category/save")
    public String save(@Valid @ModelAttribute("category") Category category,
                       BindingResult result, Model model,
                       @RequestParam(value = "image_id", required = false) String image_id) {
        if (result.hasErrors()) {
            return "admin/category/add";
        }

        // Set the image file name in the category object
        if (image_id != null && !image_id.isEmpty()) {
            category.setImage(image_id);
        }

        categoryService.save(category);

        return "redirect:/admin/category/add?success=true";
    }
}
