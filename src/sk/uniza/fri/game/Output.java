package sk.uniza.fri.game;

import sk.uniza.fri.mission.MissionControl;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.world.World;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

/**
 * Trieda "Output" je zodpovedná za oboznámenie užívateľa o progrese hry
 */
public class Output extends JTextArea {
    private final GamePanel gamePanel;
    private final Player player;
    private final World world;
    private final MissionControl missionControl;
    private String[] commands;

    /**
     * Parametrický koštruktor, ktorý vytvorí prostredie pre vypísanie textu a priebehu hry
     *
     * @param gamePanel
     * @param player
     * @param world
     * @param missionControl
     */
    public Output(GamePanel gamePanel, Player player, World world, MissionControl missionControl) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.world = world;
        this.missionControl = missionControl;
        this.commands = new String[]{"go up", "go down", "go left", "go right", "seek", "info", "pick up", "delete", "eat", "show inventory", "health", "money", "shop", "fight", "show commands", "unlock", "mission", "armor"};
        this.setSettings();
    }

    /**
     * Vypíše aktuálnu misiu
     */
    public void printActualMission() {
        this.setText(this.missionControl.getActualMission());
    }

    /**
     * Nastaví základné nastavenie komponentu pre výpis textu
     */
    public void setSettings() {
        this.setBackground(new Color(33, 33, 33));
        this.setForeground(Color.white);
        this.setFont(new Font("Anonymous Pro", Font.PLAIN, 20));
        this.setBounds(0, this.gamePanel.getFinalSize() * 9, this.gamePanel.getWidth(), this.gamePanel.getFinalSize() * 4);
        this.setEditable(false);
    }

    /**
     * Vypíše že neuxistujú žiadne možné východy
     */
    public void printNoExitAvailable() {
        this.setText("There is no such way");
    }

    /**
     * Vypíše že hráč nemá zbraň na bojovanie
     */
    public void printNoWeapon() {
        this.setText("You have no weapon to fight with.");
    }

    /**
     * Vypíše obsah inventára
     */
    public void printInventory() {
        this.setText("In your inventory is: \n" + this.player.showInventory());
    }

    /**
     * Vypíše dialóg Npc
     */
    public void printDialog() {
        this.setText(this.player.getActualRoom().getNpc().getDialog());
    }

    /**
     * Vypíše popis miestnosti
     */
    public void printRoomDesctioption() {
        if (this.player.getActualRoom().getLock()) {
            this.setText("Try 'unlock', if that doesn't work, u need something to open it with\n" + this.player.getActualRoom().printExits());
        } else {
            this.setText(this.player.getActualRoom().getDescription() + "" + this.player.getActualRoom().printExits());
        }
    }

    /**
     * Vypíše že nie je možné bojovať, pretože tam nie je žadný možný boj
     */
    public void printNoAttack() {
        this.setText("There is no fight");
    }

    /**
     * Opýta sa ktorý predmet by chcel hráč zdvihnúť
     */
    public void printAskForItemToPickUp() {
        this.setText("Which item would you like to pick up?\n" + this.player.getActualRoom().printItems());
    }

    /**
     * Opýta sa ktorý predmet by chcel hráč vymazať z inventára
     */
    public void printAskForItemToDelete() {
        this.setText("Which item do you wanna delete? \n" + this.player.showInventory());
    }

    /**
     * Opýta sa, ktorý predmet by chcel hráš zjesť
     */
    public void printAskForFoodToEat() {
        this.setText("Which kind of food would you like? \n" + this.player.showFoodInInventory());
    }

    /**
     * Vypíše sa oznámenie o zjedení daného jedla
     */
    public void printEat(int value) {
        this.setText("You just ate, your health was increased by " + value);
    }

    /**
     * Vypíše sa oznámenie o pridaní predmetu do inventára
     */
    public void printPickUpItem() {
        this.setText("You picked up the item.\n");
    }

    /**
     * Vypíše sa oznámenie o vymazaní predmetu z inventára
     */
    public void printDeleteItem() {
        this.setText("You deleted item.\n");
    }

    /**
     * Vypíše predmety, ktoré sa nachádzajú v aktuálnej miestnosti
     */
    public void printRoomItems() {
        this.setText("In this room is: \n" + this.player.getActualRoom().printItems());
    }

    /**
     * Aktualizuje popis triedy v knižnici
     */
    public void updateWeaponRoom() {
        this.setText(this.world.getLibrary().getDescription());
    }

    /**
     * Vypíše aktuálnu hodnotu zdravia hráča
     */
    public void printHealth() {
        this.setText("Your health is " + String.valueOf(this.player.getHealth()));
    }

    /**
     * Vypíše aktuálnu hodnotu peňazí hráča
     */
    public void printMoney() {
        this.setText(this.player.printGetMoney());
    }

    /**
     * Vypíše oznámenie o skončení hry
     * Dovôd - hráč bol zabitý nepriateľom
     */
    public void printPlayerDead() {
        this.setText("Game over.\nYou were killed by the enemy, better luck next time.");
    }

    /**
     * Vypíše oznámenie o skončení hry
     * Dovôd - hráč vyhral hru
     */
    public void printYouWon() {
        this.setText("Congratulation, you got back the stolen book and by that you won the game.");
    }

    /**
     * Vypíše oznámenie o zabití nepriateľa
     */
    public void printEnemyDead() {
        this.setText("The enemy died");
    }

    /**
     * Vypíše oznámenie s predmetom, ktorý hráč zakúpil
     */
    public void printWhatUBought(String s) {
        this.setText("You just bought " + s + "\n" + this.player.printGetMoney());
    }

    /**
     * Vypíše oznámenie o tom že hráč nemá žiadne alebo nedostatok peňazí
     */
    public void printNoMoney() {
        this.setText("You dont have enough money");
    }

    /**
     * Vypíše oznámenie o vlastníctve a množstve zdravia brnenia
     */
    public void printArmor() {
        if (this.player.getArmor() == null) {
            this.setText("You dont have any armor");
        } else {
            this.setText("You have " + this.player.getArmor().getName() + " armor with " + this.player.getArmor().getHealth() + " health");
        }
    }

    /**
     * Vypíše oznámenie o priebehu boja
     * - zdravie hráča ( + množstvo zdravia brnenia)
     * - zdravie nepriateľa
     * - zoznam možných útokov
     */
    public void printFight() {
        if (!this.player.getHasArmor()) {
            this.setText("Your health: " + this.player.getHealth() + " | Your armor: 0\n" +
                    "Enemy health: " + this.player.getActualRoom().getEnemy().getHealth() +
                    "\n" + this.player.getAttacksList());
        } else {
            this.setText("Your health: " + this.player.getHealth() + " | Your armor: " + this.player.getArmor().getHealth() + "\n" +
                    "Enemy health: " + this.player.getActualRoom().getEnemy().getHealth() +
                    "\n" + this.player.getAttacksList());
        }
    }

    /**
     * Metóda, ktorá si uloží možné príkazy
     */
    public String getCommands() {
        String list = "";
        int num = 0;
        for (String command : this.commands) {
            if (num > 10) {
                list += "\n";
                num = 0;
            }
            list += command + ",  ";
            num++;
        }
        return list;
    }

    /**
     * Vypíše možné príkazi, ktoré môže používateľ použiť
     */
    public void printCommands() {
        this.setText("Commands you can use: \n" + this.getCommands());
    }

    /**
     * Vypíše oznámenie o uzamknutí vchodov
     */
    public void printCantUnlock() {
        this.setText("Try 'unlock', if that doesn't work, u need something to open it with ");
    }

    /**
     * Vypíše oznámenie o nemožnosti nakupovania
     */
    public void printCanShopHere() {
        this.setText("You cant shop here");
    }
}
