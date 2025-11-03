/**
 * <h2>Clase Villain</h2>
 * Representa a un villano dentro del juego.
 * Extiende de {@link GameCharacter}, por lo que hereda sus atributos y comportamientos básicos
 * (como salud, daño, movimiento y posición).
 *
 * <p>Además, incluye un atributo adicional <b>level</b> que indica la dificultad o experiencia del villano.</p>
 */
public class Villain extends GameCharacter {

    /** Nivel del villano, que determina su fuerza o dificultad dentro del juego. */
    private int level;

    /**
     * Constructor que inicializa un villano con los parámetros especificados.
     *
     * @param health      cantidad de salud del villano
     * @param damage      daño que el villano puede infligir
     * @param favoriteMov movimiento favorito del villano
     * @param position    posición inicial del villano en el mapa
     * @param symbol      símbolo que representa al villano en la interfaz
     * @param level       nivel del villano
     */
    public Villain(int health, int damage, String favoriteMov, Position position, char symbol, int level) {
        super(health, damage, favoriteMov, position, symbol);
        this.level = level;
    }

    /**
     * Obtiene el nivel actual del villano.
     *
     * @return nivel del villano
     */
    public int getLevel() {
        return level;
    }

    /**
     * Establece un nuevo nivel para el villano.
     *
     * @param level nuevo nivel a asignar
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
