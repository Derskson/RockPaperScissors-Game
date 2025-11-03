/**
 * <h2>Clase Player</h2>
 * Representa al jugador dentro del juego.
 * Hereda de {@link GameCharacter}, obteniendo así los atributos y comportamientos básicos
 * como salud, daño, movimiento y posición.
 *
 * <p>Esta clase puede ser extendida en el futuro para añadir funcionalidades específicas
 * del jugador, como inventario, experiencia o habilidades especiales.</p>
 */
public class Player extends GameCharacter {

    /**
     * Constructor que inicializa un jugador con los parámetros especificados.
     *
     * @param health      cantidad de salud del jugador
     * @param damage      daño que el jugador puede infligir
     * @param favoriteMov movimiento favorito del jugador
     * @param position    posición inicial del jugador en el mapa
     * @param symbol      símbolo que representa al jugador en la interfaz
     */
    public Player(int health, int damage, String favoriteMov, Position position, char symbol) {
        super(health, damage, favoriteMov, position, symbol);
    }
}
