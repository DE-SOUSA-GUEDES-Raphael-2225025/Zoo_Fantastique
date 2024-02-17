package ZooFantastique.models.creatures.vivipares.lycanthrope;

import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.vivipares.Vivipare;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.IRun;
import javafx.application.Platform;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * La classe {@code Lycanthrope} représente une créature fantastique de type vivipare,
 * possiblement un être capable de se transformer en une forme animale, qui a la
 * capacité de courir. Elle étend les fonctionnalités de la classe {@link Vivipare}
 * et implémente l'interface {@link IRun}.
 * <p>
 * Le Lycanthrope peut avoir un processus de mise bas particulier et possède une
 * agilité notable pour courir.
 * </p>
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see Vivipare
 * @see IRun
 */
public class Lycanthrope extends Vivipare implements IRun {

    private double force;
    private double facteurDomination;
    private RangDomination rang;
    private double niveau;
    private double impétuosité;
    private Meute meute;

    private static ArrayList<String> moisSaisonAmour = new ArrayList<>(Arrays.asList("Février", "Mars", "Avril", "Mai"));


    public Lycanthrope(Enclos enclos) {
        super("Lycanthrope", enclos, "Awoooo!");
        Random random = new Random();
        rang = RangDomination.values()[random.nextInt(1,RangDomination.values().length)];
        initStats();
    }


    public Lycanthrope(Enclos enclos, Sexe sexe){
        this(enclos);
        this.setSexe(sexe);
    }


    public Lycanthrope(Enclos enclos, RangDomination rang){
        this(enclos);
        this.rang = rang;
        initStats();
    }

    /**
     * Initialise les statistiques du Lycanthrope.
     */
    public void initStats(){
        Random random = new Random();
        impétuosité = random.nextDouble();
        facteurDomination = rang.getRangPuissance() * 10 + 5;
        force = random.nextInt(1,50);
        niveau = force * facteurDomination * rang.getRangPuissance();
        this.setTaille(new Random().nextInt(1,3));
        this.setPoids(new Random().nextInt(100,250));
    }


    @Override
    public void run() {
        Notifications.create().title("Court").text("Le Lycanthrope court").position(Pos.TOP_CENTER).showInformation();
    }

    public void hurler() {
        meute.notifyHurlement(this);
    }

    public void rejoindreMeute(Meute meute) {
        this.meute = meute;
        meute.getMembres().add(this);
    }

    public void quitterMeute() {
        meute.removeMembre(this);
        meute = null;
        rang = null;
    }

    @Override
    public void die() {
        if(meute != null) {
            meute.getMembres().remove(this);
            meute.updateCoupleAlpha(meute.getStrongestMale());
            meute = null;
        }
        super.die();
    }

    @Override
    public void setEnclos(Enclos enclos){
        if(enclos != this.getEnclos()){
            quitterMeute();
        }
        super.setEnclos(enclos);
    }


    /**
     * Indique si le lycanthrope peut dominer un autre lycanthrope.
     * @param lycanthrope
     * @return
     */
    public boolean canDominate(Lycanthrope lycanthrope){
        if(lycanthrope.getSexe() == Sexe.FEMELLE && lycanthrope.rang == RangDomination.α) return false;
        if(lycanthrope.getRang() == RangDomination.α && this.getRang() == RangDomination.α) return false;
        if(lycanthrope == this) return false;
        if(lycanthrope.getRang() == RangDomination.α && this.getSexe() == Sexe.FEMELLE) return false;
        if(lycanthrope.getSexe() == Sexe.MALE && lycanthrope.rang == RangDomination.α) return true;
        return (this.force >= lycanthrope.force);
    }


    /**
     * Tente de dominer un lycanthrope.
     * @param lycanthrope
     * @return
     */
    public boolean attemptDomination(Lycanthrope lycanthrope){
        if(this.niveau > lycanthrope.niveau || lycanthrope.getRang() == RangDomination.ω){
            achieveDomination(lycanthrope);
            return true;
        }else{
            if(lycanthrope.getRang() == RangDomination.α){
                Random random = new Random();
                if(random.nextDouble() < 0.1){ // 10% de chance quitter la meute si domine par un alpha
                    quitterMeute();
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * Domine un lycanthrope.
     * @param lycanthrope
     */
    public void achieveDomination(Lycanthrope lycanthrope){
        if(lycanthrope.getRang() == RangDomination.α){
            this.getMeute().updateCoupleAlpha(this);
            return;
        }

        /* Echange des rangs de domination */
        if(getRang().isInferior(lycanthrope.getRang())){
            RangDomination rangA = getRang();
            setRang(lycanthrope.getRang());
            lycanthrope.setRang(rangA);
        }

        /* Mise a jour des facteurs de domination */
        double dominationFactorToTake = new Random().nextInt(3) + 1;
        lycanthrope.facteurDomination -= dominationFactorToTake;
        facteurDomination+=dominationFactorToTake;
    }



    public void transformer() {
        // TODO : Implémenter la transformation du Lycanthrope
    }


    /**
     * Permet au licanthrope de repondre a un hurlement.
     */
    public void hurlementRetour() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications.create().position(Pos.TOP_LEFT).title("Hurlement").text("Un lycanthrope hurle au loin").showInformation();

            }
        });
    }

    public RangDomination getRang() {
        return rang;
    }

    public void setRang(RangDomination rang) {
        this.rang = rang;
    }

    public Meute getMeute() {
        return meute;
    }

    public double getForce() {
        return force;
    }

    public double getFacteurDomination() {
        return facteurDomination;
    }

    public static ArrayList<String> getMoisSaisonAmour() {
        return moisSaisonAmour;
    }

    public void setMeute(Meute meute) {
        this.meute = meute;
    }

    public double getNiveau() {
        return niveau;
    }
}