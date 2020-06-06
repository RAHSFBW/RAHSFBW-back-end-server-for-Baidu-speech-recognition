package Dao;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class BaseDao {
    public int update(Connection connection, String sql, Object ... args) {

        PreparedStatement preparedStatement = null;
        try {
//            //1.获取数据可连接
//            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            preparedStatement = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0;i<args.length;++i) {//可变形参看做数组
                preparedStatement.setObject(i+1, args[i]);//!!!!注意
            }
            //4.执行操作
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //修改为自动提交，针对于使用数据库连接池的情况（使用的连接会被归还到连接池，所以要恢复）
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            //5.关闭资源
            DatabaseUtil.closeResources(null,preparedStatement);
        }
        return 0;
    }
}
