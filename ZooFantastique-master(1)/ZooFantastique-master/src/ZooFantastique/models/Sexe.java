package ZooFantastique.models;

import java.util.Random;

public enum Sexe {

    MALE,FEMELLE;

    public Sexe getRandomSexe(){
        if(new Random().nextInt(2) == 0) return Sexe.FEMELLE;
        return Sexe.MALE;
    }
}
