package com.dsi.spring.controller;

import com.dsi.spring.model.Cast;
import com.dsi.spring.services.CastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/casts")
public class CastController {

    @Autowired
    private CastService castService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("casts", castService.getCasts());
        return "admin/movie/cast/casts";
    }

    @GetMapping("/create")
    public String createCastForm(Model model) {
        model.addAttribute("castForm", new Cast());
        return "admin/movie/cast/create_cast_form";
    }

    @PostMapping("/create")
    public String createCast(Cast cast) {
        castService.createCast(cast);
        return "redirect:/admin/casts/";
    }
}
