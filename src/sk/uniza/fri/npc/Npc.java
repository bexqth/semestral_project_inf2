package sk.uniza.fri.npc;

import sk.uniza.fri.matter.ShopItem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Generická trieda pre určenie, s akým typom predmetu sa bude pracovať
 *
 * @param <E>
 */
public class Npc<E extends ShopItem> {

    private Scanner scanner;
    private String textFile;
    private final ArrayList<E> shopItems;

    private ArrayList<Character> options;
    private String question;
    private String dialog;

    /**
     * Bezparametrický konštruktor vytvárajúci ArrayList pre predmety
     */
    public Npc() {
        this.shopItems = new ArrayList<>();
        this.question = "Good afternoon, what would you like?";
    }

    /**
     * Metóda vráti dialog
     *
     * @return this.dialog
     */
    public String getDialog() {
        this.createDialog();
        return this.dialog;
    }

    /**
     * Metóda vytvorí textovú reprezentáciu možností
     */
    public void createDialog() {
        this.dialog = "";
        this.createOption();
        this.dialog += this.question + "\n";
        for (int i = 0; i < this.shopItems.size(); i++) {
            this.dialog += this.options.get(i) + ") " + this.shopItems.get(i).getName() + " | " + "health - " + this.shopItems.get(i).getHealth() + " | " + "price - " + this.shopItems.get(i).getPrice() + "\n";
        }
    }

    /**
     * Metóda vytvorí abecedné poradie možností
     */
    public void createOption() {
        this.options = new ArrayList<>();
        for (int i = 0; i < this.shopItems.size(); i++) {
            this.options.add( ( char )(97 + i));
        }
    }

    /**
     * Vráti ArrayList predmetov
     *
     * @return this.shopItems
     */
    public ArrayList<E> getShopItems() {
        return this.shopItems;
    }

    public E sellItem(String option) {
        for (int i = 0; i < this.shopItems.size(); i++) {
            if (this.shopItems.get(i).getName().equals(option)) {
                return this.shopItems.get(i);
            }
        }
        return null;
    }

    /**
     * Metóda pridá do ArrayListu predmetov predmet zadaný v konštruktori
     *
     * @param item
     */
    public void addItem(E item) {
        this.shopItems.add(item);
    }
}
