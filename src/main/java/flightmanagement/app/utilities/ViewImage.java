package flightmanagement.app.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

public class ViewImage {

	public static String displayImage(InputStream is)throws IOException{
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[4096];
		int bytesRead=-1;
		
		while((bytesRead=is.read(buffer))!=-1)
		{
			outputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes=outputStream.toByteArray();
		String base64Image=Base64.getEncoder().encodeToString(imageBytes);
		
		return base64Image;
	}

}
