package com.kevin.e_mall.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath =  Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	//生成水印
	public static String generateThumbnail(InputStream thumbnailInputStream, String targetAddr, String fileName) {
		//解析绝对路径
		String realFileName = getRandomFileName();
		String extension = getFileExtension(fileName);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("curr Addr is: " + relativeAddr);
		//destination file
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		makeDirPath(targetAddr);
		logger.debug("full Addr is: " + PathUtil.getImgBasePath() + relativeAddr);
		//往目标路径生成img+水印
		try {
			Thumbnails.of(thumbnailInputStream).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
			.outputQuality(0.8f).toFile(dest);;
		}catch(IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}
	
	//设置随机文件名10000~99999
	public static String getRandomFileName() {
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}
	
	//获得文件后缀
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	//运用file.mkdir产生路径
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	
	//水印测试用例
	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("/Users/kevin/Desktop/sky.jpg")).size(200,200)
		.watermark(Positions.BOTTOM_RIGHT,
				ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f)
		.toFile("/Users/kevin/Desktop/sky-new.jpg");
	}
	
	public static void deleteFileOrPath(String storePath) {
		File targetPath = new File(PathUtil.getImgBasePath()+storePath);
		if(targetPath.exists()) {
			if(targetPath.isDirectory()) {
				File files[] = targetPath.listFiles();
				for(int i = 0; i < files.length; i++) {
					files[i].delete();
				}	
			}
			targetPath.delete();
		}
	}
}
