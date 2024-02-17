    package ZooFantastique.models.enclos;
    /**
     * L'énumération Proprete représente les niveaux de propreté d'un enclos dans le zoo fantastique.
     * Elle peut être MAUVAIS, CORRECT ou BON.
     */
    public enum Proprete {

        MAUVAIS, CORRECT, BON;
        /**
         * Récupère le niveau de propreté précédent.
         *
         * @return Le niveau de propreté précédent, sauf si le niveau actuel est MAUVAIS, auquel cas il retourne MAUVAIS.
         */
        public Proprete getPrevious() {
            if(this != Proprete.MAUVAIS){
                return Proprete.values()[this.ordinal() - 1];
            }
            return Proprete.MAUVAIS;
        }

    }
