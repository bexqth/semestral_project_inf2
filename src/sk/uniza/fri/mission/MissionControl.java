package sk.uniza.fri.mission;

import java.util.ArrayList;

/**
 * Trieda, ktorá vytvára misie, ktoré je možné urobiť počas hry
 */
public class MissionControl {
    private ArrayList<Mission> missions;

    /**
     * Bezparametrický konštruktor pre vytvorenie missi
     */
    public MissionControl() {
        this.missions = new ArrayList<>();
        this.createMissions();
    }

    /**
     * Metóda, pridá do ArrayListu misii nové misie
     */
    public void createMissions() {
        this.missions.add(new Mission("sword", "Pick up the sword which was left for you"));
        this.missions.add(new Mission("levelUp", "Fight an enemy to get a higher level"));
        this.missions.add(new Mission("portal", "Find a way to open the portal the portal"));
        this.missions.add(new Mission("finalEnemy", "Fight the final enemy"));
        this.missions.add(new Mission("book", "Get the Magic Book"));
    }

    /**
     * Vracia ArrayList misii
     *
     * @return this.missions
     */
    public ArrayList<Mission> getMissions() {
        return this.missions;
    }

    /**
     * Nastaví misiu ukončenú podľa mena na základe parametra
     *
     * @param name
     */
    public void setMissionDone(String name) {
        for (int i = 0; i < this.missions.size(); i++) {
            if (this.missions.get(i).getName().equals(name)) {
                this.missions.get(i).setDone(true);
            }
        }
    }

    /**
     * Metóda vráti prvú možnú neukončenú misiu
     *
     * @return this.mission.get(i).getText()
     */
    public String getActualMission() {
        for (int i = 0; i < this.missions.size(); i++) {
            if (!this.missions.get(i).getDone()) {
                return this.missions.get(i).getText();
            }
        }
        return null;
    }
}
