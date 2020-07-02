import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Engineus {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String x = input.nextLine();
		ArrayList<String> xxx = new ArrayList<String>();
		xxx = parser(x);
		/*for(int i = 0; i < xxx.size(); i++) {
			System.out.println(xxx.get(i));
		}
		*/
		//System.out.print(xxx);
		
		String html = "<html><head><title>First parse</title></head>"
				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		//System.out.print(doc);
		Document doc2 = null;
		try {
			doc2 = Jsoup.connect("http://www.eprodoffice.com/Shhh/a_aa.htm").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(doc2);
		ArrayList<String> xxxx= checker(xxx,x);
		for(int i=0;i<xxxx.size();i++) {
			System.out.println(xxxx.get(i));
		}
	}
	public static ArrayList<String> parser(String keyword){
		Document doc2 = null;
		ArrayList<String> possibilities = new ArrayList<String>();
		String f1 = keyword.substring(0,1);
		String f2 = keyword.substring(0,2);
		try {
			doc2 = Jsoup.connect("http://www.eprodoffice.com/Shhh/" +f1 + "_" + f2 + ".htm").get();
		} catch (Exception e) {
			try {
				doc2 = Jsoup.connect("http://www.eprodoffice.com/Shhh/" + f2 + ".htm").get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		String docString = doc2.toString();
		String[] hrefArray = docString.split("<a href=\"");
		for(int i = 1; i < hrefArray.length; i++) {
			for(int j = 0; j < hrefArray[i].length() - 1; j++) {
				if(hrefArray[i].substring(j, j+1) .contentEquals( "\"")) {
					possibilities.add(hrefArray[i].substring(0, j));
					break;
				}
			}
		}
		return possibilities;
	}
	public static ArrayList<String> checker(ArrayList<String> x, String keyword){
		ArrayList<String> returner= new ArrayList<String>();
		Document doc3=null;
		for(int i=0;i<x.size();i++) {
			if(x.get(i).contains("http") || x.get(i).contains("https")) {
				try {
					doc3= Jsoup.connect(x.get(i)).get();
					
				}
				catch (Exception e){
			
					
				}
				try {
					if(doc3.text().contains(keyword)) {
						returner.add(x.get(i));
						
					}
					
				}
				catch(Exception h) {
					
				}
				if(returner.size()>=1) {
					break;
				}
				
				
				
				
			}
		}
		return returner;
		
	}
}
