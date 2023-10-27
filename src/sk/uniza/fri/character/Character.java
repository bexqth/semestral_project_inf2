package sk.uniza.fri.character;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Vytvorí a definuje vlastnosti charakteru
 */

public abstract class Character {
    private int health;
    private ArrayList<Integer> attackType;
    private ArrayList<String> attackName;
    private boolean ultinaUsed;
    private String ultinaName;
    private String levelFile;
    private Scanner scanner;

    /**
     * Parametrický konštruktor, v ktorom sa definujú premenné doležité pre danú triedu
     *
     * @param levelFile
     */
    public Character(String levelFile) {
        this.levelFile = levelFile;
        this.health = 100;
        this.ultinaUsed = false;

        this.attackType = new ArrayList<>();
        this.attackName = new ArrayList<>();
        this.loadLevel();
        this.findtUltinaName();
    }

    /**
     * Nastaví nový level pre charaktera na základe názvu textového súboru
     *
     * @param file
     */
    public void newLevel(String file) {
        this.deleteAttacks();
        this.levelFile = file;
        this.loadLevel();
    }

    /**
     * Vymaže z arrayListu útok s najväčším zranením
     */
    public void deleteAttacks() {
        int sizeN = this.attackName.size();
        int sizeT = this.attackType.size();

        for (int i = 0; i < sizeN; i++) {
            this.attackName.remove(0);
        }

        for (int i = 0; i < sizeT; i++) {
            this.attackType.remove(0);
        }
    }

    /**
     * Metóda načíta nový level
     */
    public void loadLevel() {
        try {
            this.scanner = new Scanner(new File(this.levelFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (this.scanner.hasNextLine()) {
            this.attackName.add(this.scanner.next());
            this.attackType.add(this.scanner.nextInt());
        }
    }

    /**
     * Metodá nastaví novú výšku zdravia
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Metóda vráti hodnotu zdravia
     *
     * @return this.health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Metóda vráti textový retazec zdravia
     *
     * @return this.health
     */
    public String printHealth() {
        return String.valueOf(this.health) + "\n";
    }

    /**
     * Metóda, ktorá zníži danému charakterovy jeho zdravie
     *
     * @param howMuch
     */
    public abstract void lowerHealth(int howMuch);

    /**
     * Vráti boolean hodnotu či daný chakakter je nažive, alebo nie
     *
     * @return this.health
     */
    public boolean getDead() {
        return this.health <= 0;
    }

    /**
     * Ak je zdravie viac ako maximálne možné, zmení ho na maximum
     */
    public void restoreHealth() {
        if (this.health > 100) {
            this.health = 100;
        }
    }

    /**
     * Nájde meno útoku s najväčším poškodením
     */
    public void findtUltinaName() {
        int max = 0;
        this.ultinaName = "";

        for (int i = 0; i < this.attackType.size(); i++) {
            if (max < this.attackType.get(i)) {
                max = this.attackType.get(i);
                this.ultinaName = this.attackName.get(i);
            }
        }
    }

    /**
     * Vráti meno najsilnejšieho útoku
     *
     * @return this.ultinaName
     */
    public String getUltinaName() {
        return this.ultinaName;
    }

    /**
     * Vráti či bol najsilnejší útok použitý
     *
     * @return this.ultinaUsed
     */
    public boolean getUltinaUsed() {
        return this.ultinaUsed;
    }

    /**
     * Skontroluje či bol najsilnejší útok použitý
     */
    public void setTrueUltinaUsed() {
        for (int i = 0; i < this.attackName.size(); i++) {
            if (this.attackName.get(i).equals(this.ultinaName)) {
                this.attackName.remove(i);
                this.attackType.remove(i);
            }
        }
    }

    /**
     * Vráti celý ArrayList poškodení útokov
     *
     * @return this.attackType
     */
    public ArrayList<Integer> getAttackType() {
        return this.attackType;
    }

    /**
     * Vráti celý ArrayList mien útokov
     *
     * @return this.attackName
     */
    public ArrayList<String> getAttackName() {
        return this.attackName;
    }

    /**
     * Vráti textovú reprezentáciu útokov
     *
     * @return list
     */
    public String getAttacksList() {
        String list = "Attacks: \n";
        for (int i = 0; i < this.attackType.size(); i++) {
            list += "Attack " + this.attackName.get(i) + " - damage : " + this.attackType.get(i) + "\n";
        }
        return list;
    }

}
