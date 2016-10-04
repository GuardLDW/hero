package servelt;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CustomerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //ͨ��HttpServletRequest������Ի�ȡ���ݣ�Ȼ��ͨ��HttpServletResponse������Ӧ�ͻ��ˣ����緵�����ݵȡ�
    //����get����,�����ܿͻ��˽��ܵ����ݣ����ظ��ͻ����ļ��е�����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//���ļ��е�json�������ַ�������ʽ���ظ��ͻ���,ͨ����response���в���
		DataOutputStream out=new DataOutputStream(response.getOutputStream());
		out.writeBytes(getFoldInfo());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//����post����,���ܿͻ��˷�����User���������ݣ������ݱ��浽�ļ��У������ؿͻ�������
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String comment = request.getParameter("comment");
		
		checkInfo(username, password, comment);
	}
	
	
	
	
	
	public void checkInfo(String username, String password, String comment) throws IOException{
		File file = new File("/data/data/com.hero.app/file/userInfo");
		//���ļ���д�����ݣ���json��ʽд��
		FileOutputStream out = new FileOutputStream(file);
		byte byt[] = ("{\"username\":" + username + "," + "\"password\":" + password + "," +
		"\"comment\"��" + comment + "}").getBytes();
		out.write(byt);
	}
	
	
	
	//���ļ��ж�ȡ���ݣ����ַ�������ʽ����
	public String getFoldInfo() throws IOException{
		File file = new File("/data/data/com.hero.app/file/userInfo");
		FileInputStream in = new FileInputStream(file);
			byte byt[] = new byte[1024];
			int len = in.read(byt);//���ļ��ж�ȡ��Ϣ
		return new String(byt, 0, len);
		
	}

}