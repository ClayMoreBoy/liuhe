import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test04 {
	public static void main(String[] args) throws Exception {
		String temp="107期：【灰心丧气】⌒⌒⌒(家野中特)⌒⌒(家野中特)⌒⌒⌒107期：【雁字回时】火爆全网=＜＜②肖╋①肖＞＞━◆━＜＜②肖╋①肖＞＞=实力见证..财富不再是梦想！";
		StringBuilder sb = new StringBuilder();
		char[] charArray = temp.toCharArray();
		int i = 0;
		for (char c : charArray) {
			if(Character.isDigit(c)){
				if(i ==3){
					break;
				}
				i++;
				sb.append(c);
			}
		}
		System.out.println(sb.toString());
	}
}
