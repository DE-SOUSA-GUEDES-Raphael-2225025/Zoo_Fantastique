package ZooFantastique.models.interfaces;

/**
 * L'interface {@code ISwim} définit le comportement des entités capables de nager.
 * Les classes qui implémentent cette interface doivent fournir une implémentation
 * spécifique de la méthode {@link #swim()}.
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see ISwim
 */
public interface ISwim {

    /**
     * Définit le comportement de la nage.
     */
    void swim();
}