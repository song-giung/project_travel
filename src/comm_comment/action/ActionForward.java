package comm_comment.action;
/*
 ActionForward Ŭ������ Action �������̽����� ����� �����ϰ� ��� ���� ������ ������ �Ҷ� ���Ǵ� Ŭ�����Դϴ�.
 �� Ŭ������ Redirect ���� ���� �������� �������� ��ġ�� ������ �ֽ��ϴ�.
 FrontController���� ActionForwardŬ���� Ÿ������ ��ȯ���� ��������
 �� ���� Ȯ���Ͽ� �ش��ϴ� ��û �������� ������ ó���� �մϴ�. 
 
 */
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
