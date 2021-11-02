package com.dsi.spring.controller;

import com.dsi.spring.model.Actor;
import com.dsi.spring.service.ActorService;

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
@RequestMapping("/admin/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("actors", actorService.getActors());
        return "admin/movie/actor/actors";
    }

    @GetMapping("/create")
    public String createActorForm(Model model) {
        model.addAttribute("actorForm", new Actor());
        return "admin/movie/actor/actor_form";
    }

    @PostMapping("/create")
    public String createActor(Actor actor) {
        actorService.saveActor(actor);
        return "redirect:/admin/actors/";
    }

    // shows update form
    @GetMapping("/edit/{id}")
    public String updateActorForm(@PathVariable("id") long id, Model model) {
        Actor actor;
        try {
            actor = actorService.getActorById(id);
            model.addAttribute("actorForm", actor);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "admin/movie/actor/actor_form";
    }

    @PostMapping("/edit/{id}")
    public String updateActor(@PathVariable("id") long id, @Validated Actor actor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            actor.setId(id);
            return "admin/movie/actor/actor_form";
        }

        actorService.saveActor(actor);
        return "redirect:/admin/actors/";
    }

    @GetMapping("/delete/{id}")
    public String deleteActor(@PathVariable("id") long id, Model model) {
        Actor actor;
        try {
            actor = actorService.getActorById(id);
            actorService.deleteActor(actor);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/admin/actors/";
    }
}
