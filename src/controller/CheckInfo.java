package controller;

import Dao.Jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckInfo {
	/*
	 * ��½ʱ����û���Ϣ
	 * return 0��ʾû������û���1��ʾ������ȷ��2��ʾ�û����ڵ��������
	 */
	public int isMember(String table, String id, String passwd) {
		int auth;
		if (table.equals("student")) auth = 0;
		else if (table.equals("teacher")) auth = 1;
		else auth = 2;
		Connection connection = Jdbc.CONNECTION;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT Password FROM User WHERE id = ? AND Auth = ?");
			preparedStatement.setString(1,id);
			preparedStatement.setInt(2,auth);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String TruePWD = resultSet.getString(1);
			System.out.println(resultSet+"\n"+TruePWD);
			if (TruePWD.equals(passwd))
				return 1;
			else return 2;
		} catch (SQLException e) {
			return 0;
		}
	}

}
