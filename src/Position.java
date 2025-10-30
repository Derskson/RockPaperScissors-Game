/**
 * <h2>Clase Posición</h2>
 * Esta clase representa una posición utilizando las coordenadas X Y
 */
public class Position {
    private int posX;
    private int posY;

    //Definición del constructor con parámetros

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    //Getters y setters

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    //Movimiento de la posición
    public void moveUp(){
        posY -= 1;
    }
    public void moveDown(){
        posY += 1;
    }
    public void moveLeft() {
        posX -= 1;
    }
    public void moveRight() {
        posX += 1;
    }

    //To string
    @Override
    public String toString() {
        return "Position{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
