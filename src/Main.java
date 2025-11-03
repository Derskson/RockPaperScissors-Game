import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase principal del juego que maneja la lógica de movimiento y la interacción del jugador con el mapa.
 */
public class Main {
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
    public static void main(String[] args) throws IOException {
        GameMap map = new GameMap(Map);
        Scanner sc = new Scanner(System.in);
        Player jugador = new Player(150,10,null, 0.0f, new Position(1,1), '¡');
        map.addObject(jugador);
        Villain subdito1 = new Villain(100,10,"Tijeras", new Position(2,2), '1', 1);
        map.addObject(subdito1);
        Villain peleador2 = new Villain(200, 15, "Papel", new Position(6, 5),'2', 2);
        map.addObject(peleador2);
        Villain jefe3 = new Villain(300, 30,"Piedra", new Position(7,7), '3', 3);
        map.addObject(jefe3);

        System.out.println("""
                Selecciona tu movimiento favorito con los siguientes números:
                [1]:Piedra
                [2]:Papel
                [3]:Tijeras""");
        int input = sc.nextInt();
        switch (input){
            case Game.PIEDRA -> jugador.setFavoriteMov("Piedra");
            case Game.PAPEL -> jugador.setFavoriteMov("Papel");
            case Game.TIJERA -> jugador.setFavoriteMov("Tijeras");
        }
        Game.chooseMov(input, jugador);
        System.out.println(jugador.getFavoriteMov());



        char Enter = '\n';
        System.out.println(jugador.getPosition());
        System.out.println("Presiona W,A,S,D para moverte. Q para salir." +
                "Después de cada movimiento presiona Enter para continuar");
        char key = Enter;
        while (key!= 'Q') {

            Game.printMap(map, Map);


            System.out.println("Key pressed: " + key);
            Movement.movePlayer(jugador.getPosition(), key, Map);
            map.isSomeoneHere(jugador);
            System.out.println("Position after move: " + jugador.getPosition());
            key = Movement.readKey();
            Game.clearConsole();
        }
        System.out.println("Juego terminado");
    }
}