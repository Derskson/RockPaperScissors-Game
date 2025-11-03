import java.io.IOException;
import java.util.Scanner;

/**
 * -----------------------------------------------------------------------------
 * Proyecto: Piedra, Papel o Tijera RPG
 * Archivo: Main.java
 * Versión: 1.0
 * Autor: Andersson G. Jaime (github: Derskson)
 * -----------------------------------------------------------------------------
 *
 * <h2>Clase Main</h2>
 * Clase principal del juego que controla la ejecución general, el movimiento del jugador
 * y las interacciones con los enemigos dentro del mapa.
 *
 * <p>En esta clase se inicializan los objetos del juego (jugador y villanos),
 * se configura el mapa y se maneja el bucle principal de juego.</p>
 *
 * <p>El jugador puede moverse utilizando las siguientes teclas:
 * <ul>
 *   <li><b>W</b>: arriba</li>
 *   <li><b>A</b>: izquierda</li>
 *   <li><b>S</b>: abajo</li>
 *   <li><b>D</b>: derecha</li>
 *   <li><b>Q</b>: salir del juego</li>
 * </ul>
 * </p>
 */
public class Main {

    /**
     * Representación del mapa del juego en forma de matriz bidimensional de caracteres.
     * Los símbolos tienen los siguientes significados:
     * <ul>
     *   <li><b>#</b>: pared u obstáculo</li>
     *   <li><b>espacio</b>: área transitable</li>
     * </ul>
     */
    static char[][] Map = {
            "####################".toCharArray(),
            "#        #         #".toCharArray(),
            "#  ####  #  ###### #".toCharArray(),
            "#        #         #".toCharArray(),
            "######  #########  #".toCharArray(),
            "#                  #".toCharArray(),
            "#  #################".toCharArray(),
            "#                  #".toCharArray(),
            "####################".toCharArray()
    };

    /**
     * Método principal del programa. Configura los objetos del juego (jugador y villanos),
     * permite seleccionar el movimiento favorito del jugador y ejecuta el bucle principal
     * de movimiento e interacción.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws IOException si ocurre un error al leer la entrada del teclado.
     */
    public static void main(String[] args) throws IOException {
        GameMap map = new GameMap();
        Scanner sc = new Scanner(System.in);

        // --- Creación del jugador ---
        Player jugador = new Player(150, 10, null, new Position(1, 1), '¡');
        map.addObject(jugador);

        // --- Creación de villanos ---
        Villain subdito1 = new Villain(100, 10, "Tijeras", new Position(2, 2), '1', 1);
        Villain subdito2 = new Villain(100, 10, "Papel", new Position(7, 2), '1', 1);
        Villain peleador1 = new Villain(200, 15, "Papel", new Position(15, 5), '2', 2);
        Villain peleador2 = new Villain(200, 15, "Papel", new Position(2, 6), '2', 2);
        Villain jefe1 = new Villain(300, 30, "Piedra", new Position(7, 7), '3', 3);
        Villain jefe2 = new Villain(300, 30, "Piedra", new Position(16, 1), '3', 3);

        // Añadir villanos al mapa
        map.addObject(subdito1);
        map.addObject(subdito2);
        map.addObject(peleador1);
        map.addObject(peleador2);
        map.addObject(jefe1);
        map.addObject(jefe2);

        // --- Selección del movimiento favorito ---
        System.out.println("""
                Selecciona tu movimiento favorito con los siguientes números:
                [1]: Piedra
                [2]: Papel
                [3]: Tijeras
                """);
        int input = sc.nextInt();
        switch (input) {
            case Game.PIEDRA -> jugador.setFavoriteMov("Piedra");
            case Game.PAPEL -> jugador.setFavoriteMov("Papel");
            case Game.TIJERA -> jugador.setFavoriteMov("Tijeras");
        }
        Game.chooseMov(input, jugador);

        System.out.println("Tu movimiento favorito es: " + jugador.getFavoriteMov());
        System.out.println("Presiona W, A, S, D para moverte. Q para salir.");
        System.out.println("Después de cada movimiento, presiona Enter para continuar.");

        // --- Bucle principal del juego ---
        char key = '\n';
        while (key != 'Q') {
            Game.printMap(map, Map);
            Movement.movePlayer(jugador.getPosition(), key, Map);
            map.isSomeoneHere(jugador);
            key = Movement.readKey();
            Game.clearConsole();

            // Comprobación de victoria
            if (map.getObjects().size() == 1 && map.getObjects().getFirst() instanceof Player) {
                System.out.println("¡Has derrotado a todos los enemigos! ¡Eres el campeón!");
                break;
            }
        }

        System.out.println("Juego terminado.");
    }
}
