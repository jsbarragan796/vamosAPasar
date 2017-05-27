package punto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Coaliciones {

	public static void main(String[] args) throws Exception {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


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
					total+=p[j];
				}
				if(total!=100){
					throw new Exception("La suma de los porcentajes de las acciones no es representa el 100% ");
				}
			

			} catch (NumberFormatException e) {
				throw new Exception("El p_" + (i + 1) + " no es un entero para el socio " + i);
			}
			
			
			System.out.println(n);
			System.out.println(Arrays.toString(p));

		}
	}
}
