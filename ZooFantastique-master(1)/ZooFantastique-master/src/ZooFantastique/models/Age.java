package ZooFantastique.models;

import ZooFantastique.models.enclos.Proprete;

/**
 * L'énumération {@code Age} représente les différents stades de vie d'une entité.
 * Les valeurs possibles sont {@code JEUNE}, {@code ADULTE}, et {@code VIEUX}.
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see Age
 */
public enum Age {

    /**
     * Représente le stade de vie "JEUNE".
     */
    JEUNE,

    /**
     * Représente le stade de vie "ADULTE".
     */
    ADULTE,

    /**
     * Représente le stade de vie "VIEUX".
     */
    VIEUX,

    MORT;


    public Age getNextAge() {
        if(this != Age.VIEUX){
            return Age.values()[this.ordinal() + 1];
        }
        return Age.VIEUX;
    }
}
