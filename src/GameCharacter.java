public class GameCharacter extends GameObject {

    // Atributos
    private int health;
    private int damage;
    private String favoriteMov;


    public GameCharacter(int health, int damage, String favoriteMov, Position position, char symbol){
        super(position);
        this.health = health;
        this.damage = damage;
        this.favoriteMov = favoriteMov;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getFavoriteMov() {
        return favoriteMov;
    }

    public void setFavoriteMov(String favoriteMov) {
        this.favoriteMov = favoriteMov;
    }



}
