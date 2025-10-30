import java.io.IOException;

public class Movement {

    /**
     * Lee una tecla desde la entrada estándar y la devuelve en mayúscula.
     * @return La tecla leída en mayúscula.
     * @throws IOException
     */
    public static char readKey() throws IOException {
        char key = (char) System.in.read();
        while (System.in.available() > 0) System.in.read();// Limpiar el buffer
        return Character.toUpperCase(key);
    }

    /**
     * Mueve la posición del jugador según la tecla de dirección presionada
     *
     * @param playerPos
     * @param direction
     * @param map
     */
    public static void movePlayer(Position playerPos, char direction, char[][] map){
        int previousX = playerPos.getPosX();
        int previousY = playerPos.getPosY();

        switch (direction){
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
                System.out.println("Dirección no válida. Usa W, A, S, D para moverte.");return;
        }
        if (map[playerPos.getPosY()][playerPos.getPosX()] == '#'){
            System.out.println("No puedes moverte en esa dirección, hay un obstáculo.");
            playerPos.setPosX(previousX);
            playerPos.setPosY(previousY);
        }
    }
}
