package main.java;

public class AttackResult {

    String resultMsg;
    int damageDealt;
    StatusEffect statusEffect;
    int resourceSpent;

    public AttackResult(String resultMsg, int damageDealt, int resourceSpent, StatusEffect statusEffect) {
        this.resultMsg = resultMsg;
        this.damageDealt = damageDealt;
        this.statusEffect = statusEffect;
        this.resourceSpent = resourceSpent;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    public int getResourceSpent() {
        return resourceSpent;
    }
}
