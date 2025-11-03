import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Clase GameMap</h2>
 * Representa el mapa del juego, encargado de gestionar y renderizar todos los objetos
 * que existen dentro del entorno, como jugadores, villanos u otros elementos interactivos.
 *
 * Esta clase mantiene una lista estática de todos los objetos del juego y
 * se encarga de dibujar el mapa en la consola.
 */
public class GameMap {

    /** Lista estática que almacena todos los objetos actualmente presentes en el mapa. */
    private static final List<GameObject> gameObjects = new ArrayList<>();

    // ===========================
    // Gestión de objetos del mapa
    // ===========================

    /**
     * Añade un objeto al mapa.
     *
     * @param obj objeto del tipo {@link GameObject} que se desea añadir al mapa.
     */
    public void addObject(GameObject obj) {
        gameObjects.add(obj);
    }

    /**
     * Elimina un objeto del mapa.
     *
     * @param obj objeto del tipo {@link GameObject} que se desea eliminar del mapa.
     */
    public static void removeObject(GameObject obj) {
        gameObjects.remove(obj);
    }

    /**
     * Obtiene la lista completa de objetos presentes en el mapa.
     *
     * @return lista de objetos {@link GameObject} activos en el mapa.
     */
    public List<GameObject> getObjects() {
        return gameObjects;
    }

    // ===========================
    // Renderizado del mapa
    // ===========================

    /**
     * Dibuja el mapa actual en la consola, colocando los objetos en sus respectivas posiciones.
     *
     * <p>El método recorre la matriz del mapa y, si encuentra un objeto en una
     * coordenada determinada, imprime su símbolo en lugar del carácter del mapa base.</p>
     *
     * @param map matriz bidimensional de caracteres que representa la estructura del mapa.
     */
    public void drawMap(char[][] map) {
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[y].length; ++x) {
                boolean isObjectHere = false;

                // Recorremos la lista de objetos del mapa
                for (GameObject obj : gameObjects) {
                    if (x == obj.getPosition().getPosX() && y == obj.getPosition().getPosY()) {
                        System.out.print(obj.getSymbol());
                        isObjectHere = true;
                        break; // Sale del bucle de objetos, continúa el recorrido del mapa
                    }
                }

                // Si no hay ningún objeto, imprime el carácter base del mapa
                if (!isObjectHere) System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    // ===========================
    // Interacción entre objetos
    // ===========================

    /**
     * Verifica si el jugador ha encontrado un villano en su posición actual.
     *
     * <p>Si el jugador ocupa la misma casilla que un villano, se inicia automáticamente
     * una batalla entre ambos. En caso de que el villano sea derrotado, es eliminado del mapa.</p>
     *
     * @param player instancia de {@link Player} cuya posición se evalúa dentro del mapa.
     */
    public void isSomeoneHere(Player player) {
        Villain found = null;

        // 1) Buscar villano en la misma casilla que el jugador
        for (GameObject obj : gameObjects) {
            if (obj instanceof Villain v) {
                if (player.getPosition().getPosX() == v.getPosition().getPosX()
                        && player.getPosition().getPosY() == v.getPosition().getPosY()) {
                    found = v;
                    break; // Villano encontrado, salimos del bucle
                }
            }
        }

        // 2) Si hay un villano, iniciar batalla
        if (found != null) {
            System.out.println("Te has encontrado con un enemigo de nivel: " + found.getLevel());
            System.out.println("¡Cuidado! Tiene " + found.getFavoriteMov() + " como movimiento favorito.");

            Game.renderBattle(player, found);

            // 3) Si el villano fue derrotado, eliminarlo del mapa
            if (found.getHealth() <= 0) {
                removeObject(found);
                System.out.println("El villano ha sido eliminado del mapa.");
            }
        }
    }
}
