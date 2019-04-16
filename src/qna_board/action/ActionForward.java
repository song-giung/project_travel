package qna_board.action;



public class ActionForward {
	private boolean redirect = false;
	private String path = null;

	
	public boolean isRedirect() {
	
		return redirect;
	}


	public void setRedirect(boolean b) {
		redirect = b;
	}

	
	public String getPath() {
		return path;
	}


	public void setPath(String string) {
		path = string;
	}
}
