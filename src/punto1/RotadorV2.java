package punto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RotadorV2 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		String line= bf.readLine();
		while(line!=null)
		{
			int tam= Integer.parseInt(line);
			int numr= Integer.parseInt(bf.readLine());
			for (int i = 0; i < numr; i++) {
				String act= bf.readLine();
				StringTokenizer st= new StringTokenizer(act, " ");
				int r= Integer.parseInt(st.nextToken());
				int p= Integer.parseInt(st.nextToken());
				int k= Integer.parseInt(st.nextToken());
			}
			
			line= bf.readLine();
		}
	}
}
