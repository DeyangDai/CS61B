import java.io.*;

class Nuke2{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));
		String line = br.readLine();
		String subLine1 = line.substring(0,1);
		String subLine2 = line.substring(2,line.length());
		String result = subLine1 + subLine2;
		System.out.println(result);
	}
}