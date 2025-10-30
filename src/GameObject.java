public class GameObject {

    private Position position;
    private char symbolToDraw;

    public GameObject(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public char getSymbol() {
        return symbolToDraw;
    }

    public void setSymbol(char symbol) {
        this.symbolToDraw = symbol;
    }

}
