package punto3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Alfametica {

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
		// -1 no esta en las palabras, -2 es una letra que no esta en el inicio
		// ,-3 es una letra de inicio no puede ser 0
		int[] restricciones = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1 };
		// contienen las letras para encontradas en las palabras
		char[] letras = new char[10];
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

		darCombinacion(restricciones, letras, s, a);
	}

	public static void darCombinacion(int[] condiciones, char[] letras, String[] s, String[] a) {

		long inicio = System.currentTimeMillis();
		int[] exponentes = { 0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };
		Random aleatorio = new Random();
		boolean encontro = false;
		int intentos = 0;
		while (!encontro) {

			int[] posible = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1 };
			char[] asigandos = { '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'};
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
			intentos++;
			if (suma1 == suma2) {
				System.out.println(Arrays.toString(asigandos));
				encontro = true;
				System.out.println("duracion: ms " + (System.currentTimeMillis() - inicio));
			}
			if (intentos==1628800) {
				char[] asigandos2 = { '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'};
				System.out.println(Arrays.toString(asigandos2));
				encontro = true;
				System.out.println("duracion: ms " + (System.currentTimeMillis() - inicio));
			}
			
		}
	}
		
}
