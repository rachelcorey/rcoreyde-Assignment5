package main.java;

public abstract class Resource {

    String name;
    int currentAmount;
    int totalAmount;
    int regenAmtPerTurn;

    public void regen(float percentModifier) {
        currentAmount += (int) (regenAmtPerTurn * percentModifier);
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void increaseTotalAmount(int amount) {
        this.totalAmount += amount;
    }

    public void restoreFullAmount() {
        currentAmount = totalAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void decreaseCurrentAmount(int amount) {
        currentAmount -= amount;
    }

    public void increaseCurrentAmount(int amount) {
        currentAmount += amount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getName() {
        return name;
    }
}
