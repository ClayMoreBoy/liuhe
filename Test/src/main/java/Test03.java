import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test03 {
	public static void main(String[] args) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
	    URL url;
	    try {
	        url = new URL("http://m.clink.cc/web/bama.asp");
	        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), 
	        		"utf-8"));
	        String s = "";
	        while ((s = br.readLine()) != null) {
	        	if(s.contains("<br/>")){
	        		list.add(s);
	        	}
	        }
	        List<String> subList = list.subList(2, 6);
	        for (String string : subList) {
				System.out.println(string.replaceAll("<br/>", ""));
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
}
