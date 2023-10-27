package sk.uniza.fri.character;

import sk.uniza.fri.matter.Item;
import sk.uniza.fri.world.Room;

/**
 * Trieda ItemEnemy, ktorý je schopný dať predmet po jeho smrti, ktorá dedí vlastnosti na základe dedičnosti s triedou "Enemy"
 */
public class ItemEnemy extends Enemy {

    /**
     * Parametrický koštruktor na základe vytvorenej dedičnosti
     *
     * @param levelFile
     */
    public ItemEnemy(String levelFile) {
        super(levelFile);
    }

    /**
     * Metóda, ktorá po smrti, pridá danú vec do danej miestnosti
     *
     * @param room
     * @param item
     */
    public void giveItem(Room room, Item item) {
        if (this.getDead()) {
            room.addItem(item);
        }
    }
}
