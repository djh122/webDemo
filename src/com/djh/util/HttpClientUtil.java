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
		HttpClientUtil.get("192.168.18.141:8080/getDemo/test.do", "utf-8");
	}

}
