package ZooFantastique.models.creatures.vivipares.lycanthrope;

import java.util.ArrayList;

public enum RangDomination {
    α(26),β(25),γ(24),δ(23),ε(22),ζ(21),η(20),θ(19),ι(18),κ(17),λ(16),μ(15),ν(14),ξ(13),ο(12),π(11),ρ(10),σ(9),τ(8),υ(7),φ(6),χ(5),ψ(4),ω(3);

    private int rangPuissance;

    private RangDomination(int rangPuissance){
        this.rangPuissance = rangPuissance;
    }
    public RangDomination previousRang(){
        if(this != RangDomination.ω){
            return RangDomination.values()[this.ordinal()+1];
        }else{
            return RangDomination.ω;
        }
    }

    public int getRangPuissance() {
        return rangPuissance;
    }

    public RangDomination nextRang(){
        if(this != RangDomination.α){
            return RangDomination.values()[this.ordinal()-1];
        }else{
            return RangDomination.α;
        }
    }

    public boolean isInferior(RangDomination rang){
        RangDomination[] rangList = RangDomination.values();
        for(RangDomination rangDomination : rangList){
            if(rangDomination == this) return false;
            if(rangDomination == rang) return true;
        }
        return false;
    }
}
