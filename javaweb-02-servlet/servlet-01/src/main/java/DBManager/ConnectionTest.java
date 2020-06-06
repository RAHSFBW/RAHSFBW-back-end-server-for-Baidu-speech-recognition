package DBManager;

import Utils.DBUtils;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    @Test
    public void testConnection5() throws IOException, ClassNotFoundException, SQLException {

        //1.读取配置文件的4个配置信息,通过类加载器,一个系统加载器
        InputStream inputStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();

        properties.load(inputStream);


        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    @Test
    public void testConnection(){
        Connection connection = null;
        try {
                connection = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(connection);
    }
}
