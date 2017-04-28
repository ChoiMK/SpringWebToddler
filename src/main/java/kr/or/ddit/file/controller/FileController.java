package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file/")
public class FileController {
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@RequestMapping("idPicFileupload")
	public void idUpload(){}
	
	@RequestMapping("idPictureUpload")
	@ResponseBody
	public String idPictureUpload(HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = 
				              (MultipartHttpServletRequest)request;
		
		Iterator<String> fileNames = multipartRequest.getFileNames();
		String fileName = "";
		while(fileNames.hasNext()){
			MultipartFile file = multipartRequest.getFile(fileNames.next());
			if(file.getSize() > 0){
				logger.debug("field name : {}", file.getName());
				logger.debug("file name : {}", file.getOriginalFilename());
				logger.debug("file size : {}", file.getSize());
				logger.debug("file content type : {}", file.getContentType());
				
				fileName = file.getOriginalFilename();
				
				File saveFile = new File("D:\\temp\\upload", fileName);
				
				try {
					file.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileName;
	}
	
	@RequestMapping("fileDownload/{fileName}")
	public ModelAndView fileDownload(@PathVariable String fileName){
		File downloadFile = new File("D:\\temp\\upload", fileName);
		
		// 1. ModelAndView.setViewName("downloadView");
		// 2. ModelAndView.addObject("downloadFile", downloadFile);
		return new ModelAndView("downloadView", "downloadFile", downloadFile);
	}
}











