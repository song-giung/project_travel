package comm_board.action;


public class ActionForward {
	private boolean redirect=false;
	private String path=null;
	
	//property redirect�� is�޼ҵ�
	public boolean isRedirect() {
		//������Ƽ Ÿ���� boolean�� ��� get ��� is�� �տ� ���� �� �ִ�.
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect=redirect;
	}
	
	//property path �� get �޼ҵ�
	public String getPath() {
		return path;
	}
	
	//property path�� set �޼ҵ�
	public void setPath(String path) {
		this.path=path;
	}
}
