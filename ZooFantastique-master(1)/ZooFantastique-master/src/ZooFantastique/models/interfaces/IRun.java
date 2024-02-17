package ZooFantastique.models.interfaces;

/**
 * L'interface {@code IRun} définit le comportement des entités capables de courir.
 * Les classes qui implémentent cette interface doivent fournir une implémentation
 * spécifique de la méthode {@link #run()}.
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see IRun
 */
public interface IRun {

    /**
     * Définit le comportement de la course.
     */
    void run();
}