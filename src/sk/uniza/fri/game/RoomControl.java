package sk.uniza.fri.game;

import sk.uniza.fri.character.ItemEnemy;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.mission.MissionControl;
import sk.uniza.fri.world.World;

/**
 * Trieda, ktorá ma nastarosti metódy pre niektoré z miestností
 */
public class RoomControl {
    private Player player;
    private Output output;
    private MissionControl missionControl;
    private World world;

    /**
     * Parametrický koštruktor, ktorý definutie triedy potrebné pre triedu
     *
     * @param player
     * @param output
     * @param missionControl
     * @param world
     */
    public RoomControl(Player player, Output output, MissionControl missionControl, World world) {
        this.player = player;
        this.output = output;
        this.missionControl = missionControl;
        this.world = world;
    }

    /**
     *Kontrola či finálny nepriateľ je mŕtvy na pridanie predmetu nepriateľa do miestnosti
     */
    public void enemyItem() {
        if (this.player.getActualRoom().getEnemy() == this.world.getFinalEnemy()) {
            ItemEnemy en = this.world.getFinalEnemy();
            en.giveItem(this.player.getActualRoom(), this.world.getBook());
        }

    }

    /**
     * Kontrola či je finálny nepriateľ mŕtvy, pre dokončenie misie
     */
    public void finalEnemyDead() {
        if (this.player.getActualRoom().getEnemy() == this.world.getFinalEnemy()) {
            if (this.world.getFinalEnemy().getDead()) {
                this.missionControl.setMissionDone("finalEnemy");
            }
        }
    }

    /**
     * Kontrola či je portál otvorený, pre dokončenie misie
     */
    public void openPortal() {
        if (this.player.getActualRoom() == this.world.getPortal()) {
            this.missionControl.setMissionDone("portal");
        }
    }

    /**
     * Kontrola či je potrebný nepriateľ mŕtvy, pre zvýšenie levelu hráča
     */
    public void levelUp() {
        if (this.player.getActualRoom().getEnemy() == this.world.getEnemy()) {
            this.player.newLevel("level2.txt");
            this.missionControl.setMissionDone("levelUp");
        }
    }

    /**
     * Konrola a update knihovne a jej popisu pri zobraní a položení meča z knižnice
     */
    public void inWeaponRoom() {
        if (this.player.getActualRoom() == this.world.getLibrary()) {
            if (this.player.getActualRoom().hasThisItemByName(this.world.getSword().getName())) {
                this.world.getLibrary().setHasSword(false);
                this.player.setHasWeapon(true);
                this.missionControl.setMissionDone("sword");
                this.world.getLibrary().checkSword();
            } else {
                this.world.getLibrary().setHasSword(true);
                this.player.setHasWeapon(false);
                this.world.getLibrary().checkSword();
            }
            this.output.updateWeaponRoom();
        }
    }

}
