package sk.uniza.fri.world;

import sk.uniza.fri.character.Enemy;
import sk.uniza.fri.character.ItemEnemy;
import sk.uniza.fri.matter.Armor;
import sk.uniza.fri.matter.Food;
import sk.uniza.fri.matter.Item;
import sk.uniza.fri.npc.Npc;

/**
 * 18. 3. 2022 - 11:55
 *
 * @author Terezia
 */

/**
 * Trieda vytvorí celkový svet v ktorom bude hra prebiehať
 */
public class World {
    private Room enterance;
    private Room grocery;
    private Library library;
    private Room gate;
    private Room bridge;
    private Room armorShop;
    private Room enemyRoom;
    private Room crossRoad;
    private Room portal;
    private Room blueRoom;
    private Room finalRoomEnterance;
    private Room finalRoom;

    private Enemy enemy;
    private ItemEnemy itemEnemy;

    private Item sword;
    private Item key;
    private Item book;

    private Npc<Food> grocerySeller;
    private Npc<Armor> armorSeller;

    /**
     * Bezparametrický konštruktor, ktorý zavolá všetky potrebné metódy
     */
    public World() {
        this.createRooms();
        this.setDescriptions();
        this.createWorld();

        this.setImages();
        this.createItems();
        this.addItems();

        this.createNPC();
        this.addNpc();
        this.createItemsToShop();

        this.createEnemy();
        this.addEnemy();

        this.lockRooms();
        this.lockExist();
    }

    /**
     * Vytvorí miestnosti
     */
    public void createRooms() {
        this.enterance = new Room("Enterance");
        this.grocery = new Room("Grocery");
        this.library = new Library();
        this.gate = new Room("Gate");
        this.bridge = new Room("Bridge");
        this.crossRoad = new Room("Crossroad");
        this.enemyRoom = new Room("Field");
        this.armorShop = new Room("Armor shop");

        this.portal = new Room("Portal");

        this.blueRoom = new Room("Blue Room");
        this.finalRoomEnterance = new Room("Enterance");
        this.finalRoom = new Room("Final room");
    }

    /**
     * Vytvorí svet z miestností
     */
    public void createWorld() {
        this.enterance.setExit(this.gate, null, this.library, this.grocery);
        this.grocery.setExit(null, null, this.enterance, null);
        this.library.setExit(null, null, null, this.enterance);
        this.gate.setExit(this.bridge, this.enterance, null, null);
        this.bridge.setExit(this.crossRoad, this.gate, null, null);
        this.crossRoad.setExit(this.portal, this.bridge, this.enemyRoom, this.armorShop);
        this.enemyRoom.setExit(null, null, null, this.crossRoad);
        this.armorShop.setExit(null, null, this.crossRoad, null); //change the exits so its not all just UP
        this.portal.setExit(this.blueRoom, this.crossRoad, null, null);
        this.blueRoom.setExit(this.finalRoomEnterance, this.portal, null, null);
        this.finalRoomEnterance.setExit(this.finalRoom, this.blueRoom, null, null);
        this.finalRoom.setExit(null, this.finalRoomEnterance, null, null);
    }

    /**
     * Nastaví popis miestností
     */
    public void setDescriptions() {
        this.enterance.setDescripton("A magic book of this world was stolen. Your goal is to find it and kill whoever took it. \nGo to the library to pick up your sword. You can also buy some food in the grocery store.");
        this.grocery.setDescripton("This is the grocery shop, you can buy food here, to heal you later.");
        this.library.setDescripton("Pick up your sword, which was left for you.");
        this.gate.setDescripton("You are at the gate to leave the village.");
        this.bridge.setDescripton("You are at the bridge between enterance to the town and rest of the map.");
        this.crossRoad.setDescripton("You are at a crossroad with multiple ways to choose from.");
        this.enemyRoom.setDescripton("Write 'fight', if you wanna fight enemies. Remember, once fight starts, you can't leave.");
        this.armorShop.setDescripton("This is an armor shop. You can buy one to pretect you in a fight.");
        this.portal.setDescripton("This portal leads to another world.");
        this.blueRoom.setDescripton("Till now an unkown place");
        this.finalRoomEnterance.setDescripton("Looks like you right before the end.");
        this.finalRoom.setDescripton("End - defeat the enemy and get the book.");
    }

    /**
     * Nastaví obrázky miestností
     */
    public void setImages() {
        this.enterance.createImage("pics/enterance2.jpeg"); //just for test
        this.library.createImage("pics/library2.png");
        this.grocery.createImage("pics/grocery.png");
        this.bridge.createImage("pics/bridge2.png");
        this.gate.createImage("pics/gate1.jpg");
        this.crossRoad.createImage("pics/crossroad1.jpg");
        this.enemyRoom.createImage("pics/enemy14.png");
        this.armorShop.createImage("pics/armorShop.png");
        this.portal.createImage("pics/portal8.png");
        this.blueRoom.createImage("pics/blueRoom.jpg");
        this.finalRoomEnterance.createImage("pics/finalEnterance.jpg");
        this.finalRoom.createImage("pics/finalEnemy.jpg");
    }

    /**
     * Uzamkne východy miestností
     */
    public void lockExist() {
        this.portal.lockExist(this.portal.getRoomUp(), null, this.portal.getRoomLeft(), this.portal.getRoomRight());
    }

    /**
     * Vytvorí predmety
     */
    public void createItems() {
        this.sword = new Item("sword");
        this.key = new Item("key");
        this.book = new Item("Magic Book");
    }

    /**
     * Vytvorí npc
     */
    public void createNPC() {
        this.grocerySeller = new Npc<Food>();
        this.armorSeller = new Npc<Armor>();
    }

    /**
     * Pridá npc do miestností
     */
    public void addNpc() {
        this.grocery.addnNpc(this.grocerySeller);
        this.armorShop.addnNpc(this.armorSeller);
    }

    /**
     * Vytvorí predmety pre predaj
     */
    public void createItemsToShop() {
        this.grocerySeller.addItem(new Food("meat", 30, 20));
        this.grocerySeller.addItem(new Food("apple", 5, 5));
        this.grocerySeller.addItem(new Food("soup", 15, 10));

        this.armorSeller.addItem(new Armor("leather", 20, 20));
        this.armorSeller.addItem(new Armor("iron", 30, 30));
    }

    /**
     * Vytvorí nepriateľov
     */
    public void createEnemy() {
        this.enemy = new Enemy("level1.txt");
        this.itemEnemy = new ItemEnemy("level3.txt");
    }

    /**
     * Pridá nepriateľov do miestností
     */
    public void addEnemy() {
        this.enemyRoom.addEnemy(this.enemy);
        this.finalRoom.addEnemy(this.itemEnemy);
    }

    /**
     * Pridá predmety do miestností
     */
    public void addItems() {
        this.library.addItem(this.sword);
        this.armorShop.addItem(this.key);
    }

    /**
     * Vráti knihu
     * @return this.book
     */
    public Item getBook() {
        return this.book;
    }

    /**
     * Vráti meč
     * @return this.sword
     */
    public Item getSword() {
        return this.sword;
    }

    /**
     * Vráti knižnicu
     * @return this.library
     */
    public Library getLibrary() {
        return this.library;
    }

    /**
     * Vráti finálneho nepriateľa
     * @return this.itemEnemy
     */
    public ItemEnemy getFinalEnemy() {
        return this.itemEnemy;
    }

    /**
     * Vráti nepriateľa
     * @return this.enemy
     */
    public Enemy getEnemy() {
        return this.enemy;
    }

    /**
     * Vráti prvú miestnosť hry
     * @return this.enterance
     */
    public Room getEnteranceRoom() {
        return this.enterance;
    }

    /**
     * Uzamkne miestnosti predmetom
     */
    public void lockRooms() {
        this.portal.lockWith(this.key);
        this.portal.setLock(true);
    }

    /**
     * Vráti portál
     * @return this.portal
     */
    public Room getPortal() {
        return this.portal;
    }

}
