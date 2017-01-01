package com.djh.test.http;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.djh.test.base.Db;

public class JsoupTest {
	
	
	public static void name(String url,String name) throws IOException, SQLException {
		//String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/6005371.phtml?year=1015&jidu=2";
		//System.out.println(HttpClientUtil.get("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600571.phtml?year=2015&jidu=1", "gbk"));
		Document doc = Jsoup.connect(url).timeout(60*1000).get();
		String title = doc.title();
		System.out.println(title);
		Elements elements = doc.select("#FundHoldSharesTable");
		System.out.println(elements.text());
		Element ele = elements.get(0);
		Elements children = ele.child(1).children();
		for(int i=1;i<children.size();i++){
			Element dateEle = children.get(i).child(0).child(0);
			String date = "";
			if(dateEle.text().length()<15){
				date = dateEle.text();
			}else {
				date = dateEle.child(0).text();
			}
			//String date = children.get(i).child(0).child(0).child(0).text();
			System.out.println();
			String start = children.get(i).child(1).child(0).text();
			String high = children.get(i).child(2).child(0).text();
			String end = children.get(i).child(3).child(0).text();
			String low = children.get(i).child(4).child(0).text();
			String num = children.get(i).child(5).child(0).text();
			String amt = children.get(i).child(6).child(0).text();
			/**
			 * INSERT INTO ()
VALUES('')
			 * 
			 */
			Db.execute("insert into stock(name,tran_date,start_p,highest_p,lowest_p,end_p,num_p,num) values('"+name+"','"+date+"',"+start+","+high+","+low+","+end+","+amt+","+num+")");
		}
		System.out.println();
		//elements.get(1);
	}
	public static void ext() throws IOException, SQLException, InterruptedException {
		List<Map<String, Object>> list = Db.getList("select * from sto_base where code not IN (select DISTINCT name from stock where name is not null)");
		for(Map<String, Object> map:list){
			String name = (String) map.get("code");
			try {
				while (true) {
					for(int year=2016;;year--){
						for(int j=4;j>=1;j--){
							String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+name+".phtml?year="+year+"&jidu="+j;
							//System.out.println(url);
							name(url,name);
							System.out.println(year);
							Thread.sleep(20);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(map);
		}
	}
	
	
	public static void ext1() throws IOException, SQLException, InterruptedException {
		
		String name = "000953";
		while (true) {
			for(int year=2016;;year--){
				for(int j=4;j>=1;j--){
					String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+name+".phtml?year="+year+"&jidu="+j;
					System.out.println(url);
					name(url,name);
					System.out.println(year);
					Thread.sleep(1000);
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		ext1();
	}

}
