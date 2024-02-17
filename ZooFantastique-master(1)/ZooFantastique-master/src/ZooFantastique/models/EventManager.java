package ZooFantastique.models;

import ZooFantastique.controllers.CreatureController;
import ZooFantastique.controllers.EnclosController;
import ZooFantastique.controllers.LycantropeController;
import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.ovipares.Oeuf;
import ZooFantastique.models.creatures.ovipares.Ovipare;
import ZooFantastique.models.creatures.vivipares.Vivipare;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Meute;
import ZooFantastique.models.creatures.vivipares.lycanthrope.RangDomination;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.enclos.Proprete;
import ZooFantastique.ZooMain;
import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * La classe EventManager gère les événements se produisant dans le zoo fantastique.
 * Elle s'occupe de gérer les actions périodiques liées aux enclos, aux créatures et aux meutes.
 */
public class EventManager {

    private Random random;
    private final double P_ENCLOS_DEGRADE_BASE = (double) 1 /500;
    private final double P_CREATURE_DORT = (double) 1 /1000;
    private final double P_CREATURE_VIEILLIR = (double) 1 /2500;
    private final double P_CREATURE_MALADE = (double) 1 /2500;
    private final double P_CREATURE_MALADE_PERD_SANTE = (double) 1 /10;
    private final double P_CREATURE_A_FAIM = (double) 1 /1000;
    private final double P_OVIPARE_POND = (double) 1 /2000;
    private final double P_LYCANTROPE_HURLE = (double) 1 /2000;
    private final double P_LYCANTROPE_TENTE_DOMINATION = (double) 1 /200;
    private final double P_LYCANTROPE_REPRODUCE = (double) 1 /1000;
    private final double P_VIVIPARE_NAISSANCE = (double) 1 /1500;

    // Mettre les probas en variable final private avec le nom en majuscule
    //Ne pas oublier de mettre les notif pour les evenements
    // Cette classe est éxécute tout les x secondes
    public void handleZooEvent(){
        CopyOnWriteArrayList<Enclos> enclosListe = new CopyOnWriteArrayList<>(ZooMain.getZoo().getListeDesEnclos());
        CopyOnWriteArrayList<Creature> creatureListe = new CopyOnWriteArrayList<>();
        for (Enclos i : enclosListe){
            handleEnclotEvent(i);
        }
        for(Meute meute : ZooMain.getColonie()){
            handleMeuteEvent(meute);
        }
    }
    /**
     * Gère les événements liés à un enclos, tels que les actions périodiques sur les créatures présentes.
     *
     * @param enclos L'enclos à traiter.
     */
    public void handleEnclotEvent(Enclos enclos){
        CopyOnWriteArrayList<Creature> creatureList = new CopyOnWriteArrayList<>(enclos.getCreaturesPresentes());
        Iterator<Creature> iterator = creatureList.iterator();

        while(iterator.hasNext()){
            Creature creature = iterator.next();
            handleCreatureEvent(creature);
        }

        if(enclos.getPropreteDegre() != Proprete.MAUVAIS && new Random().nextDouble() <= P_ENCLOS_DEGRADE_BASE * enclos.getNbCreaturePresente()){
            enclos.lowerProperty();
            sendNotification("Enclos", "L'enclos \"" + enclos.getNom() + "\" se dégrade !\n" + "Pensez à le nettoyer !");
        }

        if(enclos.getMeute() == null){
            Lycanthrope male = null, femmelle = null;
            for(Creature creature : enclos.getCreaturesPresentes()){
                if(creature instanceof Lycanthrope){
                    Lycanthrope lycanthrope = (Lycanthrope) creature;
                    if(lycanthrope.getSexe() == Sexe.MALE) male = lycanthrope;
                    if(lycanthrope.getSexe() == Sexe.FEMELLE) femmelle = lycanthrope;
                }
            }
            if (male != null && femmelle != null) {
                new EnclosController().creerMeute(male, femmelle, enclos);
            }
        }
    }

    /**
     * Gère les évènements liés a une créature
     */
    public void handleCreatureEvent(Creature creature){
        random = new Random();

        if(creature instanceof Oeuf) return;
        if(creature.isSleeping()) return;

        if(creature instanceof Lycanthrope){
            handleLycanthropeEvent((Lycanthrope) creature);
        }

        /* ----- La créature vieillit ----- */
        if(random.nextDouble() <= P_CREATURE_VIEILLIR) {
            new CreatureController().viellir(creature);
        }

        /* ----- La créature dort ----- */
        if(random.nextDouble() <= P_CREATURE_DORT) {
            if(creature.isSleeping()){
                creature.wakeUp();
            } else {
                creature.sleep();
            }
        }

        /* ----- La créature tombe malade ----- */
        if(random.nextDouble() <= P_CREATURE_MALADE){
            if(creature.getEtat() != Etat.MALADE){
                creature.setEtat(Etat.MALADE);
                sendNotification("Créature", "La créature \"" + creature.getNom() + "\" viens de tomber malade.\nPensez à soigner les créatures malades !");
            }
        }

        /* ----- La créature perd de la santé ----- */
        if(creature.getEtat() == Etat.MALADE && random.nextDouble() <= P_CREATURE_MALADE_PERD_SANTE){
            creature.setSante(creature.getSante() - 1);
        }

        /* ----- La créature a faim ----- */
        if(!creature.isHungry() && random.nextDouble() <= P_CREATURE_A_FAIM){
            creature.setHungry(true);
            sendNotification("Créature", "La créature \"" + creature.getNom() + "\" a faim !");
        }

        /* ----- L'ovipare pond ----- */
        if(creature instanceof Ovipare && random.nextDouble() <= P_OVIPARE_POND && !creature.getEnclos().isFull() && creature.getSexe() == Sexe.FEMELLE){
            Ovipare ovipare = (Ovipare) creature;
            ovipare.lay();
            sendNotification("Créature", "L'ovipare \"" + ovipare.getNom() + "\" a pondu un oeuf !");
        }

        /* ----- Le vivipare donne naissance ----- */
        if(creature instanceof Vivipare && random.nextDouble() <= P_VIVIPARE_NAISSANCE && creature.getSexe() == Sexe.FEMELLE && !creature.getEnclos().isFull() && !(creature instanceof Lycanthrope)){
            Vivipare vivipare = (Vivipare) creature;
            vivipare.giveBirth();
            sendNotification("Créature", vivipare.getNom() + "\" a donné naissance !");
        }

    }
    /**
     * Gère les événements spécifiques aux lycanthropes, tels que le hurlement, la domination, etc.
     *
     * @param lycanthrope Le lycanthrope à traiter.
     */
    public void handleLycanthropeEvent(Lycanthrope lycanthrope){
        Random random = new Random();

        if(random.nextDouble() <= P_LYCANTROPE_HURLE && lycanthrope.getMeute() != null){
            lycanthrope.hurler();
        }

        if(random.nextDouble() <= P_LYCANTROPE_TENTE_DOMINATION && lycanthrope.getMeute() != null){
            ArrayList<Lycanthrope> possibleDomination = new ArrayList<>();
            for(Lycanthrope l : lycanthrope.getMeute().getMembres()){
                if(lycanthrope.canDominate(l)) possibleDomination.add(l);
            }
            if(possibleDomination.size() > 0){
                Lycanthrope lycanthropeDomine = possibleDomination.get(random.nextInt(possibleDomination.size()));
                new LycantropeController().attempDomination(lycanthrope, lycanthropeDomine);
            }
        }

        if(lycanthrope.getRang() != null && lycanthrope.getFacteurDomination() < lycanthrope.getRang().getRangPuissance() * 10 && lycanthrope.getRang() != RangDomination.α){
            lycanthrope.setRang(lycanthrope.getRang().previousRang());
        }
    }
    /**
     * Gère les événements liés à une meute, tels que la reproduction.
     *
     * @param meute La meute à traiter.
     */
    public void handleMeuteEvent(Meute meute){
        Random random = new Random();
        if(random.nextDouble() <= P_LYCANTROPE_REPRODUCE && Lycanthrope.getMoisSaisonAmour().contains(TimeManager.getMois())){
            meute.getCoupleAlpha().reproduce();
            sendNotification("Repoduction", "Un couple de lycanthrope a donné naissance à des nouveaux lycanthropes !");
        }
    }
    /**
     * Envoie une notification visuelle.
     *
     * @param title   Le titre de la notification.
     * @param message Le message de la notification.
     */
    public void sendNotification(String title, String message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications.create().text(message).showWarning();
            }
        });
    }
}
