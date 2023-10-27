package sk.uniza.fri.matter;

/**
 * Trieda jedla dediaca vlastnosti
 */
public class Food extends ShopItem {

    /**
     * Parametrický konštruktor na základe dedičnosti
     *
     * @param name
     * @param health
     * @param price
     */
    public Food(String name, int health, int price) {
        super(name, health, price);
    }

}
