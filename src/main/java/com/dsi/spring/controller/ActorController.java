package com.dsi.spring.controller;

import com.dsi.spring.model.Actor;
import com.dsi.spring.services.ActorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/casts")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("casts", actorService.getActors());
        return "admin/movie/cast/casts";
    }

    @GetMapping("/create")
    public String createCastForm(Model model) {
        model.addAttribute("castForm", new Actor());
        return "admin/movie/cast/create_cast_form";
    }

    @PostMapping("/create")
    public String createCast(Actor cast) {
        actorService.createActor(cast);
        return "redirect:/admin/casts/";
    }
}
