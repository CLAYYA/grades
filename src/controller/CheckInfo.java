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
	 * 登陆时检查用户信息
	 * return 0表示没有这个用户，1表示密码正确，2表示用户存在但密码错误
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
