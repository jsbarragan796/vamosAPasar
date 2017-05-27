package punto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Rotador {

	public Rotador() {

	}

	public void rotarMatriz(int[] a, int n, int[][] rs) {
		int[] resp = a;
		for (int i = 0; i < rs.length; i++) {
			resp = rotarA_R(resp, rs[i]);
		}
		System.out.println(Arrays.toString(resp));
	}

	private int[] rotarA_R(int[] a, int[] r) {
		int p = r[0];
		int q = r[1];
		int k = r[2];
		int[] resp = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i >= p && i < q) {
				int j = ((i + k - p) % (q - p)) + p;
				System.out.println("iteracion " + i);
				System.out.println("iteracion " + j +" i "+i+ " k "+k+" p "+p +" q "+q);

				resp[j] = a[i];
			} else {
				resp[i] = a[i];
			}
		}
		// SI(O(Y(p<i, i<q),Y(p=i, i<q)),RESIDUO(i-p+k,q-p)+p,i);
		return resp;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		Rotador rotador;

		// while(true){
		int n;
		int nr;
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
				if (r_i_int[0] < 0 || r_i_int[0] >= r_i_int[1] || r_i_int[1] > n || r_i_int[1] > 100000) {
					throw new Exception("El r_" + (i + 1) + " no cumple con la condicion  0<= p < q<=n<=100000 ");
				}
				if (r_i_int[2] < -100000 || r_i_int[2] > 100000) {
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

		int[] a = IntStream.range(1, n + 1).toArray();

		rotador = new Rotador();
		rotador.rotarMatriz(a, n, rs);

		// System.out.println("n: " + n);
		// System.out.println("rn: " + nr);
		// System.out.println(Arrays.deepToString(rs));
		// }
	}
}
