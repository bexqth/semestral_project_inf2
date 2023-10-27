package sk.uniza.fri.mission;

/**
 * Trieda pre misiu, ktorú môže hráš splniť
 */
public class Mission {
    private boolean done;
    private String text;
    private String name;

    /**
     * Parametrický konštruktor definujúci meno a popis misie
     *
     * @param name
     * @param text
     */
    public Mission(String name, String text) {
        this.name = name;
        this.text = text;
    }

    /**
     * Metóda vracajúca meno misie
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metóda vracajúca popis misie
     *
     * @return this.text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Metóda vracajúca boolean hodnotu či je misia ukončená
     *
     * @return this.done
     */
    public boolean getDone() {
        return this.done;
    }

    /**
     * Metóda, ktorá nastaví novú boolean hodnotu misie
     *
     * @param b
     */
    public void setDone(boolean b) {
        this.done = b;
    }

}
