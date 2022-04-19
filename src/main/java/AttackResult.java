package main.java;

public class AttackResult {

    String resultMsg;
    int damageDealt;
    StatusEffect statusEffect;

    public AttackResult(String resultMsg, int damageDealt, StatusEffect statusEffect) {
        this.resultMsg = resultMsg;
        this.damageDealt = damageDealt;
        this.statusEffect = statusEffect;
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
}
