import java.io.IOException;

public class Game {

    /**
     * Imprime el mapa con la posición del jugador
     * @param player position
     * @param map char[][]
     */

    public static void printMap(GameMap draw, char[][] map){
        draw.drawMap(map);
    }

    /**
     * Limpia la consola para cualquier sistema operativo
     */
    public static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al limpiar la consola: " + ex.getMessage());
        }
    }
}
