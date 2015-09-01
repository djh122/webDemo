package com.djh.listener;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.LoggerFactory;

public class LogPathConfigListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getRealPath("/");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}

		System.setProperty("workdir", path);

		LoggerFactory.getLogger(LogPathConfigListener.class).debug("初始日志工具");
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}