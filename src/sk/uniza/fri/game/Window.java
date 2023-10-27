package sk.uniza.fri.game;
import javax.swing.JFrame;


/**
 * Trieda ktorá vytvorí grafické prostredie pre hru
 */
public class Window extends JFrame {
    private final int size;
    private final int width;
    private final int height;
    private GamePanel gamePanel;

    /**
     * Bezparametrický konštruktor, ktorý definuje základné vlastnosti prostredia
     */
    public Window () {
        this.size = 48;
        this.width = this.size * 20;
        this.height = this.size * 14;
        this.gamePanel = new GamePanel(this.size, this.width, this.height);
        this.setSettings();
        this.gamePanel.gameLoop();
    }

    /**
     * Nastaví doplnkové vlastnosti - meno, pridanie komponentov
     */
    public void setSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Demo version");
        this.add(this.gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
