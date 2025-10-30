public class Game {

    public static void printMap(Position player, char[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (x == player.getPosX() && y == player.getPosY())
                    System.out.print("¡");
                else
                    System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

}
