package com.internousdev.mocha.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends  ActionSupport implements SessionAware {

	private Map<String,Object> session;
	private String backFlag;

	public String execute() throws SQLException{

		//ユーザー情報入力確認画面の戻るボタン以外の方法でユーザー情報入力画面へ移動したとき
		//フォームの入力値を初期化するため、使用済みのセッションを削除する
		if(	backFlag == null) {
			session.remove("create_user_id");
			session.remove("create_password");
			session.remove("create_family_name");
			session.remove("create_first_name");
			session.remove("create_family_name_kana");
			session.remove("create_first_name_kana");
			session.remove("create_email");
			session.remove("create_sex");
			session.remove("sexList");
		}
		//選択されている性別を取得
		if(!session.containsKey("create_sex")) {
			session.put("create_sex", "男性");
		}else {
			session.put("create_sex", String.valueOf(session.get("create_sex")));
		}

		//次のユーザー情報入力画面ラジオタグでの選択肢を作成
		if(!session.containsKey("sexList")) {
			List<String> sexList = new ArrayList<String>();
			sexList.add("男性");
			sexList.add("女性");
			session.put("sexList", sexList);
		}

		return SUCCESS;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public Map<String,Object> getSession() {
		return session;
	}

	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
}
