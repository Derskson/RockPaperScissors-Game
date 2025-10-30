public class Villain extends GameCharacter {

    //atributtes
    private int level;

    public Villain(int health, int damage, String favoriteMov, Position position, char symbol, int level) {
        super(health, damage, favoriteMov, position, symbol);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
