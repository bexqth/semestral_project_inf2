package sk.uniza.fri.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;

/**
 * Trieda "Input", ktorá sa stará o vstup používateľa
 */
public class Input extends TextField {
    private final GamePanel gamePanel;
    private boolean pickUp;
    private boolean delete;
    private boolean fight;
    private boolean eat;
    private boolean shop;
    private boolean cook;

    /**
     * Parametrický konštruktor, ktorý vytvorí prostredie pre zadávanie vstupu od používateľa
     *
     * @param gamePanel
     */
    public Input(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.setSettings();
    }

    /**
     * Nastaví základné nastavenie komponentu pre vstup
     */
    public void setSettings() {
        this.setBackground(new Color(33, 33, 33));
        this.setForeground(Color.white);
        this.setFont(new Font("Anonymous Pro", Font.PLAIN, 20));
        this.setBounds(0, this.gamePanel.getFinalSize() * 13, this.gamePanel.getWidth(), this.gamePanel.getFinalSize());
    }

    /**
     * Vráti text, ktorý používateľ zadal
     *
     * @return this.getText()
     */
    public String sendText() {
        return this.getText();
    }

    /**
     * Vráti boolean hodnotu pre this.eat
     *
     * @return this.eat
     */
    public boolean getEat() {
        return this.eat;
    }

    /**
     * Nastavý novú hodnotu pre this.shop
     *
     * @param v
     */
    public void setShop(boolean v) {
        this.shop = v;
    }

    /**
     * Vráti boolean hodnotu pre this.shop
     *
     * @return this.shop
     */
    public boolean getShop() {
        return this.shop;
    }

    /**
     * Nastavý novú hodnotu pre this.eat
     *
     * @param v
     */
    public void setEat(boolean v) {
        this.eat = v;
    }

    /**
     * Vráti boolean hodnotu pre this.pickUp
     *
     * @return this.pickUp
     */
    public boolean getPickUp() {
        return this.pickUp;
    }

    /**
     * Vráti boolean hodnotu pre this.delete
     *
     * @return this.delete
     */
    public boolean getDelete() {
        return this.delete;
    }

    /**
     * Vráti boolean hodnotu pre this.fight
     *
     * @return this.fight
     */
    public boolean getFight() {
        return this.fight;
    }

    /**
     * Nastavý novú hodnotu pre this.fight
     *
     * @param v
     */
    public void setFight(boolean v) {
        this.fight = v;
    }

    /**
     * Nastavý novú hodnotu pre this.pickUp
     *
     * @param v
     */
    public void setPickUp(boolean v) {
        this.pickUp = v;
    }

    /**
     * Nastavý novú hodnotu pre this.delete
     *
     * @param v
     */
    public void setDelete(boolean v) {
        this.delete = v;
    }

    /**
     * Vymaže textové pole vstupu
     */
    public void clearField() {
        this.setText("");
    }

}
