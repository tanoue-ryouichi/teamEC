package com.internousdev.mocha.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends  ActionSupport implements SessionAware  {

	private String backFlag;
	private Map<String,Object> session;

	public String execute() {

		//パスワード再設定確認画面の戻るボタン以外の方法でパスワード再設定入力画面へ移動したとき
		//フォームの入力値を初期化するため、使用済みのセッションを削除する
		if(backFlag == null) {
			session.remove("reset_user_id");
		}

		return SUCCESS;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}