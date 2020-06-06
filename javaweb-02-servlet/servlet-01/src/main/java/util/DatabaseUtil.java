package util;



import java.sql.*;

public class DatabaseUtil {

	private static Connection mConnection;

	/**
	 * 获取数据库连接
	 * 
	 * @return 唯一数据库连接
	 */
	private static Connection getConnection() {
		if (mConnection == null) {
			String url = "jdbc:mysql://localhost:3306/servlettest1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; // 数据库的Url
			try {
				Class.forName("com.mysql.jdbc.Driver"); // java反射，固定写法
				mConnection = (Connection) DriverManager.getConnection(url, "root", "03241X");
				LogUtil.log("创建数据库连接");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
		}
		return mConnection;
	}

	/**
	 * 查询操作
	 * 
	 * @param querySql
	 *            查询操作SQL语句
	 * @return 查询
	 * @throws SQLException
	 */
	public static ResultSet query(String querySql) throws SQLException {
		Statement stateMent = (Statement) getConnection().createStatement();
		return stateMent.executeQuery(querySql);
	}

	/**
	 * 插入、更新、删除操作
	 * 
	 * @param insertSql
	 *            插入操作的SQL语句
	 * @return
	 * @throws SQLException
	 */
	public static int update(String insertSql) throws SQLException {
		Statement stateMent = (Statement) getConnection().createStatement();
		return stateMent.executeUpdate(insertSql);
	}

	public static int update(String sql,Object...args)  {
		PreparedStatement preparedStatement = null;
		try {
			 preparedStatement = getConnection().prepareStatement(sql);


		for (int i = 0; i < args.length; i++) {
			preparedStatement.setObject(i+1,args[i]);
		}
			System.out.println(sql);
			return preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally {
			closeResources(null,preparedStatement);
		}
		return 0;
	}

	/**
	 * 关闭数据库连接
	 */
	public static void closeResources(Connection connection, Statement preparedStatement){
		try {
			if(preparedStatement !=null)
				preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if( connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources(Connection connection,Statement preparedStatement, ResultSet resultSet) {

		try {
			if(preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log("Statement关闭异常：[" + e.getErrorCode() + "]" + e.getMessage());
		}

		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log("连接关闭异常：[" + e.getErrorCode() + "]" + e.getMessage());
		}

		try {
			if(resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log("结果集关闭异常：[" + e.getErrorCode() + "]" + e.getMessage());
		}
	}
}
