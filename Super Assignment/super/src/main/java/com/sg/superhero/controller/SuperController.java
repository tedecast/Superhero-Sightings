/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.PowerDao;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.dao.SuperDao;
import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Super;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Teresa
 */
@Controller
public class SuperController {

    @Autowired
    SuperDao superDao;

    @Autowired
    PowerDao powerDao;

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("supers")
    public String displaySupers(Model model) {
        List<Super> supers = this.superDao.getAllSupers();
        model.addAttribute("supers", supers);
        return "supers";
    }

    @PostMapping("addSuper")
    public String addSuper(HttpServletRequest request) {

        String powerName = request.getParameter("powerName");
        String powerDescription = request.getParameter("powerDescription");

        Power power = new Power();
        power.setPowerID(power.getPowerID());
        power.setName(powerName);
        power.setDescription(powerDescription);
        this.powerDao.addPower(power);
        
        
        String type = request.getParameter("superType");
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");

        Super superhero = new Super();
        superhero.setSuperID(superhero.getSuperID());
        superhero.setPower(power);
        superhero.setType(type);
        superhero.setName(superName);
        superhero.setDescription(superDescription);

        this.superDao.addSuper(superhero);

        return "redirect:/supers";
    }
}
