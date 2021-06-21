package com.mpi.focus.controllers;

import com.mpi.focus.models.Task;
import com.mpi.focus.models.Template;
import com.mpi.focus.repos.TaskRepository;
import com.mpi.focus.repos.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @GetMapping("/task")
    public String home(Model model) {
        model.addAttribute("title", "task");
        return "task";
    }

    @GetMapping("/task/{task}")
    public String taskEdit(@PathVariable(value = "task") Long id,
                           Model model) {
        Task task = taskRepository.getById(id);
        model.addAttribute(task);
        return "taskedit";
    }

    @PostMapping("/task/{task}")
    public String taskEditForm(@PathVariable(value = "task") Long id,
                               @RequestParam String taskName,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStop,
                               @RequestParam String description,
                               @RequestParam String place
    ) {
        Task task = taskRepository.getById(id);
        task.setTaskName(taskName);
        task.setTimeStart(timeStart);
        task.setTimeStop(timeStop);
        task.setDescription(description);
        task.setPlace(place);
        taskRepository.save(task);
        return "redirect:/task/{task}";
    }

    @GetMapping("/template/newtask/{template}")
    public String task(@PathVariable(value = "template") Long id,
                       Model model) {
        Template template = templateRepository.getById(id);
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("template", template);
        model.addAttribute("tasks", tasks);
        return "createTask";
    }

    @PostMapping("/template/newtask/{template}")
    public String taskCreate(@PathVariable(value = "template") Long id,
                             @RequestParam String taskName,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStop,
                             @RequestParam String description,
                             @RequestParam String place
    ) {
        Template template = templateRepository.getById(id);
        Task task = new Task(taskName, timeStart, timeStop, description, place, template);
        taskRepository.save(task);
        return "redirect:/template/newtask/{template}";
    }
}
