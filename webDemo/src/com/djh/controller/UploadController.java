package com.djh.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djh.util.Uploader;

@Controller
public class UploadController {
	@RequestMapping(value="imageUpload.do")
	public String imageUpload(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	    Uploader up = new Uploader(request);
	    up.setSavePath("upload");
	    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	    up.setAllowFiles(fileType);
	    up.setMaxSize(10000); //单位KB
	    try {
			up.upload();
		    String callback = request.getParameter("callback");
		    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";
		    result = result.replaceAll( "\\\\", "\\\\" );
		    if( callback == null ){
				response.getWriter().print( result );
		    }else{
		        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("图片上传失败");
		}
		return null;
	}
	
	
	Logger logger = Logger.getLogger(UploadController.class);
}
