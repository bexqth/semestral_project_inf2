package sk.uniza.fri.matter;

/**
 * Trieda predstavujúca všeobecný predmet
 */
public class Item {
    private final String name;

    /**
     * Parametrický konštruktor definujúci meno
     *
     * @param name
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Metóda vracajúca meno predmetu
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

}
