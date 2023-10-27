package sk.uniza.fri.character;

import java.util.Random;

/**
 * Enemy trieda ktorá dedí z triedy "Character"
 */
public class Enemy extends Character {

    private final Random random;
    private int generatedAttack;
    private int damage;
    private String name;

    /**
     * Parametrický konštruktor na základe vytvorenej dedičnosti s triedou "Character"
     *
     * @param levelFile
     */
    public Enemy(String levelFile) {
        super(levelFile);
        this.random = new Random();
    }

    /**
     * Override metóda na zníženie zdravia precificky pre triedu "Enemy"
     *
     * @param howMuch
     */
    @Override
    public void lowerHealth(int howMuch) {
        this.setHealth(this.getHealth() - howMuch);
    }

    /**
     * Metóda, ktorá vygeneruje náhodný útok
     */
    public void generateAttack() {
        this.generatedAttack = this.random.nextInt(this.getAttackType().size());
        this.name = this.getAttackName().get(this.generatedAttack);
        this.damage = this.getAttackType().get(this.generatedAttack);
        if (this.name.equals(this.getUltinaName())) {
            this.setTrueUltinaUsed();
        }
    }

    /**
     * Vráti hodnotu poškodenia
     *
     * @return this.damage
     */
    public int getGeneratedDamage() {
        return this.damage;
    }

}
