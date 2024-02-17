package ZooFantastique.models.enclos;

import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Meute;
import Exceptions.EnclosFullException;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe {@code Enclos} représente un espace clos destiné à héberger des créatures.
 * Elle contient des informations telles que le nom, la superficie, le nombre maximal
 * de créatures autorisées, le nombre actuel de créatures présentes, le degré de propreté,
 * et la liste des créatures présentes.
 * <p>
 * @author [Votre Nom]
 * @version 1.0
 * @see Creature
 * @see Proprete
 */
public class Enclos {

    /**
     * Le nom de l'enclos.
     */
    private String nom;

    /**
     * La superficie de l'enclos.
     */
    private double superficie;

    /**
     * Le nombre maximal de créatures autorisées dans l'enclos.
     */
    private int nbCreatureMax;

    /**
     * Le nombre actuel de créatures présentes dans l'enclos.
     */
    private int nbCreaturePresente;

    /**
     * Le degré de propreté de l'enclos.
     */
    private Proprete propreteDegre;

    /**
     * La liste des créatures présentes dans l'enclos.
     */
    private ArrayList<Creature> creaturesPresentes;

    private Meute meute;


    /**
     *
     * @param nom
     * @param nbCreatureMax
     */
    public Enclos(String nom, int nbCreatureMax) {
        this.nom = nom;
        this.superficie = new Random().nextInt(50) +  30;
        this.nbCreatureMax = (int) superficie / 3;
        this.nbCreaturePresente = 0;
        this.propreteDegre = Proprete.BON;
        creaturesPresentes = new ArrayList<Creature>();
    }

    public Enclos(String nom) {
        this(nom, 10);
    }



    public void addMeute(Meute meute){
        this.meute = meute;
    }

    @Override
    public String toString() {
        return "[Enclos] " + nom + ", superficie :" + superficie + ", nombre de créature : " + nbCreaturePresente;
    }

    public String getNom() {
        return nom;
    }

    /**
     * Ajoute une créature à la liste des créatures présentes dans l'enclos.
     *
     * @param creature La créature à ajouter.
     */
    public void addCreature(Creature creature) throws EnclosFullException {
        if(nbCreaturePresente >= nbCreatureMax){
            throw new EnclosFullException("Limite de créature atteinte !");
        }
        nbCreaturePresente++;
        creaturesPresentes.add(creature);
    }

    /**
     * Supprime une créature de la liste des créatures présentes dans l'enclos.
     *
     * @param creature La créature à supprimer.
     */
    public void removeCreature(Creature creature) {
        creaturesPresentes.remove(creature);
        nbCreaturePresente--;
    }

    public void lowerProperty(){
        propreteDegre = propreteDegre.getPrevious();
    }

    public ArrayList<Creature> getCreaturesPresentes() {
        return creaturesPresentes;
    }

    /**
     * Effectue l'entretien de l'enclos en fonction de certaines conditions.
     */
    public void entretient() {
        if (creaturesPresentes.isEmpty() && propreteDegre.equals(Proprete.MAUVAIS)) {
            propreteDegre = Proprete.BON;
        }
    }

    public boolean isEmpty() {
        return creaturesPresentes.isEmpty();
    }

    public void setName(String s) {
        this.nom= s;
    }

    public void setPropreteDegre(Proprete p) { this.propreteDegre = p;}

    public int getNbCreatureMax(){
        return nbCreatureMax;
    }

    public int getNbCreaturePresente(){
        return nbCreaturePresente;
    }

    public Proprete getPropreteDegre(){
        return propreteDegre;
    }

    public void clean(){
        propreteDegre = Proprete.BON;
    }

    public void nourrirAllCreature(){
        for(Creature creature : getCreaturesPresentes()){
            creature.feed();
        }
    }

    public double getSuperficie() {
        return superficie;
    }

    public Meute getMeute() {
        return meute;
    }

    public boolean isFull(){
        return nbCreaturePresente >= nbCreatureMax;
    }

    public void setMeute(Meute meute) {
        this.meute = meute;
    }
}


