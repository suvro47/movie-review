package com.dsi.spring.controller;

import com.dsi.spring.model.Actor;
import com.dsi.spring.services.ActorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("actors", actorService.getActors());
        return "admin/movie/actor/actors";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createActorForm(Model model) {
        model.addAttribute("actorForm", new Actor());
        return "admin/movie/actor/actor_form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createActor(Actor actor) {
        actorService.saveActor(actor);
        return "redirect:/admin/actors/";
    }

    // shows update form
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
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

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.POST)
    public String updateActor(@PathVariable("id") long id, @Validated Actor actor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            actor.setId(id);
            return "admin/movie/actor/actor_form";
        }

        actorService.saveActor(actor);
        return "redirect:/admin/actors/";
    }

    @RequestMapping(value = "/delete/{id}")
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
