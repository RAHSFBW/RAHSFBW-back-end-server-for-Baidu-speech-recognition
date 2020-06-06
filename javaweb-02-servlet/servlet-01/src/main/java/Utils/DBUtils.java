package Utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    //table
    public static final String TABLE_PASSWORD = "table_user_password";
    public static final String TABLE_USERINFO = "table_user_info";

    //获取连接
    public static Connection getConnection() throws Exception {
//        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //为了向下兼容不建议省略  JDBC4.0自动加载驱动器类： 从JDK1.6开始，Oracle就将修改了添加了新的加载JDBC驱动的方式。
        // 即JDBC4.0。在启动项目或是服务时，会判断当前classspath中的所的jar包，并检查META-INF目录下，是否包含services文件夹，如果包含，
        // 就会将里面的配置加载成相应的服务。
        Connection connection = null;
        try {
            Class.forName(driverClass);

            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            //throwables.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
        return connection;
    }

    public static void closeResources(Connection connection, Statement preparedStatement){
        try {
            if(preparedStatement != null)
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        try {
            if(connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void closeResources(Connection connection, Statement preparedStatement, ResultSet resultSet){
        try {
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if(connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if(resultSet != null)
                resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
