package sk.uniza.fri.game;

import sk.uniza.fri.matter.ShopItem;
import sk.uniza.fri.mission.MissionControl;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.matter.Food;
import sk.uniza.fri.npc.Npc;
import sk.uniza.fri.world.World;

/**
 * Hlavná trieda projektu, na základe ktorej metódy sa vykonávajú príkazy
 */

public class Commands {
    private final Input input;
    private final Output output;
    private final Player player;
    private final World world;
    private final MissionControl missionControl;
    private final RoomControl roomControl;
    private final ScenePanel scenePanel;

    /**
     * Parametrický koštruktor
     *
     * @param input
     * @param output
     * @param player
     * @param world
     * @param missionControl
     * @param roomControl
     * @param scenePanel
     */

    public Commands(Input input, Output output, Player player, World world, MissionControl missionControl, RoomControl roomControl, ScenePanel scenePanel) {
        this.input = input;
        this.output = output;
        this.player = player;
        this.world = world;
        this.missionControl = missionControl;
        this.roomControl = roomControl;
        this.scenePanel = scenePanel;
    }

    /**
     * Metóda, ktorá na základe switch case porovná možné možnosti s pamaraterom a vykoná príslusnú metódu
     *
     * @param command
     */
    public void doCommand(String command) {
        this.hasArgument(command);
        switch (command) {
            case "go up":
                this.go("up");
                break;

            case "go down":
                this.go("down");
                break;

            case "go left":
                this.go("left");
                break;

            case "go right":
                this.go("right");
                break;

            case "seek":
                this.input.clearField();
                this.output.printRoomItems();
                break;

            case "info":
                this.input.clearField();
                this.output.printRoomDesctioption();
                break;

            case "pick up":
                this.input.setPickUp(true);
                this.roomControl.inWeaponRoom();
                this.input.clearField();
                this.output.printAskForItemToPickUp();
                break;

            case "delete":
                this.roomControl.inWeaponRoom();
                this.input.clearField();
                this.output.printAskForItemToDelete();
                this.input.setDelete(true);
                break;

            case "eat":
                this.input.setEat(true);
                this.input.clearField();
                this.output.printAskForFoodToEat();
                break;

            case "show inventory":
                this.input.clearField();
                this.output.printInventory();
                break;

            case "health":
                this.input.clearField();
                this.output.printHealth();
                break;

            case "money":
                this.input.clearField();
                this.output.printMoney();
                break;

            case "show commands":
                this.output.printCommands();
                this.input.clearField();
                break;

            case "shop":
                if (this.player.getActualRoom().getNpc() == null) {
                    this.output.printCanShopHere();
                } else {
                    this.output.printDialog();
                    this.input.setShop(true);
                    this.player.getActualRoom().setLockedAll(this.player.getActualRoom(), true);
                }
                this.input.clearField();
                break;

            case "unlock":
                this.unlock();
                this.input.clearField();
                break;

            case "fight":
                this.input.clearField();
                if (this.player.getActualRoom().getEnemy() == null) {
                    this.output.printNoAttack();
                } else {
                    this.input.setFight(true);
                    this.output.printFight();
                }
                break;

            case "armor":
                this.output.printArmor();
                this.input.clearField();
                break;

            case "mission":
                this.output.printActualMission();
                this.input.clearField();
                break;
            default:
        }
    }

    /**
     * Pri metódach s parametrom
     *
     * @param command
     */
    public void hasArgument(String command) {
        if (this.input.getPickUp()) {
            this.pickUpItem(command);
        } else if (this.input.getDelete()) {
            this.deleteItem(command);
        } else if (this.input.getFight()) {
            this.chooseAttack(command);
        } else if (this.input.getEat()) {
            this.eatItem(command);
        } else if (this.input.getShop()) {
            this.shop(command);
        }
    }

    /**
     * Metóda zodpovedaná za posun hráča
     *
     * @param way
     */
    public void go(String way) {
        try {
            switch (way) {
                case "up":
                    if (!this.player.getActualRoom().getRoomUp().getExitLock()) {
                        this.player.goUp();
                    }
                    break;

                case "down":
                    if (!this.player.getActualRoom().getRoomDown().getExitLock()) {
                        this.player.goDown();
                    }
                    break;

                case "left":
                    if (!this.player.getActualRoom().getRoomLeft().getExitLock()) {
                        this.player.goLeft();
                    }
                    break;

                case "right":
                    if (!this.player.getActualRoom().getRoomRight().getExitLock()) {
                        this.player.goRight();
                    }
                    break;
            }
            this.input.clearField();
            this.output.printRoomDesctioption();

        } catch (NullPointerException e) {
            this.input.clearField();
            this.output.printNoExitAvailable();
        }

    }

    /**
     * Metóda, ktorá odomkne príslušné vchody do danej miestnosti
     */
    public void unlock() {
        if (this.player.getActualRoom().getLockItem() != null) {
            for (int i = 0; i < this.player.getInventory().getItems().size(); i++) {
                if (this.player.getActualRoom().getLockItem().getName().equals(this.player.getInventory().getItems().get(i).getName())) {
                    this.player.putOutOfInventary(this.player.getActualRoom().getLockItem());
                    this.player.getActualRoom().lockWith(null);
                    this.player.getActualRoom().setLock(false);
                    this.player.getActualRoom().setLockedAll(this.player.getActualRoom(), false);
                    this.roomControl.openPortal();
                    this.output.printRoomDesctioption();
                    break;
                } else {
                    this.output.printCantUnlock();
                }
            }
        }
    }

    /**
     * Metóda, ktorá porovná výber hráča, daný ako parameter, s možnostami, aby daná možnosť bola neskôr pridaná do inventorá hráča
     *
     * @param itemToBuy
     */
    public void shop(String itemToBuy) {
        Npc seller = this.player.getActualRoom().getNpc();
        for (int i = 0; i < seller.getShopItems().size(); i++) {
            ShopItem prom = (ShopItem) seller.getShopItems().get(i);
            if (!itemToBuy.equals("") && itemToBuy.equals(prom.getName())) {
                if (this.player.noMoney() || this.player.getMoney() < prom.getPrice()) {
                    this.output.printNoMoney();
                    this.input.clearField();
                    this.input.setShop(false);
                    this.player.getActualRoom().setLockedAll(this.player.getActualRoom(), false);

                } else {
                    this.player.putInInventory(prom);
                    this.player.lowerMoney(prom.getPrice());
                    this.player.checkForArmor();
                    this.input.setShop(false);
                    this.player.getActualRoom().setLockedAll(this.player.getActualRoom(), false);
                    this.input.clearField();
                    this.output.printWhatUBought(prom.getName());
                }
            }
        }
    }

    /**
     * Na základe parametra sa hráčovi pridá predmet do inventára
     *
     * @param name
     */
    public void pickUpItem(String name) {
        for (int i = 0; i < this.player.getActualRoom().getItems().size(); i++) {
            if (!name.equals("") && name.equals(this.player.getActualRoom().getItems().get(i).getName())) {
                this.player.putInInventory(this.player.getActualRoom().getItems().get(i));
                this.player.getActualRoom().deleteItem(this.player.getActualRoom().getItems().get(i));
                this.output.printPickUpItem();
                this.input.setPickUp(false);
                this.input.clearField();
                break;
            }
        }
    }

    /**
     * Na základe parametra sa hráčovi odstráni predmet z inventára
     *
     * @param name
     */
    public void deleteItem(String name) {
        for (int i = 0; i < this.player.getInventory().getItems().size(); i++) {
            if (!name.equals("") && name.equals(this.player.getInventory().getItems().get(i).getName())) {
                this.player.getActualRoom().addItem(this.player.getInventory().getItems().get(i));
                this.player.putOutOfInventary(this.player.getInventory().getItems().get(i));
                this.output.printDeleteItem();
                this.input.setDelete(false);
                this.input.clearField();
                break;
            }
        }
    }

    /**
     * Hráčovi pribudne zdravie na základe výšky zdravia vybraného predmetu
     *
     * @param name
     */
    public void eatItem(String name) {
        for (int i = 0; i < this.player.getInventory().getItems().size(); i++) {
            if (!name.equals("") && name.equals(this.player.getInventory().getItems().get(i).getName())) {
                if (this.player.getInventory().getItems().get(i) instanceof Food food) {
                    food = (Food) this.player.getInventory().getItems().get(i);
                    this.player.addHeatlh(food.getHealth());
                    this.output.printEat(food.getHealth());
                    this.player.putOutOfInventary(food);
                    this.input.setEat(false);
                    this.input.clearField();
                    break;
                }
            }
        }
    }

    /**
     * Na základe parametra sa vyberie útok v boji
     *
     * @param command
     */
    public void chooseAttack(String command) {
        if (!this.player.getHasWeapon()) {
            this.output.printNoWeapon();
            this.input.setFight(false);
        } else {
            this.player.getActualRoom().setLockedAll(this.player.getActualRoom(), true);
            for (int i = 0; i < this.player.getAttackType().size(); i++) {
                if (this.player.getActualRoom().getEnemy() != null) {
                    if (!command.equals("") && command.equals(this.player.getAttackName().get(i))) {
                        this.player.fight(this.player.getAttackType().get(i), this.player.getActualRoom().getEnemy());

                        if (command.equals(this.player.getUltinaName())) {
                            this.player.setTrueUltinaUsed();
                        }
                        this.fightControl();
                        this.input.clearField();
                    }
                }
            }
        }
    }

    /**
     * Kontrola procesu boja
     * Kto umrie - zistenie končenia hry
     * V prípade nepriateľa definovaného ako "ItemEnemy", pridanie predmetu do miestosti daného nepriateľa
     */
    public void fightControl() {
        if (this.player.getDead()) {
            this.input.clearField();
            this.output.printPlayerDead();
            this.input.setFight(false);
        } else if (this.player.getActualRoom().getEnemy().getDead()) {
            this.player.getActualRoom().setLockedAll(this.player.getActualRoom(), false);
            this.roomControl.enemyItem();
            this.roomControl.finalEnemyDead();
            this.roomControl.levelUp();
            this.output.printEnemyDead();
            this.input.setFight(false);
            this.player.getActualRoom().setEnemy(null);
        } else {
            this.output.printFight();
        }
    }

}