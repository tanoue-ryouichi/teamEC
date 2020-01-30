package com.internousdev.mocha.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mocha.dao.UserInfoDAO;
import com.internousdev.mocha.util.CommonUtility;
import com.internousdev.mocha.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserConfirmAction extends ActionSupport implements SessionAware{

	private String user_id;//ユーザーID
	private String password;//パスワード
	private String family_name;//姓
	private String first_name;//名
	private String family_name_kana;//姓ふりがな
	private String first_name_kana;//名ふりがな
	private String sex;//性別。"男性"または"女性"
	private String email;//メールアドレス
	private Map<String,Object> session;
	private List<String> messageListFamilyName;//入力された姓が要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageListFirstName;//入力された名が要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageListFamilyNameKana;//入力された姓ふりがなが要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageListFirstNameKana;//入力された名ふりがなが要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageListUserId;//入力されたユーザーIDが要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageListPassword;//入力されたパスワードが要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageListEmail;//入力されたメールアドレスが要件を満たさないときに出力されるエラーメッセージ
	private String messageListUserCheck;//入力されたユーザIDが既に使用されていたときに出力されるエラーメッセージ
	private String concealedPassword;//ブラインドをかけたパスワードを格納する変数

	public String execute() throws SQLException {

		String result = ERROR;

		//ユーザー情報入力確認画面で情報を表示するためセッションに各項目の値をプット
		session.put("create_user_id", user_id);
		session.put("create_password", password);
		session.put("create_family_name", family_name);
		session.put("create_first_name", first_name);
		session.put("create_family_name_kana", family_name_kana);
		session.put("create_first_name_kana", first_name_kana);
		session.put("create_email", email);
		session.put("create_sex", sex);

		//ユーザー情報入力画面で入力されたユーザー情報に不備がないか確認する
		//InputCheckerクラスの各メゾッド（doCheckForEmailなど）にユーザー情報入力画面で入力された情報（user_idなど）を引数として挿入する
		//メゾッドの返り値はList型のstringList変数
		//同時にユーザー情報入力画面でエラーメッセージを表示するためにstringListを各messageListセッターに代入

		InputChecker input = new InputChecker();

		messageListFamilyName = input.doCheck("姓", family_name,1,16, true, true, true, false, true, false);
		messageListFirstName = input.doCheck("名", first_name,1,16, true,true, true, false, true, false);
		messageListFamilyNameKana = input.doCheck("姓ふりがな", family_name_kana,1,16, false, false, true, false, false, false);
		messageListFirstNameKana = input.doCheck("名ふりがな", first_name_kana,1,16, false, false, true, false, false, false);
		messageListUserId = input.doCheck("ユーザID", user_id,1,8, true, false, false, true, false, false);
		messageListPassword = input.doCheck("パスワード", password,1,16, true, false, false, true, false, false);
		messageListEmail = input.doCheckForEmail(email);
		//参考用 	Message = input.doCheck(項目名, 値, 最小文字数, 最大文字数, 半角英字, 漢字, ひらがな, 半角数字, カタカナ, スペース) true false
		//			Message = input.doCheck(propertyName, value, minLength, maxLength, availableAlphabeticCharacters, availableKanji, availableHiragana, availableHalfWidthDigit, availableKatakana, availableHalfWidthSpace)

		//ユーザー情報入力値チェック↓
		//もし各messageListになんらかのメッセージが入っていた場合、入力した情報は要件を満たしていないのでreturnする
		//もし各messageListにメッセージが何も入っていない場合、入力した情報は要件を満たしている
		if(	messageListFamilyName.size() > 0
		||	messageListFirstName.size() > 0
		||	messageListFamilyNameKana.size() > 0
		||	messageListFirstNameKana.size() > 0
		||	messageListUserId.size() > 0
		||	messageListPassword.size() > 0
		||	messageListEmail.size() > 0) {
			return result;
		}

		//ユーザIDが使用されているかどうかを確認する処理
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if(	userInfoDAO.createUser_check(user_id) == 1) {
			//使用されていたとき
			messageListUserCheck = "使用できないユーザーIDです。";
		}else {
			//パスワードを隠蔽処理
			CommonUtility commonUtility = new CommonUtility();
			concealedPassword = commonUtility.concealPassword(password);

			result = SUCCESS;
		}

		return result;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getFamily_name_kana() {
		return family_name_kana;
	}

	public void setFamily_name_kana(String family_name_kana) {
		this.family_name_kana = family_name_kana;
	}

	public String getFirst_name_kana() {
		return first_name_kana;
	}

	public void setFirst_name_kana(String first_name_kana) {
		this.first_name_kana = first_name_kana;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<String> getMessageListFamilyName() {
		return messageListFamilyName;
	}

	public void setMessageListFamilyName(List<String> messageListFamilyName) {
		this.messageListFamilyName = messageListFamilyName;
	}

	public List<String> getMessageListFirstName() {
		return messageListFirstName;
	}

	public void setMessageListFirstName(List<String> messageListFirstName) {
		this.messageListFirstName = messageListFirstName;
	}

	public List<String> getMessageListFamilyNameKana() {
		return messageListFamilyNameKana;
	}

	public void setMessageListFamilyNameKana(List<String> messageListFamilyNameKana) {
		this.messageListFamilyNameKana = messageListFamilyNameKana;
	}

	public List<String> getMessageListFirstNameKana() {
		return messageListFirstNameKana;
	}

	public void setMessageListFirstNameKana(List<String> messageListFirstNameKana) {
		this.messageListFirstNameKana = messageListFirstNameKana;
	}

	public List<String> getMessageListUserId() {
		return messageListUserId;
	}

	public void setMessageListUserId(List<String> messageListUserId) {
		this.messageListUserId = messageListUserId;
	}

	public List<String> getMessageListPassword() {
		return messageListPassword;
	}

	public void setMessageListPassword(List<String> messageListPassword) {
		this.messageListPassword = messageListPassword;
	}

	public List<String> getMessageListEmail() {
		return messageListEmail;
	}

	public void setMessageListEmail(List<String> messageListEmail) {
		this.messageListEmail = messageListEmail;
	}

	public String getMessageListUserCheck() {
		return messageListUserCheck;
	}

	public void setMessageListUserCheck(String messageListUserCheck) {
		this.messageListUserCheck = messageListUserCheck;
	}

	public String getConcealedPassword() {
		return concealedPassword;
	}

	public void setConcealedPassword(String concealedPassword) {
		this.concealedPassword = concealedPassword;
	}
}