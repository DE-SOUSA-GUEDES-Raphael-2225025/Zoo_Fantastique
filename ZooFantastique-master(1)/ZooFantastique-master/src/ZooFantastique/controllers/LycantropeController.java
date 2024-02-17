package ZooFantastique.controllers;

import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.lycanthrope.RangDomination;
/**
 * La classe LycantropeController gère les opérations liées aux lycanthropes dans le zoo fantastique.
 */
public class LycantropeController {


    /**
     * Tente une domination entre deux lycanthropes.
     *
     * @param dominant Le lycanthrope dominant.
     * @param dominé   Le lycanthrope dominé.
     */
    public void attempDomination(Lycanthrope dominant, Lycanthrope dominé){
        boolean dominationSuccess = dominant.attemptDomination(dominé);

        if(dominé.getRang() == RangDomination.α && dominationSuccess){ // Le lycantrope alpha a été dominé
            System.out.println("Le lycantrope de rang " + dominé.getRang() + " est devenu le nouveau male du couple alpha");
        }else if(dominationSuccess){ // Le lycantrope a dominé
            System.out.println("Lycantrope de rang " + dominant.getRang() + " a dominé le lycantrope de rang " + dominé.getRang());
        }else{ // Le lycantrope n'a pas dominé
            System.out.println("Lycantrope de rang " + dominé.getRang() + " se montre agressif envers un autre lycanthrope");
        }
    }
}
