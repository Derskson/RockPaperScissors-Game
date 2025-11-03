import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    public static final int PIEDRA = 1;
    public static final int PAPEL = 2;
    public static final int TIJERA = 3;


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

    public static void renderLife(int lifePlayer, Player player, int lifeVillain, Villain villain) {
        int barSize = 20;

        int lifeBarPlayer = (lifePlayer * barSize/player.getHealth());
        int lifeBarVillain = (lifeVillain * barSize/villain.getHealth());

        if (lifeBarVillain <= 0) lifeBarVillain = 0;
        if (lifeBarPlayer <= 0) lifeBarPlayer = 0;

        String BarPlayer = "⣿".repeat(lifeBarPlayer)+"-".repeat(barSize-lifeBarPlayer);
        String BarVillain = "⣿".repeat(lifeBarVillain)+"-".repeat(barSize-lifeBarVillain);
        System.out.println("Jugador: "+BarPlayer+" "+lifePlayer +'/'+player.getHealth());
        System.out.println("Villano: "+BarVillain+" "+lifeVillain+'/'+villain.getHealth());
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

    public static void rewardPlayer(Player player, Villain villain){
        float reward = villain.getLevel()*20;// Dependiendo el nivel del villano así mismo ganará monedas
        player.setMoney(player.getMoney()+reward);
        System.out.println("Has ganado: "+player.getMoney()+" monedas!");
    }

    static boolean checkLife(int life) {
        return life <= 0;
    }

    public static void renderBattle(Player player, Villain villain){
        Scanner sc = new Scanner(System.in);

        int lifePlayer = player.getHealth();
        int lifeVillain = villain.getHealth();
        renderLife(lifePlayer, player, lifeVillain, villain);

        while(!checkLife(lifePlayer) && !checkLife(lifeVillain)){
            System.out.println("""
                Selecciona un movimiento
                
                [1]:Piedra
                [2]:Papel
                [3]:Tijeras
                """);
            int choice = sc.nextInt();
            int choiceVillain = aleatoryMov();

            String movPlayer = switch (choice){
                case PIEDRA -> "Piedra";
                case PAPEL -> "Papel";
                case TIJERA -> "Tijeras";
                default -> "Piedra";
            };
            String movVillain = switch (choiceVillain){
                case PIEDRA -> "Piedra";
                case PAPEL -> "Papel";
                case TIJERA -> "Tijeras";
                default -> "Piedra";
            };

            System.out.println("Tu contrincante saca: " + movVillain);
            int result = RockPaperScissors(choice, choiceVillain);

            if (result == 1) {
                int dmg = movPlayer.equals(player.getFavoriteMov()) ? player.getDamage() * 2 : player.getDamage();
                System.out.println("¡Golpeas con fuerza y haces " + dmg + " de daño!");
                lifeVillain -= dmg;

                if (checkLife(lifeVillain)) {
                    System.out.println("¡Has derrotado al villano!");
                    rewardPlayer(player, villain);
                    break;
                }
            } else if (result == -1) {
                int dmg = movVillain.equals(villain.getFavoriteMov()) ? villain.getDamage() * 2 : villain.getDamage();
                System.out.println("El villano contraataca e inflige " + dmg + " de daño!");
                lifePlayer -= dmg;

                if (checkLife(lifePlayer)) {
                    System.out.println("Has sido derrotado...");
                    break;
                }
            } else {
                System.out.println("Ambos esquivan el ataque.");
            }

            renderLife(lifePlayer, player, lifeVillain, villain);
        }

    }
}
