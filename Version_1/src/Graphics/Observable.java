package Graphics;

public interface Observable {
    /**
     * Permet d'attahcer un observateur
     * @param o l'observateur
     */
    void attacheObservateur(Observateur o);

    /**
     * Permet de dettacher un observateur
     * @param o l'observateur
     */
    void detacheObservateur(Observateur o);

    /**
     * permet de nottifier tous les observateurs
     */
    void notifieObservateurs();

    void notifieTimers();


}
