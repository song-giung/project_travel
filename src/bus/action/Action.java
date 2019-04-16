package bus.action;

import javax.servlet.http.*;
//�듅�젙 鍮꾩쫰�땲�뒪 �슂泥��쑝濡� �닔�뻾�븯怨� 寃곌낵 媛믪쓣 ActionForward ���엯�쑝濡� 蹂��솚�븯�뒗 硫붿꽌�뱶媛� �젙�쓽�릺�뼱 �엳�뒿�땲�떎.
//Action : �씤�꽣�럹�씠�뒪紐�  
//ActionForward : 諛섑솚�삎
public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
