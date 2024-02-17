package ZooFantastique.models;

import ZooFantastique.models.enclos.Enclos;

import java.util.ArrayList;

/**
 * La classe {@code ZooFantastique} représente un zoo fantastique, avec un nom,
 * un propriétaire de type {@link MaitreDeZoo}, un nombre maximal d'enclos, et
 * une liste d'enclos.
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see MaitreDeZoo
 * @see Enclos
 */
public class ZooFantastique {

    /**
     * Le nom du zoo fantastique.
     */
    private String nom;

    /**
     * Le propriétaire du zoo, de type {@code MaitreDeZoo}.
     */
    private MaitreDeZoo proprietaire;

    /**
     * Le nombre maximal d'enclos autorisés dans le zoo.
     */
    private int nbMaxEnclos;

    /**
     * La liste des enclos présents dans le zoo.
     */
    private ArrayList<Enclos> listeDesEnclos;

    /**
     *
     * @param nbMaxEnclos
     */
    public ZooFantastique(int nbMaxEnclos) {
        this.nbMaxEnclos = nbMaxEnclos;
        this.listeDesEnclos = new ArrayList<Enclos>();
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères du zoo fantastique.
     *
     * @return Une représentation du zoo fantastique.
     */
    @Override
    public String toString() {
        return "ZooFantastique{" +
                "nom='" + nom + '\'' +
                ", proprietaire=" + proprietaire +
                ", nbMaxEnclos=" + nbMaxEnclos +
                ", listeDesEnclos=" + listeDesEnclos +
                '}';
    }

    /**
     * Met à jour le zoo fantastique.
     * TODO : Implémenter la logique de mise à jour spécifique.
     */
    public void updateZoo() {
        // TODO : Implémenter la logique de mise à jour spécifique.
    }

    public boolean hasName(){
        return nom != null;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Enclos> getListeDesEnclos() {
        return listeDesEnclos;
    }

    public void addEnclos(Enclos enclos){
        listeDesEnclos.add(enclos);
    }

}
