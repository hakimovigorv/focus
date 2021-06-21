package com.mpi.focus.controllers;

import com.mpi.focus.models.Plan;
import com.mpi.focus.models.Template;
import com.mpi.focus.repos.PlanRepository;
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
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    @GetMapping("/plan")
    public String showPlan(Model model) {
        List<Plan> plans = planRepository.findAll();
        model.addAttribute("plans", plans);
        return "plan";
    }

    @GetMapping("/newplan")
    public String plan(Model model) {
        List<Plan> plans = planRepository.findAll();
        model.addAttribute("plans", plans);
        return "createPlan";
    }

    @PostMapping("/newplan")
    public String addPlan(@RequestParam String planName,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime actionData,
                          @RequestParam String location,
                          Model model) {
        Plan plan = new Plan(planName, actionData, location);
        planRepository.save(plan);
        Iterable<Plan> plans = planRepository.findAll();
        model.addAttribute("plans", plans);
        return "redirect:plan";
    }

    @GetMapping("/plan/{plan}")
    public String planEdit(@PathVariable(value = "plan") Long id,
                           Model model) {
        Plan plan = planRepository.getById(id);
        //List<Template> templates = plan.getTemplates();
        model.addAttribute("plan", plan);
        //model.addAttribute("templates", templates);
        return "planedit";
    }

    @PostMapping("/plan/{plan}")
    public String planEditForm(@PathVariable(value = "plan") Long id,
                               @RequestParam String planName,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime actionData,
                               @RequestParam String location) {
        Plan plan = planRepository.getById(id);
        plan.setPlanName(planName);
        plan.setActionData(actionData);
        plan.setLocation(location);
        planRepository.save(plan);
        return "redirect:";
    }
}
