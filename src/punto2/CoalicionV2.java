package punto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class CoalicionV2 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		String line= bf.readLine();
		while(line!=null){
			int n= Integer.parseInt(line);
			int[] p= new int[n];
			StringTokenizer st= new StringTokenizer(bf.readLine(), " ");
			for (int i = 0; i < n; i++) {
				p[i]=Integer.parseInt(st.nextToken());
			}
			double maxP= calcularMaximaNomina(p, 0, p[n - 1], n - 1);
			System.out.println(String.format(Locale.ROOT,"%.2f", maxP));
			line= bf.readLine();
		}
	}

	// El metodo va realizando las combinaciones de acciones
	// posibles de la suma de las acciones del socio n con las de los socios
	// 0..indiceSumado
	public static double calcularMaximaNomina(int[] p, double maxNomina, double sumaAcciones, int indiceSumado) {

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
			// Conparandola con la maxNomina se returna la mayor

			double nomina = (p[p.length - 1] * 100) / sumaAcciones;
			if (nomina > maxNomina) {
				return nomina;
			} else {
				return maxNomina;
			}
		}
	}
}
