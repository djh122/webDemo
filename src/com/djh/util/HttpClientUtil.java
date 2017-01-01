package com.djh.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpClientUtil {
	
	private static CloseableHttpClient hc = null;
	
	private static int TIMEOUT = 120 * 1000;
	
	static {
		
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager( HttpSSLSupport.getRegistry() );
		cm.setDefaultMaxPerRoute( 10 );
		cm.setMaxTotal( 200 );
		
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout( TIMEOUT )
				.setConnectTimeout( TIMEOUT ).setSocketTimeout( TIMEOUT ).build();
		
		hc = HttpClients.custom().setConnectionManager( cm )
				.setDefaultRequestConfig( defaultRequestConfig ).build();
	}
	
	public static String post( String url, String data, String encode ) throws IOException {

		String ret = "";
		HttpPost post = new HttpPost( url );
		
		try {
			
			StringEntity se = new StringEntity( data, encode );
			se.setContentType( "application/x-www-form-urlencoded" );
			post.setEntity( se ); 
			CloseableHttpResponse res = hc.execute( post );
			HttpEntity he = res.getEntity();
			
			try {
				
				ret = EntityUtils.toString( he, encode );
				EntityUtils.consume( he );
				
			} catch ( IOException e ) {
				throw e;
			} finally {
				res.close();
			}

		} catch ( IOException e ) {
			throw e;
		} finally {
			post.abort();
		}
		return ret;
	}
	
	public static String get( String url, String encode ) throws IOException {
		String ret = "";
		HttpGet get = new HttpGet( url );
		
		try {
			
			StringEntity se = new StringEntity(encode );
			se.setContentType( "application/x-www-form-urlencoded" );
			//get.setEntity( se ); 
			//get.
			CloseableHttpResponse res = hc.execute(get);
			HttpEntity he = res.getEntity();
			try {
				ret = EntityUtils.toString( he, encode );
				EntityUtils.consume( he );
			} catch ( IOException e ) {
				throw e;
			} finally {
				res.close();
			}

		} catch ( IOException e ) {
			throw e;
		} finally {
			get.abort();
		}
		return ret;
	}

	
	public static void main(String[] args) throws IOException {
		//System.out.println(HttpClientUtil.get("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600571.phtml?year=2015&jidu=1", "gbk"));
		Document doc = Jsoup.connect("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600571.phtml?year=2015&jidu=1").get();
		String title = doc.title();
		System.out.println(title);
		System.out.println(doc.select("#FundHoldSharesTable"));
	}

}
