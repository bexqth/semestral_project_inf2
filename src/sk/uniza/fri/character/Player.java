package sk.uniza.fri.character;

import sk.uniza.fri.matter.Armor;
import sk.uniza.fri.matter.Inventory;
import sk.uniza.fri.matter.Item;
import sk.uniza.fri.world.Room;


/**
 * Triedy vytvorí hráča, dedené vlastnosti na základe dedičnosti s triedou "Character"
 */

public class Player extends Character {
    private final Inventory inventory;
    private final Room room;
    private int money;
    private boolean hasWeapon;
    private Armor armor;
    private boolean hasArmor;
    private int leftDamage;

    /**
     * Parametrický koštruktor na základe vytvorenej dedičnosti s triedou "Character"
     * Pridané dalšie dva parametre, dané pre túto triedu
     *
     * @param levelFile
     * @param room
     * @param inventory
     */
    public Player(String levelFile, Room room, Inventory inventory) {
        super(levelFile);
        this.room = room;
        this.inventory = inventory;
        this.room.setActualRoom(room);
        this.money = 100;
        this.armor = null;
    }

    /**
     * Metóda pridá brnenie hráčovy
     *
     * @param armor
     */
    public void addArmor(Armor armor) {
        this.armor = armor;
    }

    /**
     * Vráti hodnotu brnenia
     *
     * @return this.armor
     */
    public Armor getArmor() {
        return this.armor;
    }

    /**
     * Skontroluje či má hráč v intentári brnenie, ak áno, pridá ho
     */
    public void checkForArmor() {
        for (int i = 0; i < this.inventory.getItems().size(); i++) {
            if (this.inventory.getItems().get(i) instanceof Armor armor) {
                this.addArmor(new Armor(armor.getName(), armor.getHealth(), armor.getPrice()));
                this.hasArmor = true;
                this.putOutOfInventary(armor);
            }
        }
    }

    /**
     * Vráti boolean hodnotu, či má hráč na sebe brnenie
     *
     * @return this.hasArmor
     */
    public boolean getHasArmor() {
        return this.hasArmor;
    }

    /**
     * Metóda, zníži zdravie nepriatelovi na základe parametrov
     *
     * @param damage
     * @param character
     */
    public void fight(int damage, Character character) {
        character.lowerHealth(damage);
        if (character instanceof Enemy enemy) {
            enemy.generateAttack();
            this.lowerHealth(enemy.getGeneratedDamage());
        }
    }

    /**
     * Nastaví novú hodnotu, či má hráč zbraň alebo nie
     *
     * @param v
     */
    public void setHasWeapon(boolean v) {
        this.hasWeapon = v;
    }

    /**
     * Vráti boolean hodnotu, či má hráč zbraň alebo nie
     *
     * @return this.hasWeapon
     */
    public boolean getHasWeapon() {
        return this.hasWeapon;
    }

    /**
     * Metóda zníži zdravie brnenia hráča
     *
     * @param howMuch
     */
    public void loverArmorHealth(int howMuch) {
        try {
            if (howMuch > this.armor.getHealth() && this.armor.getHealth() > 0) {
                this.leftDamage = (howMuch - this.armor.getHealth());
                this.armor.setHealth(0);
                this.armor = null;
                this.hasArmor = false;
            } else {
                if (this.armor.getHealth() == 0 || this.armor.getHealth() < 0) {
                    this.armor = null;
                    this.hasArmor = false;
                } else {
                    this.armor.setHealth(this.armor.getHealth() - howMuch);
                    this.leftDamage = 0;
                }
            }

        } catch (NullPointerException e) {
            //System.out.println();
        }
    }

    /**
     * Override metóda, pre danú triedu, ktorá zníži zdravie hráča
     *
     * @param howMuch
     */
    @Override
    public void lowerHealth(int howMuch) {
        if (this.hasArmor) {
            this.loverArmorHealth(howMuch);
            if (this.leftDamage > 0) {
                this.setHealth(this.getHealth() - this.leftDamage);
            }
        } else {
            this.setHealth(this.getHealth() - howMuch);
        }
    }

    /**
     * Pridá zdravie hráčovi
     *
     * @param howMuch
     */
    public void addHeatlh(int howMuch) {
        this.setHealth(this.getHealth() + howMuch);
        this.restoreHealth();
    }

    /**
     * Vráti boolean hodnotu, či sa množstvo peňazí rovná nule
     *
     * @return this.money == 0
     */
    public boolean noMoney() {
        return this.money == 0;
    }

    /**
     * Vráti hodnotu peňazí
     *
     * @return this.money
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Vráti textovú reprezentáciu hodnoty preňazí
     *
     * @return this.money
     */
    public String printGetMoney() {
        return "You have " + String.valueOf(this.money) + " coins";
    }

    /**
     * Zníži hodnotu peňazí, ktoré hráč má
     *
     * @param howMuch
     */
    public void lowerMoney(int howMuch) {
        this.money -= howMuch;
    }

    /**
     * Priráta parametrickú hodnotu, k pôvodnej hodnote peňazí
     *
     * @param howMuch
     */
    public void raiseMoney(int howMuch) {
        this.money += howMuch;
    }

    /**
     * Pridá do inventoráta hráča predmet definovaný ako parameter
     *
     * @param newItem
     */
    public void putInInventory(Item newItem) {
        this.inventory.addItem(newItem);
    }

    /**
     * Odoberie z inventára predmet definovaný ako parameter
     *
     * @param item
     */
    public void putOutOfInventary(Item item) {
        this.inventory.deleteItem(item);
    }

    /**
     * Vráti textovú reprezentáciu inventorára
     *
     * @return this.inventory.showItself()
     */
    public String showInventory() {
        return this.inventory.showItself();
    }

    /**
     * Vráti textovú reprezentáciu len jedla v inventári
     *
     * @return this.inventory.showFood()
     */
    public String showFoodInInventory() {
        return this.inventory.showFood();
    }

    /**
     * Vráti hodnotu aktuálnej miestnosti
     *
     * @return this.room.getActualRoom();
     */
    public Room getActualRoom() {
        return this.room.getActualRoom();
    }

    /**
     * Posunie hráča smerom hore
     */
    public void goUp() {
        this.room.setActualRoom(this.room.getActualRoom().getRoomUp());
    }

    /**
     * Posunie hráča smerom dole
     */
    public void goDown() {
        this.room.setActualRoom(this.room.getActualRoom().getRoomDown());
    }

    /**
     * Posunie hráča smerom doľava
     */
    public void goLeft() {
        this.room.setActualRoom(this.room.getActualRoom().getRoomLeft());
    }

    /**
     * Posunie hráča smerom doprava
     */
    public void goRight() {
        this.room.setActualRoom(this.room.getActualRoom().getRoomRight());
    }

    /**
     * Vráti inventár hráča
     *
     * @return this.inventory;
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Metóda, ktorá v inventorá vyhľadá predmet, definovaný v parametri
     *
     * @param item
     * @return true/false
     */
    public boolean hasItem(Item item) {
        for (int i = 0; i < this.inventory.getItems().size(); i++) {
            if (this.inventory.getItems().get(i) == item) {
                return true;
            }
        }
        return false;
    }

}
