
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

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
			String[] a= new String[st.countTokens()+1];
			a[0]="-";
			count=1;
			while(st.hasMoreTokens()){
				String next= st.nextToken().trim();
//				System.out.println(next);
				if (next.equals("+"))
					a[count]="-";
				else if (next.equals("-"))
					a[count]="+";
				else a[count]=next;
				count++;
			}
			String[] v= Arrays.copyOf(s, s.length+a.length);
			System.arraycopy(a, 0, v, s.length, a.length);
			String sol= alf(v);
//			System.out.println(sol);
//			System.out.println(Arrays.toString(s));
//			System.out.println(Arrays.toString(a));
//			System.out.println(Arrays.toString(v));
			line= bf.readLine();
		}
	}

	public static String alf(String[] v){
		int numPal= (v.length/2)+1;
		HashMap<String, Integer> dic= new HashMap<String, Integer>();
		String[] first= new String[numPal];
		int j=0, value=1;
		String f= "";
		for (int i = 0; i < first.length; i++) {
			String act= v[j].charAt(0) + "";
			first[i]= act;
			if (!f.contains(act)){
				f= f + v[j].charAt(0);
				dic.put(act, value);
				value++;
			}
			j+=2;
		}
		String t=f;
		int value1=0;
		for (int i = 0; i < v.length; i+=2) {
			String palact= v[i];
			for (int k = 1; k < palact.length() && t.length()!=10; k++) {
				String letter= palact.charAt(k) + "";
				if(!t.contains(letter)){
					t= t + letter;
					dic.put(letter, value1);
					value1++;
					if (i==1)
						value1+=value;
					System.out.println("value: " + value + "value1: " + value1);
				}
			}
		}
		int column=1, sum=0, carry=0, ikey=0;
		String op="";
		String[] keys= new String[dic.keySet().size()];
		dic.keySet().toArray(keys);
		for (int i = 0; i < v.length; i+=2) {
			String palact= v[i], cact="";
			if(palact.length()>0)
				cact= palact.charAt(v[i].length()-column) + "";
			if (i==0)
				op= "+";
			else if ((i+1)<v.length)
				op= v[i];
			if(!cact.equals("")){
				int val= dic.get(cact);
				if(op.equals("+")){
					sum= (sum + val)%10;
					if ((sum+val)%10==0)
						carry++;
				}
				else{
					sum= (sum - val)%10;
					if ((sum - val)%10==0)
						carry++;
				}
			}
			if(i==v.length-1 && sum!=0){
//				i=0;
//				int va= dic.get(keys[ikey]);
//				if ((va+1)<10)
//					dic.put(keys[ikey], va++);
			}
		}
		System.out.println(dic);
		System.out.println("f: " + f);
		System.out.println("t: " + t);
		return t;
	}
}
