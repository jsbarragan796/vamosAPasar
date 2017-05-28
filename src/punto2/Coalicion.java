package punto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Coalicion {

	public Coalicion() {

	}

	// El metodo va realizando las combinaciones de acciones
	// posibles de la suma de las acciones del socio n con las de los socios
	// 0..indiceSumado
	private double calcularMaximaNomina(int[] p, double maxNomina, double sumaAcciones, int indiceSumado) {

		double maxTemp = maxNomina;
		// si la suma de acciones es menor a 50 es nesesario seguir sumando.
		if (sumaAcciones <= 50) {
			int indice = indiceSumado - 1;

			while (indice >= 0) {
				double respTemp = calcularMaximaNomina(p, maxNomina, sumaAcciones + p[indice], indice);
				if (maxTemp < respTemp) {
					maxTemp = respTemp;
				}
				indice--;
			}
			if (indiceSumado - 1 >= 0) {
				//se continua comparando la suma del socio n con el socio indiceSumado - 1
				return calcularMaximaNomina(p, maxTemp, p[p.length - 1] + p[indiceSumado - 1], indiceSumado - 1);
			} else {
				// termino de comparar con todas la acciones, aca pasa si la
				// suma del socio 0 con el socio n no es mayor a la mitad
				return maxTemp;
			}

		} else {
			// Cuando ya no se reguiere sumar, se procede a calcular la nomina
			// con la actual combinacion
			// Comparandola con la maxNomina se retorna la mayor

			double nomina = (p[p.length - 1] * 100) / sumaAcciones;
			if (nomina > maxNomina) {
				return nomina;
			} else {
				return maxNomina;
			}

		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		Coalicion coliacion;

		while (true) {
			int n;
			int[] p;
			// se lee el numero de socios
			try {
				n = new Integer(stdIn.readLine());
				if (n <= 0 || n > 100000) {
					throw new Exception("El numero de socios no cumple con el rango 0<n<100000");
				}
			} catch (NumberFormatException e) {
				throw new Exception("No se recibió un entero para el numero de socios");
			} catch (IOException e) {
				throw new Exception("No se recibió correctamente el numero de socios");
			}
			// se leen todos porcentajes de los socios

			int i = 0;
			p = new int[n];

			String p_string = stdIn.readLine();
			String[] p_split = p_string.split(" ");
			if (p_split.length != n) {
				throw new Exception("Los porcentajes de acciones no es acorde con el numero de los socios ");
			}

			try {
				int total = 0;
				for (int j = 0; j < p_split.length; j++) {
					p[j] = new Integer(p_split[j]);
					total += p[j];
				}
				if (total != 100) {
					throw new Exception("La suma de los porcentajes de las acciones no es representa el 100% ");
				}

			} catch (NumberFormatException e) {
				throw new Exception("El p_" + (i + 1) + " no es un entero para el socio " + i);
			}
			//
			coliacion = new Coalicion();
			// inico metodo recursivo
			double resp = coliacion.calcularMaximaNomina(p, 0, p[p.length - 1], p.length - 1);
			

			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
			otherSymbols.setDecimalSeparator('.');
			otherSymbols.setGroupingSeparator(' '); 
			DecimalFormat df = new DecimalFormat("#0.00", otherSymbols);
			System.out.println(df.format(resp));
		}
	}
}
