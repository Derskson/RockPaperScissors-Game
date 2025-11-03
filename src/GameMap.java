import java.util.ArrayList;
import java.util.List;

public class GameMap {


    private final List<GameObject> gameObjects;

    public GameMap(char[][] map) {
        this.gameObjects = new ArrayList<>();

    }

    /**
     * Con este método podemos añadir objetos a la lista
     */
    public void addObject(GameObject obj){
        gameObjects.add(obj);
    }

    /**
     * Este metodo recorrer el mapa y ubicar los objetos en ello
     *
     */
    public void drawMap(char[][] map){
        for (int y = 0; y < map.length; ++y){
            for (int x = 0; x < map[y].length; ++x){
                boolean isObjectHere = false;
                for (GameObject obj: gameObjects){
                    if ( x == obj.getPosition().getPosX() && y == obj.getPosition().getPosY() ){
                        System.out.print(obj.getSymbol());
                        isObjectHere = true;
                        break;//Este break es para el for de GameObject para que siga con el recorrido del mapa
                    }
                }
                if (!isObjectHere) System.out.print(map[y][x]);
            }
            System.out.println();
        }

    }

    public void isSomeoneHere(Player player){
        for (GameObject obj: gameObjects){
            if (obj instanceof Villain v){
                if (player.getPosition().getPosX() == v.getPosition().getPosX() &&
                    player.getPosition().getPosY() == v.getPosition().getPosY()){
                    System.out.println("Te ha encontrado a un enemigo de nivel:"+v.getLevel());
                    System.out.println("¡Cuidado! Tiene "+v.getFavoriteMov()+" como movimiento favorito");
                    Game.renderBattle(player, v);
                }
            }
        }
    }

}
