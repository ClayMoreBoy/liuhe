import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test06 {
	public static void main(String[] args) throws Exception {
		StringBuffer sb = new StringBuffer("");
	    URL url;
	    try {
	        url = new URL("http://www.138246.com/bbs/gl01.htm");
            URLConnection conn = url.openConnection();  
            conn.setReadTimeout(30000000);  
            conn.setConnectTimeout(30000000);  
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), 
	        		"gbk"));
	        String s = "";
	        while ((s = br.readLine()) != null) {
	            sb.append(s + "\r\n");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
		Document document = Jsoup.parse(sb.toString());
		//Elements select = document.select("table");
		Element elementById = document.getElementById("table5");
		//Element elementById1 = document.getElementById("table6458");
		
		//Elements element = select.get(1).select("td").get(3).select("span");
	//	Element element = select.get(1).select("td").get(3);
		//System.out.println(elementById.toString());
		System.out.println(elementById.toString().replaceAll("张天师", "勇往六合"));
	//	for (Element element2 : element) {
			//System.out.println(element2.text());
		//}
	}
}
