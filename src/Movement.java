import java.io.IOException;

public class Movement {

    public static char readKey() throws IOException {
        char key = (char) System.in.read();
        while (System.in.available() > 0) System.in.read();// Limpiar el buffer
        return Character.toUpperCase(key);
    }
}
