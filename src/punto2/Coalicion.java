package punto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Coalicion {

	public Coalicion() {

	}

	private double calcularMaximaNomina(int[] p, double maxNomina, int sumaAcciones, int indiceSumado) {
		System.out.println("max "+maxNomina+" suma acciones "+sumaAcciones +" indice sumado "+indiceSumado);
		System.out.println(" ");
		double maxTemp=maxNomina;
		
		if (sumaAcciones <= 50) {
			int indice = indiceSumado - 1;
			while (indice >= 0) {
				System.out.println("iteracion "+p[indice] +" con el indice sumado "+p[indiceSumado]);
				double respTemp=calcularMaximaNomina(p, maxNomina, sumaAcciones + p[indice], indice);
				if(maxTemp<respTemp){
					maxTemp=respTemp;
				}
				indice--;
			}
			if(indiceSumado-1>=0){
			return calcularMaximaNomina(p, maxTemp, p[p.length - 1] + p[indiceSumado - 1], indiceSumado - 1);
			}else{
				return maxTemp;
			}
		} else{

			double nomina = (p[p.length - 1] * 100) / sumaAcciones;
			if (nomina > maxNomina) {
				return nomina;
			}
			else{
				return maxNomina;
			}			
			
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		Coalicion coliacio;

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
			coliacio= new Coalicion();
	
			double resp= coliacio.calcularMaximaNomina(p, 0, p[p.length-1], p.length-1);
			System.out.println("nomina maxima "+resp);
			

		}
	}
}
