package com.internousdev.mocha.dto;

public class UserInfoDTO {

	private String user_id;//ユーザーID
	private String password;//パスワード
	private String family_name;//姓
	private String first_name;//名
	private String family_name_kana;//姓ふりがな
	private String first_name_kana;//名ふりがな
	private byte sex;//性別
	private String email;//メールアドレス
	private byte loginFlg;//ログインフラグ

	//ユーザID
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	//パスワード
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//姓
	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	//名
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	//姓ふりがな
	public String getFamily_name_kana() {
		return family_name_kana;
	}

	public void setFamily_name_kana(String family_name_kana) {
		this.family_name_kana = family_name_kana;
	}

	//名ふりがな
	public String getFirst_name_kana() {
		return first_name_kana;
	}

	public void setFirst_name_kana(String first_name_kana) {
		this.first_name_kana = first_name_kana;
	}

	//性別
	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	//メールアドレス
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//ログインフラグ、未ログインなら0、ログインなら1
	public byte getLoginFlg() {
		return loginFlg;
	}

	public void setLoginFlg(byte loginFlg) {
		this.loginFlg = loginFlg;
	}
}
