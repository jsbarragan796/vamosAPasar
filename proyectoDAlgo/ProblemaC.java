
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * PROBLEMA C Proyecto DAlgo 2017-1
 *
 * @autores Juan Sebastian Barragan – Stephannie Jimenez 201212774 201423727
 */

public class ProblemaC{
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line, "=");
            String sum = st.nextToken();
            String ans = st.nextToken().trim();
            st = new StringTokenizer(sum, "+-", true);
            String[] s = new String[st.countTokens()];
            int count = 0;
            while (st.hasMoreTokens()) {
                s[count] = st.nextToken().trim();
                count++;
            }
            st = new StringTokenizer(ans, "+-", true);
            String[] a = new String[st.countTokens()];
            count = 0;
            while (st.hasMoreTokens()) {
                a[count] = st.nextToken().trim();
                count++;
            }
            alf(s, a);
            line = bf.readLine();
        }
    }
    
    public static void alf(String[] s, String[] a) throws Exception {
        // Arreglo de reglas donde la posicion corresponde al codigo ASCI-65
        // (A=0)
        // -1 no esta en las palabras, -2 es una letra que no esta en el inicio
        // -3 es una letra de inicio no puede ser 0
        int[] restricciones = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1 };
        // Arreglo letras contiene las letras encontradas en las palabras
        char[] letras = new char[25];
        
        // indice letras agregadas a letras
        int letrasAgregadas = 0;
        
        // recorrer todas las palabras antes del igual
        for (String ss : s) {
            char[] palabra = ss.toCharArray();
            for (int i = 0; i < palabra.length; i++) {
                char c = palabra[i];
                if (i == 0 && !(c == 43 || c == 45)) {
                    int esta = restricciones[(int) c - 65];
                    if (esta == -1 || esta == -2) {
                        restricciones[(int) c - 65] = -3;
                        letras[letrasAgregadas] = c;
                        if (esta == -1) {
                            letrasAgregadas++;
                        }
                    }
                } else if (c == 43 || c == 45) {
                    // ignorar si es signo
                } else {
                    int esta = restricciones[(int) c - 65];
                    
                    if (esta == -1) {
                        restricciones[(int) c - 65] = -2;
                        letras[letrasAgregadas] = c;
                        letrasAgregadas++;
                    }
                }
            }
        }
        // recorrer todas las palabras despues del igual
        for (String ss : a) {
            
            char[] palabra = ss.toCharArray();
            for (int i = 0; i < palabra.length; i++) {
                char c = palabra[i];
                // i = 0 es la primera letra de una palabra
                if (i == 0 && !(c == 43 || c == 45)) {
                    int esta = restricciones[(int) c - 65];
                    
                    if (esta == -1 || esta == -2) {
                        restricciones[(int) c - 65] = -3;
                        letras[letrasAgregadas] = c;
                        
                        if (esta == -1) {
                            letrasAgregadas++;
                        }
                    }
                } else if (c == 43 || c == 45) {
                    // ignorar si es signo
                } else {
                    int esta = restricciones[(int) c - 65];
                    if (esta == -1) {
                        restricciones[(int) c - 65] = -2;
                        letras[letrasAgregadas] = c;
                        letrasAgregadas++;
                    }
                }
            }
        }
        
        if (letrasAgregadas > 9) {
            String asigandos2 = "**********";
            System.out.println(asigandos2);
        } else {
            buscarRespuesta(restricciones, letras, s, a);
        }
    }
    
    public static void buscarRespuesta(int[] condiciones, char[] letras, String[] s, String[] a) {
        
        int[] exponentes = { 0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000,};
        Random aleatorio = new Random();
        boolean encontro = false;
        double intentos = factorial(10);
        while (!encontro) {
            
            int[] posible = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1 };
            char[] asigandos = { '*', '*', '*', '*', '*', '*', '*', '*', '*', '*' };
            for (int i = 0; i < letras.length && (int) letras[i] != 0; i++) {
                int letra = letras[i] - 65;
                // si es primera palabra se descarta de la seleccion el 0
                if (condiciones[letra] == -3) {
                    int asignar = aleatorio.nextInt(9) + 1;
                    boolean asignoo = false;
                    while (!asignoo) {
                        if (asigandos[asignar] == '*') {
                            posible[letra] = asignar;
                            asigandos[asignar] = letras[i];
                            asignoo = true;
                        } else {
                            asignar = aleatorio.nextInt(9) + 1;
                        }
                    }
                }
                if (condiciones[letra] == -2) {
                    int asignar = aleatorio.nextInt(10);
                    boolean asignoo = false;
                    while (!asignoo) {
                        if (asigandos[asignar] == '*') {
                            posible[letra] = asignar;
                            asigandos[asignar] = letras[i];
                            asignoo = true;
                        } else {
                            asignar = aleatorio.nextInt(10);
                        }
                    }
                }
            }
            // verificar el resultado de las sumas
            int suma1 = 0;
            int suma2 = 0;
            
            for (int i = 0; i < a.length; i++) {
                char[] c = a[i].toCharArray();
                if (i % 2 == 0) {
                    int sumaParcial = 0;
                    for (int j = 0; j < c.length; j++) {
                        sumaParcial += posible[c[j] - 65] * exponentes[c.length - j];
                    }
                    // es la primera palabra
                    if (i == 0) {
                        suma1 = sumaParcial;
                    } else {
                        String operador = a[i - 1];
                        if (operador.equals("+")) {
                            suma1 = suma1 + sumaParcial;
                        } else if (operador.equals("-")) {
                            suma1 = suma1 - sumaParcial;
                        }
                    }
                }
            }
            for (int i = 0; i < s.length; i++) {
                char[] c = s[i].toCharArray();
                if (i % 2 == 0) {
                    int sumaParcial = 0;
                    for (int j = 0; j < c.length; j++) {
                        sumaParcial += posible[c[j] - 65] * exponentes[c.length - j];
                        
                    }
                    // es la primera palabra
                    if (i == 0) {
                        suma2 = sumaParcial;
                    } else {
                        String operador = s[i - 1];
                        if (operador.equals("+")) {
                            suma2 = suma2 + sumaParcial;
                        } else if (operador.equals("-")) {
                            suma2 = suma2 - sumaParcial;
                        }
                    }
                }
            }
            
            // encuentra solucion
            if (suma1 == suma2) {
                String resp = new String(asigandos);
                System.out.println(resp);
                encontro = true;
                
            }
            // no encuentra solucion
            if (intentos < 0) {
                String asigandos2 = "**********";
                System.out.println(asigandos2);
                encontro = true;
                
            }
            intentos--;
        }
    }
    
    public static double factorial(double numero) {
        if (numero == 0)
            return 1;
        else
            return numero * factorial(numero - 1);
    }
    
}
