import java.util.Random;

public class Laberinto {

    private char[][] matrix;
    private int filas;
    private int columnas;
    private static final char OBSTACULO = '*';
    private static final char ENTRADA = 'I';
    private static final char SALIDA = 'G';
    private static final char CAMINO = '+';
    private static final char BLANCO = ' ';
    private Random rnd;

    public Laberinto(int filas, int columnas, double obstaculos) {

        matrix = new char[filas][columnas];
        rnd = new Random();
        this.filas = filas;
        this.columnas = columnas;
        generarLaberinto(obstaculos);

    }
    public Laberinto(int filas, int columnas) {
        this(filas,columnas,0.3);
    }

    private void generarLaberinto(double obs) {
        // Inicializar matriz
        for(int i = 0; i < filas; i++) {
            for(int j = 0; j < columnas; j++) {
                matrix[i][j] = BLANCO;
            }
        }

        int max = (filas > columnas)? filas : columnas;
        int entradaX = 0, entradaY = 0;

        // Generar entrada
        boolean encontrado = false;
        while (!encontrado) {
            int posX = rnd.nextInt(0,max);
            int posY = rnd.nextInt(0,max);
            if (esValido(posX,posY)) {
                matrix[posX][posY] = ENTRADA;
                entradaX = posX;
                entradaY = posY;
                encontrado = true;
            }
        }

        // Generar salida
        encontrado = false;
        while (!encontrado) {
            int posX = rnd.nextInt(0,max);
            int posY = rnd.nextInt(0,max);
            if (esValido(posX,posY) && !(posX == entradaX && posY == entradaY)) {
                matrix[posX][posY] = SALIDA;
                encontrado = true;
            }
        }

        // Generar obstaculos
        double numObs = filas*columnas*obs;             // Numero de obstaculos que debe de haber
        while (numObs > 0) {
            int posX = rnd.nextInt(0,max);
            int posY = rnd.nextInt(0,max);
            if (esValido(posX,posY)) {
                matrix[posX][posY] = OBSTACULO;
                numObs--;
            }
        }
    }

    private boolean esValido(int posX, int posY) {
        boolean dentroX = posX >= 0 && posX < filas;
        boolean dentroY = posY >= 0 && posY < columnas;
        return dentroX && dentroY && matrix[posX][posY] == BLANCO;
    }
    //Clase algoritmo a con los metos de la lista ordenada, conjunto, etc
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Laberinto: \n");
        for(int i = 0; i < filas; i++) {
            for(int j = 0; j < columnas; j++) {
                sb.append(matrix[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}