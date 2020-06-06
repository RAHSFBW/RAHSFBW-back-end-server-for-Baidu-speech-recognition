package util;



import java.sql.*;

public class DatabaseUtil {

	private static Connection mConnection;

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return Ψһ���ݿ�����
	 */
	private static Connection getConnection() {
		if (mConnection == null) {
			String url = "jdbc:mysql://localhost:3306/servlettest1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; // ���ݿ��Url
			try {
				Class.forName("com.mysql.jdbc.Driver"); // java���䣬�̶�д��
				mConnection = (Connection) DriverManager.getConnection(url, "root", "03241X");
				LogUtil.log("�������ݿ�����");
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
	 * ��ѯ����
	 * 
	 * @param querySql
	 *            ��ѯ����SQL���
	 * @return ��ѯ
	 * @throws SQLException
	 */
	public static ResultSet query(String querySql) throws SQLException {
		Statement stateMent = (Statement) getConnection().createStatement();
		return stateMent.executeQuery(querySql);
	}

	/**
	 * ���롢���¡�ɾ������
	 * 
	 * @param insertSql
	 *            ���������SQL���
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
	 * �ر����ݿ�����
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
			LogUtil.log("Statement�ر��쳣��[" + e.getErrorCode() + "]" + e.getMessage());
		}

		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log("���ӹر��쳣��[" + e.getErrorCode() + "]" + e.getMessage());
		}

		try {
			if(resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log("������ر��쳣��[" + e.getErrorCode() + "]" + e.getMessage());
		}
	}
}
