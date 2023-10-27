package sk.uniza.fri.matter;

import java.util.ArrayList;

/**
 * Trieda predstavujúca inventár hráča
 */

public class Inventory {

    private ArrayList<Item> items;

    /**
     * Bezparametrický konštruktor, definujúci priestor pre uloženie predmetov
     */
    public Inventory() {
        this.items = new ArrayList<>();
    }

    /**
     * Metóda vracajúci ArrayList predmetov
     *
     * @return this.items;
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * Metóda, ktorá zabezpečuje pridanie predmetu do inventára na základe parametra
     *
     * @param newItem
     */
    public void addItem(Item newItem) {
        this.items.add(newItem);
    }

    /**
     * Metóda odstráni predmet z inventára na základe parametra
     *
     * @param item
     */
    public void deleteItem(Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (item == this.items.get(i)) {
                this.items.remove(item);
                break;
            }
        }
    }

    /**
     * Vráti textovú reprezentáciu jedla nachádzajúceho sa v inventári
     *
     * @return list
     */
    public String showFood() {
        String list = "";
        int n = 0;
        for (Item item : this.items) {
            if (item instanceof Food) {
                n++;
                list += item.getName() + ", ";
            }
        }
        if (n == 0) {
            list = "none\n";
        }
        return list;

    }

    /**
     * Predstavuje textovú reprezentáciu všetkých predmetov nachádzajúcich sa v inventári
     *
     * @return
     */
    public String showItself() {
        String zoznam = "";
        if (this.items.size() == 0) {
            return "no items";
        } else {
            for (Item item : this.items) {
                if (item == null) {
                    return "none";
                }
                zoznam += item.getName() + ", ";
            }
        }
        return zoznam;
    }
}
