package com.ju.utils;

import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
//上传到阿里云
@Component
public class AliOssUtils {
	@Autowired
	private OSS ossCliect;
	//图片上传
	public String upload(MultipartFile multipartFile){
		String filename = multipartFile.getOriginalFilename();
		String filePath=getfilePath(filename);
		try {
			ossCliect.putObject("shopping",filePath,new ByteArrayInputStream(multipartFile));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.YEAR, 10);
			Url url=ossCliect.generatePresignedUrl("shopping",filePath,calendar);
			return url.toString();
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			
		}
	}

}
