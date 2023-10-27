package sk.uniza.fri.matter;

/**
 * Trieda brnenia dediaca vlastnosti
 */
public class Armor extends ShopItem {

    /**
     * Parametrický konštruktor na základe dedičnosti
     *
     * @param name
     * @param health
     * @param price
     */
    public Armor(String name, int health, int price) {
        super(name, health, price);
    }

}
