import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase principal que gestiona la lógica del juego de batalla "Piedra, Papel o Tijera".
 * Contiene métodos para renderizar la batalla, manejar movimientos, calcular resultados,
 * actualizar estadísticas y limpiar la consola.
 */
public class Game {

    /** Constantes que representan los posibles movimientos del jugador o villano. */
    public static final int PIEDRA = 1;
    public static final int PAPEL = 2;
    public static final int TIJERA = 3;

    /**
     * Imprime el mapa del juego utilizando el objeto {@link GameMap}.
     *
     * @param draw instancia de {@link GameMap} encargada de dibujar el mapa.
     * @param map  matriz bidimensional que representa el mapa actual del juego.
     */
    public static void printMap(GameMap draw, char[][] map) {
        draw.drawMap(map);
    }

    /**
     * Limpia la consola de acuerdo con el sistema operativo en uso.
     * Es compatible con Windows, Linux y macOS.
     */
    public static void clearConsole() {
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

    /**
     * Permite elegir y asignar el movimiento favorito del jugador.
     *
     * @param mov    movimiento elegido (1: Piedra, 2: Papel, 3: Tijeras)
     * @param player objeto {@link Player} al que se le asignará el movimiento.
     */
    public static void chooseMov(int mov, Player player) {
        switch (mov) {
            case 1 -> {
                System.out.println("Haz elegido Piedra");
                player.setFavoriteMov("Piedra");
            }
            case 2 -> {
                System.out.println("Haz elegido Papel");
                player.setFavoriteMov("Papel");
            }
            case 3 -> {
                System.out.println("Haz elegido Tijeras");
                player.setFavoriteMov("Tijeras");
            }
        }
    }

    /**
     * Renderiza las barras de vida del jugador y el villano en pantalla.
     *
     * @param lifePlayer  vida actual del jugador.
     * @param player      objeto {@link Player} que participa en la batalla.
     * @param lifeVillain vida actual del villano.
     * @param villain     objeto {@link Villain} que participa en la batalla.
     */
    public static void renderLife(int lifePlayer, Player player, int lifeVillain, Villain villain) {
        int barSize = 20;

        int lifeBarPlayer = (lifePlayer * barSize / player.getHealth());
        int lifeBarVillain = (lifeVillain * barSize / villain.getHealth());

        if (lifeBarVillain <= 0) lifeBarVillain = 0;
        if (lifeBarPlayer <= 0) lifeBarPlayer = 0;

        String BarPlayer = "⣿".repeat(lifeBarPlayer) + "-".repeat(barSize - lifeBarPlayer);
        String BarVillain = "⣿".repeat(lifeBarVillain) + "-".repeat(barSize - lifeBarVillain);

        System.out.println("Jugador: " + BarPlayer + " " + lifePlayer + '/' + player.getHealth());
        System.out.println("Villano: " + BarVillain + " " + lifeVillain + '/' + villain.getHealth());
    }

    /**
     * Genera un movimiento aleatorio entre 1 y 3 para el villano.
     *
     * @return número aleatorio (1: Piedra, 2: Papel, 3: Tijeras).
     */
    public static int aleatoryMov() {
        return ThreadLocalRandom.current().nextInt(1, 4);
    }

    /**
     * Determina el resultado de una ronda de "Piedra, Papel o Tijeras".
     *
     * @param playerMov  movimiento elegido por el jugador.
     * @param villainMov movimiento elegido por el villano.
     * @return 1 si gana el jugador, -1 si gana el villano, 0 si hay empate.
     */
    public static int RockPaperScissors(int playerMov, int villainMov) {
        if (playerMov == villainMov) {
            System.out.println("Es un Empate!!");
            return 0;
        }

        if ((playerMov == PIEDRA && villainMov == TIJERA) ||
                (playerMov == PAPEL && villainMov == PIEDRA) ||
                (playerMov == TIJERA && villainMov == PAPEL)) {
            System.out.println("Buen movimiento! Ganaste el Turno");
            return 1;
        } else {
            System.out.println("¡Que mal! tu contrincante gana este Turno");
            return -1;
        }
    }

    /**
     * Recompensa al jugador después de derrotar a un villano.
     * Se otorgan puntos de vida y daño adicionales basados en el nivel del villano.
     *
     * @param player  jugador que gana la batalla.
     * @param villain villano derrotado.
     */
    public static void rewardPlayer(Player player, Villain villain) {
        int level = villain.getLevel();

        int lifeBonus = level * 20;   // +20 HP por nivel del villano
        int damageBonus = level * 5;  // +5 daño por nivel del villano

        player.setHealth(player.getHealth() + lifeBonus);
        player.setDamage(player.getDamage() + damageBonus);

        System.out.println("¡Has derrotado al villano de nivel " + level + "!");
        System.out.println("Ganas +" + lifeBonus + " HP y +" + damageBonus + " daño.");
        System.out.println("Tus nuevas estadísticas:");
        System.out.println("Vida: " + player.getHealth());
        System.out.println("Daño: " + player.getDamage());
    }

    /**
     * Verifica si la vida de un personaje ha llegado a 0 o menos.
     *
     * @param life cantidad actual de vida.
     * @return {@code true} si el personaje está muerto, {@code false} si aún tiene vida.
     */
    static boolean checkLife(int life) {
        return life <= 0;
    }

    /**
     * Controla el flujo completo de una batalla entre el jugador y un villano.
     * Permite al usuario seleccionar su movimiento, calcula resultados,
     * aplica daño y determina el ganador de la pelea.
     *
     * @param player  jugador controlado por el usuario.
     * @param villain enemigo que enfrenta al jugador.
     */
    public static void renderBattle(Player player, Villain villain) {
        Scanner sc = new Scanner(System.in);

        int lifePlayer = player.getHealth();
        int lifeVillain = villain.getHealth();
        renderLife(lifePlayer, player, lifeVillain, villain);

        while (!checkLife(lifePlayer) && !checkLife(lifeVillain)) {
            int choice = 0;
            boolean validInput = false;

            // Solicitar movimiento del jugador con control de errores
            while (!validInput) {
                try {
                    System.out.println("""
                    Selecciona un movimiento
                    
                    [1]: Piedra
                    [2]: Papel
                    [3]: Tijeras
                    """);
                    choice = sc.nextInt();

                    if (choice < 1 || choice > 3) {
                        System.out.println("Opción no válida. Intenta de nuevo.");
                    } else {
                        validInput = true;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Debes escribir un número (1, 2 o 3).");
                    sc.nextLine(); // Limpia el buffer del scanner
                }
            }

            int choiceVillain = aleatoryMov();

            String movPlayer = switch (choice) {
                case PIEDRA -> "Piedra";
                case PAPEL -> "Papel";
                case TIJERA -> "Tijeras";
                default -> "Piedra";
            };

            String movVillain = switch (choiceVillain) {
                case PIEDRA -> "Piedra";
                case PAPEL -> "Papel";
                case TIJERA -> "Tijeras";
                default -> "Piedra";
            };

            System.out.println("Tu contrincante saca: " + movVillain);
            int result = RockPaperScissors(choice, choiceVillain);

            // Resultado de la ronda
            if (result == 1) {
                int dmg = movPlayer.equals(player.getFavoriteMov()) ? player.getDamage() * 2 : player.getDamage();
                System.out.println("¡Golpeas con fuerza y haces " + dmg + " de daño!");
                lifeVillain -= dmg;

                if (checkLife(lifeVillain)) {
                    System.out.println("¡Has derrotado al villano!");
                    rewardPlayer(player, villain);
                    GameMap.removeObject(villain);
                    break;
                }

            } else if (result == -1) {
                int dmg = movVillain.equals(villain.getFavoriteMov()) ? villain.getDamage() * 2 : villain.getDamage();
                System.out.println("El villano contraataca e inflige " + dmg + " de daño!");
                lifePlayer -= dmg;

                if (checkLife(lifePlayer)) {
                    System.out.println("Has sido derrotado...");
                    System.out.println("Pulsa enter para volver a intentarlo");
                    sc.nextLine(); // limpia buffer
                    sc.nextLine(); // espera ENTER
                    break;
                }
            } else {
                System.out.println("Ambos esquivan el ataque.");
            }

            renderLife(lifePlayer, player, lifeVillain, villain);
        }
    }
}
