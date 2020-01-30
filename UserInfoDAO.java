package com.internousdev.mocha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.mocha.dto.UserInfoDTO;
import com.internousdev.mocha.util.DBConnector;

public class UserInfoDAO {

	//ユーザー登録情報作成機能用DAO。各ユーザー情報を引数に受け取りinsert文でデータベースに格納
	//成功時はcheckが1、失敗時はcheckが0
	public int createUser(	String user_id,String password,
							String family_name,String first_name,String family_name_kana,String first_name_kana,
							byte sex,
							String email) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int check  = 0;

		String sql = "INSERT INTO user_info ("
				+ "user_id,"
				+ "password,"
				+ "family_name,"
				+ "first_name,"
				+ "family_name_kana,"
				+ "first_name_kana,"
				+ "sex,"
				+ "email,"
				+ "status,"
				+ "logined,"
				+ "regist_date,"
				+ "update_date"
				+ ")"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,now(),now())";

		try{
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1,user_id);
			ps.setString(2,password);
			ps.setString(3,family_name);
			ps.setString(4,first_name);
			ps.setString(5,family_name_kana);
			ps.setString(6,first_name_kana);
			ps.setByte(7,sex);
			ps.setString(8,email);
			ps.setInt(9, 0);
			ps.setInt(10, 1);

			check  = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return check ;
	}

	//ユーザIDの重複確認用DAO（ユーザー登録情報作成機能）。ユーザーIDを引数に受け取り
	//select文でユーザIDがすでに使われていないか検索
	//ユーザIDが使われていればcheckが1、使われていなければcheckが0
	public int createUser_check (String user_id) throws SQLException{

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int check = 0;

		String sql = "SELECT * from user_info where "
				+ "user_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);

			ResultSet rs = ps.executeQuery();

			//入力されたユーザーIDが存在するかどうか、存在すればcheckが1になる
			if(rs.next()) {
				check = 1;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return check;
	}

	//ログイン認証機能用DAO。ログイン画面からユーザーIDとパスワードを引数に受け取り、select文でユーザを検索。
	//入力されたIDとパスを持つユーザーが存在しているかどうか判定する。
	//もしユーザが存在すれば認証成功としUserInfoDTOのsetLoginFlgメゾットからloginFlgの値を1にする。
	public UserInfoDTO getLoginUserInfo(String user_id,String password) throws SQLException{

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		UserInfoDTO userInfoDTO = new UserInfoDTO();

		String sql = "SELECT * FROM user_info where "
				+ "user_id = ? AND "
				+ "password = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			//会員情報テーブルに入力されたユーザーIDとパスワードを持つユーザーがいたとき
			if(rs.next()) {
				userInfoDTO.setUser_id(rs.getString("user_id"));
				if(rs.getString("user_id") != null){
					userInfoDTO.setLoginFlg((byte) 1);
				}
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return userInfoDTO;
	}

	//マイページ機能用DAO。ユーザーIDを引数に受け取り、性、名、ふりがな、性別、メールアドレスなどの情報をUserInfoDTOに入れて返す。
	public UserInfoDTO getMyPageInfo(String user_id) throws SQLException{

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		UserInfoDTO userInfoDTO = new UserInfoDTO();

		String sql = "SELECT * FROM user_info where "
				+ "user_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				userInfoDTO.setFamily_name(rs.getString("family_name"));
				userInfoDTO.setFirst_name(rs.getString("first_name"));
				userInfoDTO.setFamily_name_kana(rs.getString("family_name_kana"));
				userInfoDTO.setFirst_name_kana(rs.getString("first_name_kana"));
				userInfoDTO.setSex(rs.getByte("sex"));
				userInfoDTO.setEmail(rs.getString("email"));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return userInfoDTO;
	}

	//パスワード再設定機能用DAO。ユーザーIDと新しいパスワードを引数に受け取り、update文で会員情報テーブルのパスワードを更新する
	//成功時はcheckが1、失敗時はcheckが0
	public int resetPassword (String user_id,String new_password) throws SQLException{

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int check = 0;

		String sql = "UPDATE user_info SET "
				+ "password = ? ,"
				+ "update_date=now() "
				+ "where "
				+ "user_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, new_password);
			ps.setString(2, user_id);

			check = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return check;
	}

	//ユーザーの存在確認用DAO（パスワード再設定機能）。ユーザーIDとパスワードを引数に受け取り、会員情報テーブルから検索
	//ユーザーが存在しているとcheckが1、ユーザーが存在しなければcheckが0
	public int resetPassword_check (String user_id,String password) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int check = 0;

		String sql = "SELECT * from user_info where "
				+ "user_id = ? AND "
				+ "password = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			//会員情報テーブルにユーザーが存在していればcheckが1
			if(rs.next()) {
				check = 1;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return check;
	}
}