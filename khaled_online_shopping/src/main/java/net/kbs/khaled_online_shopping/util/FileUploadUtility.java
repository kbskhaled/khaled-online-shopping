package net.kbs.khaled_online_shopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	
	// on peut remplacer \\ par /
	private static final String ABS_PATH = "D:\\projet khaled\\projet j2ee\\projet online shopping\\projet_khaled_online_shopping_workspace\\khaled-online-shopping\\khaled_online_shopping\\src\\main\\webapp\\assets\\images\\";
	// 
	private static String REAL_PATH = null;
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		// TODO Auto-generated method stub
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");

		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		
		try {
			//transfer the file to both the location
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
	}

}
