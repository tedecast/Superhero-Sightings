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
public class PowerController {

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

    @GetMapping("powers")
    public String displayPowers(Model model) {
        List<Power> powers = this.powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @PostMapping("addPower")
    public String addPower(HttpServletRequest request) {

        String powerName = request.getParameter("powerName");
        String powerDescription = request.getParameter("powerDescription");

        Power power = new Power();
        power.setName(powerName);
        power.setDescription(powerDescription);

        this.powerDao.addPower(power);

        return "redirect:/powers";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        this.powerDao.deletePowerByID(powerID);

        return "redirect:/powers";
    }

    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        Power power = this.powerDao.getPowerByID(powerID);

        model.addAttribute("power", power);
        return "editPower";
    }

    @PostMapping("editPower")
    public String performEditPower (HttpServletRequest request) {
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        Power power = this.powerDao.getPowerByID(powerID);

        power.setName(request.getParameter("powerName"));
        power.setDescription(request.getParameter("powerDescription"));

        this.powerDao.updatePower(power);

        return "redirect:/powers";
    }
}
