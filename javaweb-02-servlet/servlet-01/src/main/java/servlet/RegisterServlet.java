package servlet;

import beans.CommonResponse;
import net.sf.json.JSONObject;
import util.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public  RegisterServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("已经进入get");
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader read = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = read.readLine()) != null) {
            sb.append(line);
        }

        String req = sb.toString();
        System.out.println(req);

        // 第一步：获取客户端 发来的请求，恢复其Json格式——>需要客户端发请求时也封装成Json格式
        JSONObject object = JSONObject.fromObject(req);
        // requestCode暂时用不上
        // 注意下边用到的2个字段名称requestCode、requestParam要和客户端CommonRequest封装时候的名字一致
        String requestCode = object.getString("requestCode");
        JSONObject requestParam = object.getJSONObject("requestParam");

        String name = requestParam.getString("name");
        String password = requestParam.getString("password");
        String sql = "insert into tb_account(account, password) values(?,?);";

        CommonResponse response = new CommonResponse();

        int update = DatabaseUtil.update(sql, name, password);
        if(update == 1)
            response.setResult("0","insert success");
        else
            response.setResult("-1","insert failed");

        // 第三步：将结果封装成Json格式准备返回给客户端，但实际网络传输时还是传输json的字符串
        // 和我们之前的String例子一样，只是Json提供了特定的字符串拼接格式
        // 因为服务端JSon是用到经典的第三方JSon包，功能强大，不用像Android中那样自己手动转，直接可以从Bean转到JSon格式
        String resStr = JSONObject.fromObject(response).toString();

        System.out.println(resStr);
//		response.getWriter().append(EncryptUtil.getEDSEncryptStr(resStr)); // 可以对字符串进行加密操作，相应的到了客户端就需要解密
        resp.getWriter().append(resStr).flush();

    }

}
