package com.mycompany.tennis.core;

import java.util.List;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;

public class TestDeConnection {
    public static void main(String... args){
    	
    	JoueurService joueurService=new JoueurService();
    	Joueur noah=new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannik");
        noah.setSexe('H');
        joueurService.createJoueur(noah);
    	
       /*JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
       Joueur bartoli = joueurRepository.getById(21L);
       System.out.println(bartoli.getPrenom()+" "+bartoli.getNom());*/
       
       /*JoueurRepositoryImpl joueurRepository2=new JoueurRepositoryImpl();
       Joueur noah=new Joueur();
       noah.setNom("Noah");
       noah.setPrenom("Yannik");
       noah.setSexe('H');
       joueurRepository2.create(noah);*/
       
      /* JoueurRepositoryImpl joueurRepository3=new JoueurRepositoryImpl();
       Joueur noah2= joueurRepository.getById(52L);

       noah.setPrenom("Yannick");
  
       joueurRepository3.update(noah2);*/
       
       /*joueurRepository3.delete(52L);
       
       List<Joueur> liste=joueurRepository3.list();
       
       for (Joueur joueur : liste) {
    	   System.out.println(joueur.getPrenom()+" "+joueur.getNom());
    	   
       }*/
    }
}

