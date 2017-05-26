package punto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class rotacion {

	public static void main(String[] args) throws Exception {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		int n = -1;
		int nr = -1;
		try {
			n = new Integer(stdIn.readLine());
			if (n <= 0 || n > 100000) {
				throw new Exception("El entero n no cumple con el rango 0<n<100000");
			}
		} catch (NumberFormatException e) {
			throw new Exception("No se recibió un entero para n");
		} catch (IOException e) {
			throw new Exception("No se recibió correctamente un entero n");
		}
		try {
			nr = new Integer(stdIn.readLine());
			if (nr < 0 | nr > 100000) {
				throw new Exception("El entero nr no cumple con el rango 0<=n<100000");
			}
		} catch (NumberFormatException e) {
			throw new Exception("No se recibió un entero para nr");
		} catch (IOException e) {
			throw new Exception("No se recibió correctamente un entero nr");
		}

		int i = 0;
		int[][] rs = new int[nr][3];
		while (i < nr) {

			String r_i_string = stdIn.readLine();
			String[] r_i_split = r_i_string.split(" ");
			if (r_i_split.length != 3) {
				throw new Exception("El r_" + (i + 1) + " no porporciona los 3 valores para p , q  y k");
			}
			int[] r_i_int;
			try {
				r_i_int = new int[] { new Integer(r_i_split[0]), new Integer(r_i_split[1]), new Integer(r_i_split[2]) };
				if (r_i_int[0]<0||r_i_int[0] >= r_i_int[1]||r_i_int[1]>n||r_i_int[1]>100000) {
					throw new Exception("El r_" + (i + 1) + " no cumple con la condicion  0<= p < q<=n<=100000 ");
				}
				if (r_i_int[2]<-100000||r_i_int[2]>100000) {
					throw new Exception("El r_" + (i + 1) + " no cumple con la condicion  -100000<k<100000 ");
				}
			} catch (NumberFormatException e) {
				throw new Exception(
						"El r_" + (i + 1) + " no tiene un entero para alguno o algunos de los valores p , q  y k");
			}
			rs[i] = r_i_int;
			i++;
		}
		if (i != nr) {
			throw new Exception("No se recibieron todos los valores de  r (p , q , k)" + i + " s " + nr);
		}
		
		

		System.out.println("n: " + n);
		System.out.println("rn: " + nr);
		System.out.println(Arrays.deepToString(rs));
	}
}
