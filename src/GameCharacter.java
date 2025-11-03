/**
 * <h2>Clase GameCharacter</h2>
 * Representa un personaje dentro del juego, ya sea el jugador o un villano.
 *
 * <p>Esta clase extiende de {@link GameObject} e introduce características comunes
 * como la salud, el daño y el movimiento favorito. Sirve como clase base
 * para las subclases {@link Player} y {@link Villain}.</p>
 */
public class GameCharacter extends GameObject {

    /** Cantidad de puntos de vida del personaje. */
    private int health;

    /** Daño base que el personaje puede infligir en combate. */
    private int damage;

    /** Movimiento o ataque preferido del personaje. */
    private String favoriteMov;

    /**
     * Constructor principal para inicializar un personaje del juego.
     *
     * @param health       cantidad inicial de salud del personaje.
     * @param damage       daño base que puede causar.
     * @param favoriteMov  movimiento o ataque preferido.
     * @param position     posición inicial en el mapa.
     * @param symbol       símbolo con el que se representa al personaje en el mapa.
     */
    public GameCharacter(int health, int damage, String favoriteMov, Position position, char symbol) {
        super(position, symbol);
        this.health = health;
        this.damage = damage;
        this.favoriteMov = favoriteMov;
    }

    /**
     * Obtiene la cantidad de salud actual del personaje.
     *
     * @return la salud actual.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Establece una nueva cantidad de salud para el personaje.
     *
     * @param health nueva salud.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Obtiene el daño base del personaje.
     *
     * @return el daño.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Establece un nuevo valor de daño para el personaje.
     *
     * @param damage nuevo valor de daño.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Obtiene el movimiento favorito del personaje.
     *
     * @return el movimiento favorito.
     */
    public String getFavoriteMov() {
        return favoriteMov;
    }

    /**
     * Establece el movimiento favorito del personaje.
     *
     * @param favoriteMov nuevo movimiento favorito.
     */
    public void setFavoriteMov(String favoriteMov) {
        this.favoriteMov = favoriteMov;
    }
}
