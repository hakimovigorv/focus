package com.mpi.focus.controllers;

import com.mpi.focus.models.Plan;
import com.mpi.focus.models.Template;
import com.mpi.focus.repos.PlanRepository;
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
public class TemplateController {

    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    PlanRepository planRepository;

    @GetMapping("/template")
    public String home(Model model) {
        List<Template> templates = templateRepository.findAll();
        model.addAttribute("templates", templates);
        return "template";
    }

    @GetMapping("/plan/newtemplate/{plan}")
    public String template(@PathVariable(value = "plan") Long id,
                           Model model) {
        Plan plan = planRepository.getById(id);
        List<Template> templates = templateRepository.findAll();
        model.addAttribute("templates", templates);
        model.addAttribute("plan", plan);
        return "createTemplate";
    }

    @PostMapping("/plan/newtemplate/{plan}")
    public String templateCreate(@PathVariable(value = "plan") Long id,
                                 @RequestParam String templateName
                               ) {
        Plan plan = planRepository.getById(id);
        Template template = new Template(templateName, plan);
        templateRepository.save(template);
        return "redirect:/plan/newtemplate/{plan}";
    }


    @GetMapping("/template/{template}")
    public String templateEdit(@PathVariable(value = "template") Long id,
                               Model model) {
        Template template = templateRepository.getById(id);
        model.addAttribute("template", template);
        return "templateedit";
    }

    @PostMapping("/template/{template}")
    public String planEditForm(@PathVariable(value = "template") Long id,
                               @RequestParam String templateName) {
        Template template = templateRepository.getById(id);
        template.setTemplateName(templateName);
        templateRepository.save(template);

        return "redirect:/template/{template}";
    }

}
