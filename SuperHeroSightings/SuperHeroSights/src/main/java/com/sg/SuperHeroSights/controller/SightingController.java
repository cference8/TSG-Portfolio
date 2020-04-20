/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.controller;

import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Location;
import com.sg.SuperHeroSights.models.Sighting;
import com.sg.SuperHeroSights.service.ServiceLayer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author board
 */
@Controller
public class SightingController {
    
    @Autowired
    ServiceLayer service;    
    
    @GetMapping("sighting")
    public String displaySightingPage(Model m){
        List<Sighting> sightings = service.getAllSightingsToDisplay();
        List<Hero> heroes = service.getAllHeroes();
        List<Location> locations = service.getAllLocations();
        
        m.addAttribute("sightings", sightings);
        m.addAttribute("heroes", heroes);
        m.addAttribute("locations", locations);
        
        return "sighting";
    }
    
    @PostMapping("addSighting")
    public String addSighting(Sighting toAdd, HttpServletRequest request) {
        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        
//        String heroId = request.getParameter("heroId");
//        String locationId = request.getParameter("locationId");
//        String date = request.getParameter("date");
//            
//        toAdd.getHero().setHeroId(Integer.parseInt(heroId));
//        toAdd.getLocation().setId(Integer.parseInt(locationId));
//        toAdd.setDateSighted(LocalDate.parse(date, formatter));
//        service.addSighting(toAdd);
 
        return "redirect:/sighting";
        
    }
}
