import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static char[][] Map = {
            "####################".toCharArray(),
            "#12345678#123456789#".toCharArray(),
            "#  ####  #  ###### #".toCharArray(),
            "#        #         #".toCharArray(),
            "######  #########  #".toCharArray(),
            "#                  #".toCharArray(),
            "#  #################".toCharArray(),
            "#                  #".toCharArray(),
            "####################".toCharArray()
    };
    public static void main(String[] args) throws IOException {
        Position myPosition = new Position(1,1);
        System.out.println("Presiona W,A,S,D para moverte. Q para salir. Presiona Enter para continuar");
        char key = '\n';
        while (key!= 'Q') {
            Game.printMap(myPosition, Map);
            System.out.println("Key pressed: " + key);
            Movement.movePlayer(myPosition, key, Map);
            System.out.println("Position after move: " + myPosition);
            key = Movement.readKey();
        }

    }
}