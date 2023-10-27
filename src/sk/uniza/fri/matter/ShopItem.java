package sk.uniza.fri.matter;

/**
 * Trieda predstavujúca predmet, ktorého hlavná špecifikácia je, že je ho možné kúpiť
 */

public class ShopItem extends Item {
    private String name;
    private int price;
    private int health;

    /**
     * Parametrický konštruktor definujúci meno, zdravie, cenu
     *
     * @param name
     * @param health
     * @param price
     */
    public ShopItem(String name, int health, int price) {
        super(name);
        this.price = price;
        this.health = health;
    }

    /**
     * Metóda vracajúca aktuálnu hodnotu zdravia
     *
     * @return this.health;
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Metóda nastaví novú hodnotu zdravia
     *
     * @param v
     */
    public void setHealth(int v) {
        this.health = v;
    }

    /**
     * Metóda vracajúca aktuálnu hodnotu peňazí
     *
     * @return this.price;
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Vráti textovú reprezentáciu
     *
     * @return this.name, this.health, this.price
     */
    public String toString() {
        return this.name + " | " + this.health + " | " + this.price + "\n";
    }

}
