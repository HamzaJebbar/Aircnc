package com.example.model;


import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
public class HoteService {
    HoteRepository hoteRep;
    AppartementRepository aptRep;

    public HoteService(HoteRepository hoteRep, AppartementRepository aptRep) {

        this.hoteRep = hoteRep;
        this.aptRep = aptRep;
    }
    //Afficher la liste des Hotes

    @RequestMapping (value = "/getHotes", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    public List <Hote> getListHotes(){

        return hoteRep.findAll();
    }


    // Recuperer un Hote dont l'id est connu

    @RequestMapping (value = "/getHote/{id_Hote}", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    public Hote Hote(@PathVariable ("id_Hote") int id_Hote) {
        for (Hote Hote : hoteRep.findAll()) {
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

        hoteRep.save(Hote);
    }

    //Supprimer un Hote de la liste

    @RequestMapping(value = "/delHote/{id_Hote}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void supprimerHote(@PathVariable("id_Hote") int id_Hote) throws Exception{
        int id = -1;
        for (Hote Hote : hoteRep.findAll()) {
            if(Hote.getId_voy() == id_Hote) {

                id = id_Hote;
            }
        }
        if(id!=-1)
            hoteRep.deleteById(id);
    }

    @RequestMapping(value = "/addHoteApt/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Hote addHoteApt(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
        Hote h = null;
        Appartement a = null;
        for (Appartement apt: aptRep.findAll()){
            if(apt.getId_Appartement() == id_Appartement)
                for (Hote hote : hoteRep.findAll()) {
                    if(hote.getId_voy() == id_Voyageur) {

                        h = hote;
                        a = apt;
                    }

                }
        }
        if(h!=null && a!=null){
            h.getAppartements().add(a);
            a.setHote(h);
            hoteRep.save(h);
            aptRep.save(a);
            return h;
        }
        return null;
    }

    @RequestMapping(value = "/rmHoteApt/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Hote rmHoteApt(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
        Hote h = null;
        Appartement a = null;
        for (Hote hote : hoteRep.findAll()) {
            if(hote.getId_voy() == id_Voyageur) {
                for(Appartement apt: hote.getAppartements()){
                    if(apt.getId_Appartement()==id_Appartement){

                        h = hote;
                        a = apt;
                    }
                }
            }
        }
        if(h!=null && a!=null){
            h.getAppartements().remove(a);
            a.setHote(null);
            hoteRep.save(h);
            return h;
        }
        return null;
    }


}
