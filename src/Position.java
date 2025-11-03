/**
 * <h2>Clase Position</h2>
 * Representa una posición en un plano bidimensional mediante coordenadas X e Y.
 * Esta clase se utiliza para controlar el movimiento de objetos dentro del mapa del juego.
 */
public class Position {

    /** Coordenada horizontal (eje X) de la posición. */
    private int posX;

    /** Coordenada vertical (eje Y) de la posición. */
    private int posY;

    /**
     * Crea una nueva posición con las coordenadas especificadas.
     *
     * @param posX valor inicial del eje X.
     * @param posY valor inicial del eje Y.
     */
    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    // ===========================
    // Getters y Setters
    // ===========================

    /**
     * Obtiene la coordenada X actual.
     *
     * @return valor de {@code posX}.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Establece una nueva coordenada X.
     *
     * @param posX nuevo valor para {@code posX}.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Obtiene la coordenada Y actual.
     *
     * @return valor de {@code posY}.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Establece una nueva coordenada Y.
     *
     * @param posY nuevo valor para {@code posY}.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // ===========================
    // Métodos de movimiento
    // ===========================

    /**
     * Mueve la posición una unidad hacia arriba (disminuye Y en 1).
     */
    public void moveUp() {
        posY -= 1;
    }

    /**
     * Mueve la posición una unidad hacia abajo (incrementa Y en 1).
     */
    public void moveDown() {
        posY += 1;
    }

    /**
     * Mueve la posición una unidad hacia la izquierda (disminuye X en 1).
     */
    public void moveLeft() {
        posX -= 1;
    }

    /**
     * Mueve la posición una unidad hacia la derecha (incrementa X en 1).
     */
    public void moveRight() {
        posX += 1;
    }

    // ===========================
    // Representación en texto
    // ===========================

    /**
     * Devuelve una representación en texto de la posición.
     *
     * @return una cadena con el formato {@code Position{posX=..., posY=...}}.
     */
    @Override
    public String toString() {
        return "Position{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
