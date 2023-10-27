package sk.uniza.fri.game;

import sk.uniza.fri.character.Player;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * Trieda zodpovedajúca za zmenu obrázkov počas hry
 */
public class ScenePanel extends JPanel {
    private final int width;
    private final int height;
    private Player player;
    private JLabel label;

    /**
     * Parametrický konštruktor, ktorý definuje potrebné parametre a vytvorí prostredie pre vykreslenie
     *
     * @param gamePanel
     * @param player
     */
    public ScenePanel(GamePanel gamePanel, Player player) {
        this.player = player;
        this.label = new JLabel();
        this.width = gamePanel.getWidth();
        this.height = gamePanel.getFinalSize() * 9;
        this.setSettings();
        this.label.setBounds(0, 0, this.width, this.height);
        this.setImage();
        this.add(this.label);
    }

    /**
     * Nastaví základné nastavenie priestoru
     */
    public void setSettings() {
        this.setLayout(null);
        this.setBackground(new Color(30, 30, 30));
        this.setVisible(true);
        this.setBounds(0, 0, this.width, this.height);
    }

    /**
     * Prenastaví obrázok podľa aktuálnej miestnosti
     */
    public void setImage() {
        this.label.setIcon(this.player.getActualRoom().getImage());
    }

}
