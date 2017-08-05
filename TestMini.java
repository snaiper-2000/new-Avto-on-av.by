package av.by;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMini {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pattern pat=Pattern.compile("\\d+\\s\\d+");
		Matcher matcher=pat.matcher("15 646 р.");
		while (matcher.find()) {
			System.out.println(matcher.group().replaceAll(" ", ""));
			int ch = Integer.parseInt(matcher.group().replaceAll(" ", ""));
		    System.out.println(ch);
		};
      
		/*String str = "140000 миль";
		String[] array = str.split(" ");
		
		System.out.println(array[0]);
		*/
		
		/*int ch = Integer.parseInt("15646");
	    System.out.println(ch);
	    */
	}
	

}
