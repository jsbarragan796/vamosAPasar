package punto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class RotadorV2 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		String line= bf.readLine();
		while(line!=null)
		{
			int n= Integer.parseInt(line); //tamaño del arreglo
			int numr= Integer.parseInt(bf.readLine()); //num rotaciones
			int[] a = IntStream.range(1, n + 1).toArray(); //Se genera el arreglo
			for (int i = 0; i < numr; i++) { //leo lineas
				String act= bf.readLine();
				StringTokenizer st= new StringTokenizer(act, " ");
				int p= Integer.parseInt(st.nextToken());
				int q= Integer.parseInt(st.nextToken());
				int k= Integer.parseInt(st.nextToken());
				a= r(p,q,k,a); //Hago la k-rotación
			}
			System.out.println(Arrays.toString(a));
			line= bf.readLine();
		}
	}
	
	public static int[] r(int p, int q, int k, int[] act){
		int mov= k%(q-p); //Cuanto me voy a mover
		int[] ret= Arrays.copyOf(act, act.length); //Copia temporal del arreglo
		for (int i = p; i < q; i++) {
			int pos= (i+mov)%q; //Posición nueva
			if (pos==0 && mov>0) //Si me muevo hacia la derecha
				pos=p;
			else if (pos==0 && mov<0) //Si me muevo hacia la izquierda
				pos=q-1;
			ret[pos]=act[i];
		}
		return ret;
	}
}