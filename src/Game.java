import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    static final int PIEDRA = 1;
    static final int PAPEL = 2;
    static final int TIJERA = 3;


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

    public static void chooseMov(int mov, Player player){
        switch (mov){
            case 1:
                System.out.println("Haz elegido Piedra");
                player.setFavoriteMov("Piedra");
                break;
            case 2:
                System.out.println("Haz elegido Papel");
                player.setFavoriteMov("Papel");
                break;
            case 3:
                System.out.println("Haz elegido Tijeras");
                player.setFavoriteMov("Tijeras");
                break;
        }
    }

    public static void renderLife(int lifePlayer, int lifeVillain){
        int initialLifePlayer = lifePlayer;
        int initialLifeVillain = lifeVillain;
        int barSize = 20;
        int lifeBarPlayer = (lifePlayer*barSize/initialLifePlayer);
        int lifeBarVillain = (lifeVillain*barSize/initialLifeVillain);
        String BarPlayer = "⣿".repeat(lifePlayer)+"-".repeat(barSize*initialLifePlayer);
        String BarVillain = "⣿".repeat(lifeVillain)+"-".repeat(barSize*initialLifeVillain);

        System.out.println("Jugador: ["+BarPlayer+"] "+ lifePlayer +" HP");
        System.out.println("Villano: ["+BarVillain+"] "+lifeVillain+" HP");

    }

    public static int aleatoryMov(){
        return ThreadLocalRandom.current().nextInt(1,4);
    }

    public static int RockPaperScissors(int playerMov, int villainMov){
        if ( playerMov == villainMov){
            System.out.println("Es un Empate!!");
            return 0;
        }

        if (    ( playerMov == PIEDRA && villainMov == TIJERA) ||
                ( playerMov == PAPEL && villainMov == PIEDRA) ||
                ( playerMov == TIJERA && villainMov == PAPEL)){
            System.out.println("Buen movimiento! Ganaste el Turno");
            return 1;
        }
        else System.out.println("¡Que mal! tu contrincante gana este Turno");return -1;

    }

    public static void renderBattle(Player player, Villain villain){
        Scanner sc = new Scanner(System.in);
        int lifePlayer = player.getHealth();
        int lifeVillain = villain.getHealth();
        renderLife(lifePlayer,lifeVillain);
        while(lifePlayer>0 || lifeVillain>0){
            System.out.println("""
                Selecciona un movimiento
                
                [1]:Piedra
                [2]:Papel
                [3]:Tijeras
                """);
            int movPlayer = sc.nextInt();
            int movVillain = aleatoryMov();
            System.out.println("Tu contrincante saca: "+
                    (movVillain==PIEDRA ? "Piedra" :
                        movVillain==PAPEL ? "Papel" :
                        "Tijeras"));
            int result = RockPaperScissors(movPlayer, movVillain);
            if(result==1){
                lifeVillain-=player.getDamage();
            }
            else lifePlayer-=villain.getDamage();
            renderLife(lifePlayer,lifeVillain);

        }
    }
}
