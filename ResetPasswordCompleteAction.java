package com.internousdev.mocha.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mocha.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware {

	private Map<String,Object> session;

	public String execute() throws SQLException {

		String result = ERROR;
		UserInfoDAO userInfoDAO = new UserInfoDAO();

		//パスワード再設定確認画面で確認したユーザIDと新しいパスワードをデータベースに格納する処理
		//成功時はcheckが1,失敗時はcheckが0
		int check = userInfoDAO.resetPassword(
				session.get("reset_user_id").toString(),
				session.get("reset_new_password").toString());

		if(check > 0) {
			result = SUCCESS;
		}

		//使用済みのセッションを削除する処理
		session.remove("reset_user_id");
		session.remove("reset_new_password");

		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}