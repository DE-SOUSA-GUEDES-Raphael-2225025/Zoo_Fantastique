package ZooFantastique.models.interfaces;

/**
 * L'interface {@code IRevive} définit le comportement des entités capables d'être
 * ressuscitées. Les classes qui implémentent cette interface doivent fournir une
 * implémentation spécifique de la méthode {@link #revive()}.
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see IRevive
 */
public interface IRevive {

    /**
     * Définit le comportement de résurrection.
     */
    void revive();
}
