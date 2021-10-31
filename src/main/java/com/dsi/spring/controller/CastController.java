package com.dsi.spring.controller;

import com.dsi.spring.dao.CastDao;
import com.dsi.spring.model.Cast;

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
    private CastDao castDao;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("casts", castDao.findAll());
        return "admin/movie/cast/casts";
    }

    @GetMapping("/create")
    public String createCastForm(Model model) {
        model.addAttribute("castForm", new Cast());
        return "admin/movie/cast/create_cast_form";
    }

    @PostMapping("/create")
    public String createCast(Cast cast) {
        castDao.save(cast);
        return "redirect:/admin/casts/";
    }
}
