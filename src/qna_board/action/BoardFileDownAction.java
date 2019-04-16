package qna_board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;


import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFileDownAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		String fileName = request.getParameter("filename");
		System.out.println("fileName=" +fileName);
		
		String savePath="boardupload";
		
		
		ServletContext sc = request.getServletContext();
		String sDownloadPath = sc.getRealPath(savePath);
		String sFilePath = sDownloadPath + "\\" + fileName;
		System.out.println("sFilePath=" +sFilePath);
		
		byte b[] = new byte[4096];
		String sMimeType = sc.getMimeType(sFilePath);
		
		if(sMimeType == null)
			sMimeType = "application/octet-stream";
		response.setContentType(sMimeType);
		
		String sEncoding = 
				new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);
	    
		ServletOutputStream so = response.getOutputStream();
		BufferedOutputStream out2 = null;
		BufferedInputStream in = null;
		
	    try {
	    	
	    	out2 = new BufferedOutputStream(so);
	    	in = new BufferedInputStream(new FileInputStream(sFilePath));
	    	int numRead;
	    	
	    	while((numRead = in.read(b,0,b.length)) != -1) {
	    		out2.write(b, 0, numRead);
	    	}
	    	
	    } catch(Exception e) {
	    	e.printStackTrace();
	    } finally {
			try {
				if(out2 != null){
					out2.flush();
					out2.close();
				}
				if (in != null){
					in.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	    
		forward.setRedirect(false);
		return forward;
	}

}
