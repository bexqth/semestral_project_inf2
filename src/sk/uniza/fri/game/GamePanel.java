package sk.uniza.fri.game;

import sk.uniza.fri.character.Player;
import sk.uniza.fri.matter.Inventory;
import sk.uniza.fri.mission.MissionControl;
import sk.uniza.fri.world.World;

import javax.swing.JPanel;
import java.awt.Dimension;


/**
 * Trieda vytvorí prostredie pre vytvorenie dalších komponentoch hry
 */
public class GamePanel extends JPanel {

    private int size;
    private int width;
    private int height;
    private ScenePanel scenePanel;
    private Input input;
    private Output output;
    private Commands com;
    private boolean gameEnded;
    private World world;
    private Player player;
    private Inventory inventory;
    private MissionControl missionControl;
    private RoomControl roomControl;
    private boolean playButtonClicked;

    /**
     * Parametrický konštruktor s parametrami pre definovanie veľkosti, výšky, šírky
     * Vytvorenie príslušných tried použitých v programe
     *
     * @param size
     * @param width
     * @param height
     */
    public GamePanel(int size, int width, int height) {
        this.size = size;
        this.width = width;
        this.height = height;

        this.input = new Input(this);
        this.world = new World();
        this.inventory = new Inventory();
        this.missionControl = new MissionControl();
        this.player = new Player("level1.txt", this.world.getEnteranceRoom(), this.inventory); //Enterance room from the beginning
        this.output = new Output(this, this.player, this.world, this.missionControl);
        this.roomControl = new RoomControl(this.player, this.output, this.missionControl, this.world);
        this.com = new Commands(this.input, this.output, this.player, this.world, this.missionControl, this.roomControl, this.scenePanel);

        this.scenePanel = new ScenePanel(this, this.player);
        this.output.printRoomDesctioption();

        this.gameEnded = false;
        this.setSettings();
    }

    /**
     * Základný cyklus hry, kde sa kontroluje či už nie je koniec hry
     */
    public void gameLoop() {
        while (!this.endGame()) {
            System.out.println(this.player.getActualRoom().getName());
            this.com.doCommand(this.input.sendText());
            this.scenePanel.setImage();
        }
        this.winGame();
    }

    /**
     * Nastaví definované nastavenie panela
     */
    public void setSettings() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.add(this.output);
        this.add(this.input);
        this.add(this.scenePanel);
    }

    /**
     * Vráti boolean hodnoty na základe podmienok - či je hráč nažive a či vlastní požadovaný predmet na výhru hry
     *
     * @return true/false
     */
    public boolean endGame() {
        if (this.player.getDead() || this.player.hasItem(this.world.getBook())) {
            this.input.setEditable(false);
            return true;
        }
        return false;
    }

    /**
     * Kontrola výhry hry - vlastnenie predmetu
     */
    public void winGame() {
        if (this.player.hasItem(this.world.getBook())) {
            this.output.printYouWon();
        }
    }

    /**
     * Vráti hodnotu šírky panela
     *
     * @return this.width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Vráti hodnotu jednotky panela
     *
     * @return this.size
     */
    public int getFinalSize() {
        return this.size;
    }

}
