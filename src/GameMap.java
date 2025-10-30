import java.util.ArrayList;
import java.util.List;

public class GameMap {


    public final List<GameObject> gameObjects;

    public GameMap(char[][] map) {
        this.gameObjects = new ArrayList<>();
    }

    /**
     * Con este método podemos añadir objetos a la lista
     * @param obj
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
                boolean draw = false;
                for (GameObject obj: gameObjects){
                    if ( x == obj.getPosition().getPosX() && y == obj.getPosition().getPosY() ){
                        System.out.print(obj.getSymbol());
                        draw = true;
                        break;//Este break es para el for de GameObject para que siga con el recorrido del mapa
                    }
                    else
                        System.out.print(map[y][x]);
                }
            }
            System.out.println();
        }

    }

}
