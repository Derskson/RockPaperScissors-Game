public class Player extends GameCharacter {

    float money;

    public Player(int health, int damage, String favoriteMov, float money, Position position, char symbol) {
        super(health, damage, favoriteMov, position, symbol);
        this.money = money;
    }

    // Getter y setter para money
    public float getMoney() {
        return money;
    }
    public void setMoney(float money) {
        this.money = money;
    }

}
