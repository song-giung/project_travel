package comm_board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		   request.setCharacterEncoding("utf-8");

			response.setContentType("text/html;charset=utf-8");
		   String fileName = request.getParameter("filename");
		   System.out.println("fileName =" + fileName);
		   String savePath = "boardupload";
		   
		   
		   ServletContext context = request.getServletContext();   
		   String sDownloadPath = context.getRealPath(savePath);
		   
		   String sFilePath = sDownloadPath+"/"+fileName;
		   System.out.println(sFilePath);
		   
		   byte b[] = new byte[4096];
		   
			String sMimeType = context.getMimeType(sFilePath);
		   	System.out.println("sMimeType>>"+ sMimeType);
		   	
			if(sMimeType ==null)
		   		sMimeType = "application/octet-stream";
			
		   	response.setContentType(sMimeType);
		   	
			String sEncoding = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		   	 System.out.println(sEncoding);
		   	 
			 response.setHeader("Content-Disposition", "attachment;filename="+sEncoding);
		   	ServletOutputStream so= response.getOutputStream();
		   	BufferedOutputStream out2=null;
		   	BufferedInputStream in = null;
		   	
		   	try{
		   		
		  		FileInputStream istream = new FileInputStream(sFilePath);
		   		out2=new BufferedOutputStream(so);
		   		in=new BufferedInputStream(istream);
		   		int a=0;

		   		while((a = in.read(b,0,b.length)) != -1) {

		   		   out2.write(b,0,a);
		   		  }

		   	}catch(Exception e){
		   		e.printStackTrace();
		   		
		   	}finally{
		   		if(out2!=null){
		   		out2.flush();
		   		out2.close();
		   		}
		   		if(in!=null)
		   		in.close();
		   	
		   	}
		   	
		   
		return null;
	}

}
