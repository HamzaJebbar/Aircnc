package com.model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
@RestController

public class HoteService {
    HoteRepository HoteRep;

    public HoteService(HoteRepository hoteRep) {
        HoteRep = hoteRep;
    }
    //Afficher la liste des Hotes

    @RequestMapping (value = "/getHotes", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    @ResponseBody

    public List <Hote> getListHotes(){
        return HoteRep.findAll();
    }


    // Recuperer un Hote dont l'id est connu

    @RequestMapping (value = "/getHote/{id_Hote}", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    @ResponseBody

    public Hote Hote(@PathVariable ("id_Hote") int id_Hote) {
        for (Hote Hote : HoteRep.findAll()) {
            if (Hote.getId_voy()==id_Hote ){
                return Hote;
            }
        }

        System.out.println("Le Hote n'existe pas");
        return null;
    }

    //Ajouter un Hote dans la liste

    @PostMapping("/addHote")

    public void addHote(@RequestBody Hote Hote) {

        HoteRep.save(Hote);

    }

    //Supprimer un Hote de la liste

    @RequestMapping(value = "/delHote/{id_Hote}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)

    public void supprimerHote(@PathVariable("id_Hote") int id_Hote) throws Exception{

        for (Hote Hote : HoteRep.findAll()) {

            if(Hote.getId_voy() == id_Hote) {

                HoteRep.deleteById(Hote.getId_voy());
            }

            System.out.println("le Hote n'existe pas !");

        }
    }
}
