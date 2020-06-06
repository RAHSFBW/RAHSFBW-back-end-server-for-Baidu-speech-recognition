//package servlet;
//
//import net.sf.json.JSONObject;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//
//@WebServlet("/ChangePW")
//public class ChangeServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public ChangeServlet(){
//        super();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("已经进入get");
//        doPost(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        BufferedReader read = request.getReader();
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//        while ((line = read.readLine()) != null) {
//            sb.append(line);
//        }
//        String req = sb.toString();
//        System.out.println(req);
//
//        // 第一步：获取客户端 发来的请求，恢复其Json格式——>需要客户端发请求时也封装成Json格式
//        JSONObject object = JSONObject.fromObject(req);
//        // requestCode暂时用不上
//        // 注意下边用到的2个字段名称requestCode、requestParam要和客户端CommonRequest封装时候的名字一致
//        String requestCode = object.getString("requestCode");
//        JSONObject requestParam = object.getJSONObject("requestParam");
//
//        String name = requestParam.getString("name");
//        String phone = requestParam.getString("phone");
//        String sql = "update"
//    }
//}
