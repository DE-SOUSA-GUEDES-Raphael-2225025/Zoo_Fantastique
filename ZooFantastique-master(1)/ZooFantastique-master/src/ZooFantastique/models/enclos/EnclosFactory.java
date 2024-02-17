package ZooFantastique.models.enclos;

public class EnclosFactory {


    public Enclos build(String typeEnclos, String nomEnclos){
        switch(typeEnclos){
            case "Aquarium":
                return new Aquarium(nomEnclos);
            case "Voliere":
                return new Voliere(nomEnclos);
            default:
                return new Enclos(nomEnclos);
        }
    }
}

