package ZooFantastique.models.creatures.vivipares.lycanthrope;

import java.util.ArrayList;
import java.util.Random;

public class CoupleAlpha {

    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;

    public CoupleAlpha(Lycanthrope maleAlpha, Lycanthrope femelleAlpha) {
        this.maleAlpha = maleAlpha;
        this.femelleAlpha = femelleAlpha;
    }


    public Lycanthrope getMaleAlpha() {
        return maleAlpha;
    }


    public void setMaleAlpha(Lycanthrope maleAlpha) {
        this.maleAlpha = maleAlpha;
        maleAlpha.setRang(RangDomination.α);
    }


    public Lycanthrope getFemelleAlpha() {
        return femelleAlpha;
    }


    public void setFemelleAlpha(Lycanthrope femelleAlpha) {
        this.femelleAlpha = femelleAlpha;
        femelleAlpha.setRang(RangDomination.α);
    }

    /**
     * Reproduit le couple alpha
     * Si l'enclos est plein, la reproduction ne se fait pas
     */
    public void reproduce(){
        int nbChildren = new Random().nextInt(1,7);
        ArrayList<Lycanthrope> childrenList = new ArrayList<>();
        for(int i = 0; i < nbChildren; i++){
            if(maleAlpha.getEnclos().getNbCreaturePresente() >= maleAlpha.getEnclos().getNbCreatureMax()){
                break;
            }

            if(!maleAlpha.getMeute().containsRang(RangDomination.β)){
                new Lycanthrope(maleAlpha.getEnclos(), RangDomination.β).rejoindreMeute(maleAlpha.getMeute());
            }else{
                new Lycanthrope(maleAlpha.getEnclos(), RangDomination.γ).rejoindreMeute(maleAlpha.getMeute());
            }
        }
    }
}
