/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "version1/blueprints")
public class BlueprintAPIController {
    @Autowired
    BlueprintsServices bps;
    @RequestMapping(method = RequestMethod.GET)
    public List<Blueprint> controllerGetBlueprints(){
        return bps.getAllBlueprints(HttpStatus.ACCEPTED);
    }

     @GetMapping("{author}")
    public List<Blueprint> controllerGetBlueprintsByAuthor(@PathVariable("author") String author){
        return bps.getBlueprintsByAuthor(author);
    }

    @GetMapping("{author}/{bpname}")
    public Blueprint controllerGetBlueprint(@RequestBody @PathVariable("author")String author,@PathVariable("bpname")String bpname){
        return bps.getBlueprint(author,bpname);
    }


    
    
    
    
}

