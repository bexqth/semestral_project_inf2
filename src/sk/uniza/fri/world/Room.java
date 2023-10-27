package sk.uniza.fri.world;

import sk.uniza.fri.matter.Item;
import sk.uniza.fri.npc.Npc;
import sk.uniza.fri.character.Enemy;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Trieda predstavujúca miestnost hry
 */
public class Room {
    private Room[] exits;
    private ArrayList<Item> items;
    private boolean hasItems;
    private Room actualRoom;
    private Room roomUp;
    private Room roomDown;
    private Room roomLeft;
    private Room roomRight;
    private String descripton;
    private String name;
    private Npc npc;
    private ImageIcon imageIcon;
    private boolean hasNpc;
    private String[] direction;
    private Enemy enemy;
    private Item lockItem;
    private boolean lock;
    private boolean exitLock;

    /**
     * Parametrická konštruktor s parametrom mena
     *
     * @param name
     */
    public Room(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.exits = new Room[4];
        this.direction = new String[]{"Up", "Down", "Left", "Right"};
        //this.actualRoom = this;
    }

    /**
     * Metóda nastaví východy pre miestnosť
     *
     * @param roomUp
     * @param roomDown
     * @param roomLeft
     * @param roomRight
     */
    public void setExit(Room roomUp, Room roomDown, Room roomLeft, Room roomRight) { //from where I can go where
        this.exits[0] = roomUp;
        this.exits[1] = roomDown;
        this.exits[2] = roomLeft;
        this.exits[3] = roomRight;
    }

    /**
     * Metóda nastaví novú aktuálnu miestnosť
     *
     * @param newActualRoom
     */
    public void setActualRoom(Room newActualRoom) {
        this.actualRoom = newActualRoom;
    }

    /**
     * Vytvorí obrázok pre miestnosť
     * getImage().getScaledInstance(a, b, Image.SCALE_DEFAULT) - je metóda vyhľadaná na internete
     * @param name
     */
    public void createImage(String name) {
        this.imageIcon = new ImageIcon(name);
        this.imageIcon.setImage(this.imageIcon.getImage().getScaledInstance(960, 590, Image.SCALE_DEFAULT));
    }

    /**
     * Vráti imageIcon
     *
     * @return this.imageIcon
     */
    public ImageIcon getImage() {
        return this.imageIcon;
    }

    /**
     * Vráti textový reťazec pre východy pre danú miestnosť
     *
     * @return list
     */
    public String printExits() {
        String list = "";
        for (int i = 0; i < this.exits.length; i++) {
            if (this.exits[i] != null && !this.exits[i].getExitLock()) {
                list += this.direction[i] + "- " + this.exits[i].getName() + ",  ";
            }
        }
        return list;
    }

    /**
     * Vráti aktuálnu miestnosť
     *
     * @return this.actualRoom
     */
    public Room getActualRoom() {
        return this.actualRoom;
    }

    /**
     * Vráti miestnosť ktorá sa nachádza nad danou miestnosťou
     *
     * @return this.exits[0]
     */
    public Room getRoomUp() {
        return this.exits[0];
    }


    /**
     * Vráti miestnosť ktorá sa nachádza pod danou miestnosťou
     *
     * @return this.exits[1]
     */
    public Room getRoomDown() {
        return this.exits[1];
    }

    /**
     * Vráti miestnosť ktorá sa nachádza naľavo od danej miestnosti
     *
     * @return this.exits[2]
     */
    public Room getRoomLeft() {
        return this.exits[2];
    }

    /**
     * Vráti miestnosť ktorá sa nachádza napravo od danej miestnosti
     *
     * @return this.exits[2]
     */
    public Room getRoomRight() {
        return this.exits[3];
    }

    /**
     * Vráti popis triedy
     *
     * @return this.descripton
     */
    public String getDescription() {
        return this.descripton + "\n";
    }

    /**
     * Nastaví popis triedy
     *
     * @param d
     */
    public void setDescripton(String d) {
        this.descripton = d;
    }

    /**
     * Metóda vráti ArrayList predmetov nachádzajúcich sa v miestnosti
     *
     * @return this.items
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * Metóda pridá predmet do miestnosti na základe parametra
     *
     * @param item
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Metóda vymaže predmet z miestnosti na základe parametra
     *
     * @param item
     */
    public void deleteItem(Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (item == this.items.get(i)) {
                this.items.remove(i);
            }
        }
    }

    /**
     * Môže vyhľadať predmet v miestnosti na základe parametra
     *
     * @param name
     * @return
     */
    public boolean hasThisItemByName(String name) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metóda vráti meno miestnosti
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metóda pridá Npc do miestnosti
     *
     * @param npc
     */
    public void addnNpc(Npc npc) {
        this.hasNpc = true;
        this.npc = npc;
    }

    /***
     * Metóda pridá nepriateľa do miestnosti
     * @param enemy
     */
    public void addEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Metóda vráti nepriateľa, ktorý sa nachádza v miestnosti
     *
     * @return this.enemy
     */
    public Enemy getEnemy() {
        return this.enemy;
    }

    /**
     * Vráti predmety, ktoré sa nachádzajú v miestnosti vo forme textu
     *
     * @return list
     */
    public String printItems() {
        String list = "";
        if (this.items.size() > 0) {
            for (int i = 0; i < this.items.size(); i++) {
                list += this.items.get(i).getName();
            }
            list += "\n";
            return list;
        }
        return "no items";
    }

    /**
     * Vráti npc miestnosti
     *
     * @return this.npc
     */
    public Npc getNpc() {
        return this.npc;
    }

    /**
     * Prenastaví nepriatela (enemy) miestnosti podľa parametra
     *
     * @param e
     */
    public void setEnemy(Enemy e) {
        this.enemy = e;
    }

    /**
     * Nastaví predmet pre zamknutie miestnosti
     *
     * @param item
     */
    public void lockWith(Item item) {
        this.lockItem = item;
    }

    /**
     * Vráti this.lockItem miestnosti
     *
     * @return this.lockItem
     */
    public Item getLockItem() {
        return this.lockItem;
    }

    /**
     * Vráti boolean hodnotu, či miestnosť má predmet na uzamknutie, alebo nie
     *
     * @return this.lock
     */
    public boolean getLock() {
        return this.lock;
    }

    /**
     * Prenastaví this.lock na základe parametra
     *
     * @param v
     */
    public void setLock(boolean v) {
        this.lock = v;
    }

    /**
     * Vráti boolean hodnotu uzamknutého východu
     *
     * @return this.exitLock
     */
    public boolean getExitLock() {
        return this.exitLock;
    }

    /**
     * Nastaví novú hodnotu uzamknutého východu
     *
     * @param v
     */
    public void setExitLock(boolean v) {
        this.exitLock = v;
    }


    /***
     * Uzamkne vchody miestsnoti na základe parametrou
     * @param roomUp
     * @param roomDown
     * @param roomLeft
     * @param roomRight
     */
    public void lockExist(Room roomUp, Room roomDown, Room roomLeft, Room roomRight) {
        try {
            if (roomUp != null) {
                roomUp.setExitLock(true);
            }

            if (roomDown != null) {
                roomDown.setExitLock(true);
            }

            if (roomLeft != null) {
                roomLeft.setExitLock(true);
            }

            if (roomRight != null) {
                roomRight.setExitLock(true);
            }
        } catch (NullPointerException e) {
            System.out.println("it was locked");
        }
    }

    /**
     * Uzamkne/odomkne hromadne všetky vchody
     *
     * @param room
     * @param v
     */
    public void setLockedAll(Room room, boolean v) {
        try {
            if (room.getRoomUp() != null) {
                room.getRoomUp().setExitLock(v);
            }

            if (room.getRoomDown() != null) {
                room.getRoomDown().setExitLock(v);
            }

            if (room.getRoomLeft() != null) {
                room.getRoomLeft().setExitLock(v);
            }

            if (room.getRoomRight() != null) {
                room.getRoomRight().setExitLock(v);
            }

        } catch (NullPointerException e) {
            System.out.println("it was locked");
        }
    }

}
