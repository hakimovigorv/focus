package com.mpi.focus.controllers;


import com.mpi.focus.models.Legend;
import com.mpi.focus.models.Plan;
import com.mpi.focus.models.User;
import com.mpi.focus.repos.LegendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class LegendController {

    @Autowired
    private LegendRepository legendRepository;

    @GetMapping("/legend")
    public String legendMain(Model model) {
        List<Legend> legends = legendRepository.findAll();
        model.addAttribute("legends", legends);
        return "legend-main";
    }

    @GetMapping("/legendnew")
    public String legendNew(Model model) {
        List<Legend> legends = legendRepository.findAll();
        model.addAttribute("legends", legends);
        return "createLegend";
    }

    @PostMapping("/legendnew")
    public String addLegend(@AuthenticationPrincipal User specialist,
                            @RequestParam String fakeName,
                            @RequestParam String story,
                            @RequestParam String purpose,
                            Model model) {
        Legend legend = new Legend(specialist, fakeName, story, purpose);
        legendRepository.save(legend);

        List<Legend> legends = legendRepository.findAll();
        model.addAttribute("legends", legends);
        return "redirect:legend";
    }

    @GetMapping("/legend/{legend}")
    public String legendEdit(@PathVariable Legend legend,
                             Model model) {
        model.addAttribute("legend", legend);
        return "legendedit";
    }

    @PostMapping("/legend/{legend}")
    public String legendEditForm(@PathVariable(value = "legend") Long id,
                                 @AuthenticationPrincipal User specialist,
                                 @RequestParam String fakeName,
                                 @RequestParam String story,
                                 @RequestParam String purpose) {
        Legend legend = legendRepository.getById(id);
        //
        legend.setSpecialist(specialist);
        legend.setFakeName(fakeName);
        legend.setStory(story);
        legend.setPurpose(purpose);

        legendRepository.save(legend);

        return "redirect:";
    }

}