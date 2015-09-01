package com.djh.util;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;

/**
 * Https支持工具
 * 
 * @author dy
 *
 */
public class HttpSSLSupport {
	
	private static Registry<ConnectionSocketFactory> registry = null;
	
	public static Registry<ConnectionSocketFactory> getRegistry() {
		if ( registry == null ) {
			registry = new HttpSSLSupport().enableSSL();
		}
		return registry;
	}
	
	/**
	 * 支持SSL
	 * 
	 * @return
	 */
	private Registry<ConnectionSocketFactory> enableSSL() {
		
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register( "http", plainSF );
		
		try {
			KeyStore trustStore = KeyStore.getInstance( KeyStore.getDefaultType() );
			SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial( trustStore, new AnyTrustStrategy() ).build();
			LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory( sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER );
			registryBuilder.register( "https", sslsf );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return registryBuilder.build();
	}
	
	/**
	 * 重写验证方法
	 * 
	 * @author dy
	 *
	 */
	class AnyTrustStrategy implements TrustStrategy {
		public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			return true;
		}
	}

}
