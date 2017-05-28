package punto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlfameticaV2 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		String line= bf.readLine();
		while(line!=null){
			StringTokenizer st= new StringTokenizer(line, "=");
			String sum= st.nextToken();
			String ans= st.nextToken().trim();
			st= new StringTokenizer(sum, "+-",true);
			String[] s= new String[st.countTokens()];
			int count= 0;
			while(st.hasMoreTokens()){
				s[count]=st.nextToken().trim();
				count++;
			}
			st= new StringTokenizer(ans, "+-",true);
			String[] a= new String[st.countTokens()];
			count=0;
			while(st.hasMoreTokens()){
				a[count]=st.nextToken().trim();
				count++;
			}
			String sol= alf(s,a);
			System.out.println(Arrays.toString(s));
			System.out.println(ans);
			System.out.println(Arrays.toString(a));
			line= bf.readLine();
		}
	}
	
	public static String alf(String[] s,String[] a){
		return "holi";
	}
}
