import java.io.IOException;

/**
 * <h2>Clase Movement</h2>
 * Gestiona el movimiento del jugador dentro del mapa del juego.
 *
 * <p>Contiene métodos para leer la tecla ingresada por el usuario
 * y actualizar la posición del jugador según la dirección seleccionada,
 * verificando además los límites y obstáculos del mapa.</p>
 */
public class Movement {

    /**
     * Lee una tecla desde la entrada estándar y la convierte a mayúscula.
     *
     * <p>Este método permite detectar las teclas de movimiento (W, A, S, D)
     * sin importar si se presionan en mayúscula o minúscula. Además,
     * limpia el buffer de entrada para evitar lecturas múltiples del mismo input.</p>
     *
     * @return la tecla leída en mayúscula.
     * @throws IOException si ocurre un error al leer desde la entrada estándar.
     */
    public static char readKey() throws IOException {
        char key = (char) System.in.read();
        while (System.in.available() > 0) System.in.read(); // Limpia el buffer
        return Character.toUpperCase(key);
    }

    /**
     * Mueve la posición del jugador en el mapa según la dirección ingresada.
     *
     * <p>El movimiento se basa en las siguientes teclas:
     * <ul>
     *   <li><b>W</b> - Mover hacia arriba</li>
     *   <li><b>S</b> - Mover hacia abajo</li>
     *   <li><b>A</b> - Mover hacia la izquierda</li>
     *   <li><b>D</b> - Mover hacia la derecha</li>
     * </ul></p>
     *
     * <p>Si el jugador intenta moverse hacia una casilla con un obstáculo ('#'),
     * el movimiento se cancela y se restaura la posición anterior.</p>
     *
     * @param playerPos la posición actual del jugador.
     * @param direction la dirección en la que se desea mover al jugador.
     * @param map       el mapa del juego, donde se verifican obstáculos.
     */
    public static void movePlayer(Position playerPos, char direction, char[][] map) {
        int previousX = playerPos.getPosX();
        int previousY = playerPos.getPosY();

        switch (direction) {
            case 'W':
                playerPos.moveUp();
                break;
            case 'S':
                playerPos.moveDown();
                break;
            case 'A':
                playerPos.moveLeft();
                break;
            case 'D':
                playerPos.moveRight();
                break;
            default:
                System.out.println("Dirección no válida. Usa W, A, S, D para moverte.");
                return;
        }

        // Verifica si la nueva posición es válida (sin obstáculos)
        if (map[playerPos.getPosY()][playerPos.getPosX()] == '#') {
            System.out.println("No puedes moverte en esa dirección, hay un obstáculo.");
            playerPos.setPosX(previousX);
            playerPos.setPosY(previousY);
        }
    }
}
