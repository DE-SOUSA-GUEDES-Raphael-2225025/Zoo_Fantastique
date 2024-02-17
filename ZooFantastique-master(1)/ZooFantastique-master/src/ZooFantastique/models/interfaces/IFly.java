package ZooFantastique.models.interfaces;

/**
 * L'interface {@code IFly} définit le comportement des entités capables de voler.
 * Les classes qui implémentent cette interface doivent fournir une implémentation
 * spécifique de la méthode {@link #fly()}.
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see IFly
 */
public interface IFly {

    /**
     * Définit le comportement de vol.
     */
    void fly();
}