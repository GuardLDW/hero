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
    //通过HttpServletRequest对象可以获取数据，然后通过HttpServletResponse对象响应客户端，比如返回数据等。
    //处理get请求,不接受客户端接受的数据，返回给客户端文件中的数据
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//将文件中的json数据以字符串的形式返回给客户端,通过对response进行操作
		DataOutputStream out=new DataOutputStream(response.getOutputStream());
		out.writeBytes(getFoldInfo());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//处理post请求,接受客户端发来的User的三项数据，将数据保存到文件中，不返回客户端数据
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String comment = request.getParameter("comment");
		
		checkInfo(username, password, comment);
	}
	
	
	
	
	
	public void checkInfo(String username, String password, String comment) throws IOException{
		File file = new File("/data/data/com.hero.app/file/userInfo");
		//向文件中写入数据，以json格式写入
		FileOutputStream out = new FileOutputStream(file);
		byte byt[] = ("{\"username\":" + username + "," + "\"password\":" + password + "," +
		"\"comment\"：" + comment + "}").getBytes();
		out.write(byt);
	}
	
	
	
	//从文件中读取数据，以字符串的形式返回
	public String getFoldInfo() throws IOException{
		File file = new File("/data/data/com.hero.app/file/userInfo");
		FileInputStream in = new FileInputStream(file);
			byte byt[] = new byte[1024];
			int len = in.read(byt);//从文件中读取信息
		return new String(byt, 0, len);
		
	}

}