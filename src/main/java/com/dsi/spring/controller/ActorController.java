package com.dsi.spring.controller;

import com.dsi.spring.model.Actor;
import com.dsi.spring.services.ActorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        actorService.saveActor(cast);
        return "redirect:/admin/casts/";
    }

    // shows update form
    @GetMapping("/edit/{id}")
    public String updateCastForm(@PathVariable("id") long id, Model model) {
        Actor actor;
        try {
            actor = actorService.getActorById(id);
            model.addAttribute("castForm", actor);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "admin/movie/cast/create_cast_form";
    }

    @PostMapping("/edit/{id}")
    public String updateCast(@PathVariable("id") long id, @Validated Actor actor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            actor.setId(id);
            return "admin/movie/cast/create_cast_form";
        }

        actorService.saveActor(actor);
        return "redirect:/admin/casts/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCast(@PathVariable("id") long id, Model model) {
        Actor actor;
        try {
            actor = actorService.getActorById(id);
            actorService.deleteActor(actor);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/admin/casts/";
    }
}
