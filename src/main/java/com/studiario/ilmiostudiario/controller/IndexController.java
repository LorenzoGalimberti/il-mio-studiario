package com.studiario.ilmiostudiario.controller;


import com.studiario.ilmiostudiario.model.Nozione;
import com.studiario.ilmiostudiario.repository.NozioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private NozioneRepository nozioneRepository;
    @GetMapping
    public String index(Model model){
        List<Nozione> nozioni;
        nozioni=nozioneRepository.findAll();
        model.addAttribute("nozioni",nozioni);
        return "homepage";
    }
}
