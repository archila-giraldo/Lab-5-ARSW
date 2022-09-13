/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> controllerGetBlueprints(){
        List<Blueprint> data = bps.getAllBlueprints(HttpStatus.ACCEPTED);
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(data), HttpStatus.ACCEPTED);

    }

    @GetMapping("{author}")
    public ResponseEntity<?> controllerGetBlueprintsByAuthor(@PathVariable("author") String author){
         List<Blueprint> data = bps.getBlueprintsByAuthor(author);
         Gson gson = new Gson();
         return new ResponseEntity<>(gson.toJson(data), HttpStatus.ACCEPTED);
    }

    @GetMapping("{author}/{bpname}")
    public ResponseEntity<?> controllerGetBlueprint(@RequestBody @PathVariable("author")String author,@PathVariable("bpname")String bpname){
        Blueprint data = bps.getBlueprint(author,bpname);
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(data), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<?> controllerPostBlueprint(@RequestBody Blueprint bp){
        try{
            bps.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (BlueprintPersistenceException ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se pudo resgitrar", HttpStatus.FORBIDDEN);
        }
    }
}

