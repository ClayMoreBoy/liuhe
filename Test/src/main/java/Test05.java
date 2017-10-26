import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test05 {
	public static void main(String[] args) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
	    URL url;
	    try {
	        url = new URL("http://www.889002.com/chajian/bmjg.js");
	        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), 
	        		"utf-8"));
	        String s = "";
	        while ((s = br.readLine()) != null) {
	        	list.add(s);
	        	
	        }
	        String ss = list.get(0).toString();
	        System.out.println(ss.substring(7,ss.indexOf("t")-3));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
}
