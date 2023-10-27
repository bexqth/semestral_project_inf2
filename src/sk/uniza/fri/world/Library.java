package sk.uniza.fri.world;


/**
 * Precifická miestnosť - Knižnica
 */
public class Library extends Room {
    private boolean hasSword;

    /**
     * Bezparametrický konštruktor
     */
    public Library() {
        super("Library");
        this.hasSword = true;
    }

    /**
     * Nastaví novú hodnotu pre this.hasValue
     *
     * @param value
     */
    public void setHasSword(boolean value) {
        this.hasSword = value;
    }

    /**
     * Metóda skontroluje či sa v miestnosti nachádza meč
     */
    public void checkSword() {
        if (this.hasSword) {
            this.setDescripton("Pick up your sword, which was left for you.");
        } else {
            this.setDescripton("You picked up the sword");
        }
    }
}
