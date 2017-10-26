import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test01 {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://www.110558.com/shang.htm");
		Document document = Jsoup.parse(url, 300000);
		Element select = document.getElementById("table5805");
		Elements attribute = select.select("a");
		//System.out.println(attribute);
		for (Element element : attribute) {
			String text = element.text();
		    
			System.out.println(element.attr("href")+">>>"+text);
		}
	}
}
