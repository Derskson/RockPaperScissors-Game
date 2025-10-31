import java.io.IOException;

/**
 * Clase principal del juego que maneja la lógica de movimiento y la interacción del jugador con el mapa.
 */
public class Main {
    static char[][] Map = {
            "####################".toCharArray(),
            "#         #         #".toCharArray(),
            "#  ####  #  ###### #".toCharArray(),
            "#        #         #".toCharArray(),
            "######  #########  #".toCharArray(),
            "#                  #".toCharArray(),
            "#  #################".toCharArray(),
            "#                  #".toCharArray(),
            "####################".toCharArray()
    };
    public static void main(String[] args) throws IOException {
        GameMap map = new GameMap(Map);
        Position myPosition = new Position(1,1);

        Player jugador = new Player(100,10,"Papel", 0.0f, new Position(1,1), '¡');
        map.addObject(jugador);
        Villain subdito = new Villain(100,10,"Tijera", new Position(2,2), '1', 1);
        map.addObject(subdito);


        char Enter = '\n';
        System.out.println(jugador.getPosition());
        System.out.println("Presiona W,A,S,D para moverte. Q para salir." +
                "Después de cada movimiento presiona Enter para continuar");
        char key = Enter;
        while (key!= 'Q') {

            Game.printMap(map, Map);


            System.out.println("Key pressed: " + key);
            Movement.movePlayer(jugador.getPosition(), key, Map);
            System.out.println("Position after move: " + jugador.getPosition());
            key = Movement.readKey();
            Game.clearConsole();
        }
        System.out.println("Juego terminado");
    }
}