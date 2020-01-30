package com.internousdev.mocha.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mocha.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;

	public String execute() throws SQLException {

		String result = ERROR;
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		byte sex;

		//sessionに追加した性別を文字から数値へ変換する処理
		if(session.get("create_sex").toString().equals("男性")) {
			sex = 0;
		}else {
			sex = 1;
		}

		//ユーザー情報入力確認画面で確認したユーザー情報をデータベースに格納する処理
		//成功時はcheckが1、失敗時はcheckが0
		int check = userInfoDAO.createUser(
				session.get("create_user_id").toString(),
				session.get("create_password").toString(),
				session.get("create_family_name").toString(),
				session.get("create_first_name").toString(),
				session.get("create_family_name_kana").toString(),
				session.get("create_first_name_kana").toString(),
				sex,
				session.get("create_email").toString());

		if(check > 0) {
			result = SUCCESS;
			//ログイン認証機能にユーザIDを送るためのセッション処理//
			session.put("login_user_id", session.get("create_user_id").toString());
		}

		//使用済みのセッションを削除する処理
		session.remove("create_user_id");
		session.remove("create_password");
		session.remove("create_family_name");
		session.remove("create_first_name");
		session.remove("create_family_name_kana");
		session.remove("create_first_name_kana");
		session.remove("create_email");
		session.remove("create_sex");

		return result;
	}

	public Map<String,Object> getSession(){
		return this.session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
