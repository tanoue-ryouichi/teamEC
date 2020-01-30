package com.internousdev.mocha.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mocha.dao.UserInfoDAO;
import com.internousdev.mocha.util.CommonUtility;
import com.internousdev.mocha.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware {

	private String user_id;//ユーザーID
	private String now_password;//現在のパスワード
	private String new_password;//新しいパスワード
	private String new_password_confirm;//新しいパスワード（再確認）
	private Map<String,Object> session;
	private List<String> messageList_user_id;//入力されたユーザーIDが要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageList_now_password;//入力された現在のパスワードが要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageList_new_password;//入力された新しいパスワードが要件を満たさないときに出力されるエラーメッセージ
	private List<String> messageList_new_password_confirm;//入力された新しいパスワード（再確認）が要件を満たさないときに出力されるエラーメッセージ
	private String message_password_match; //入力された新しいパスワードと新しいパスワード（再確認）が不一致のときに出力されるメッセージ
	private String message_user_match; //ユーザが存在していないときに出力されるメッセージ
	private String concealedPassword ;//ブラインドをかけたパスワードを格納する変数

	public String execute() throws SQLException {

		String result = ERROR;

		//パスワード再設定確認画面で情報を表示するためセッションにユーザーIDの値をプット
		session.put("reset_user_id", user_id);

		//パスワード再設定入力画面で入力されたユーザー情報に不備がないか確認する
		//InputCheckerクラスの各メゾッド（doCheckForEmailなど）にパスワード再設定入力画面で入力された情報（user_idなど）を引数として挿入する
		//メゾッドの返り値はList型のstringList変数
		//同時にパスワード再設定入力画面でエラーメッセージを表示するためにstringListを各messageListセッターに代入

		InputChecker input = new InputChecker();

		messageList_user_id = input.doCheck("ユーザID", user_id,1,8, true, false, false, true, false, false);
		messageList_now_password = input.doCheck("現在のパスワード", now_password,1,16, true, false, false, true, false, false);
		messageList_new_password = input.doCheck("新しいパスワード", new_password,1,16, true, false, false, true, false, false);
		messageList_new_password_confirm = input.doCheck("新しいパスワード（再確認）", new_password_confirm,1,16, true, false, false, true, false, false);
		//参考用 	Message = input.doCheck(項目名, 値, 最小文字数, 最大文字数, 半角英字, 漢字, ひらがな, 半角数字, カタカナ, スペース) true false
		//			Message = input.doCheck(propertyName, value, minLength, maxLength, availableAlphabeticCharacters, availableKanji, availableHiragana, availableHalfWidthDigit, availableKatakana, availableHalfWidthSpace)

		//パスワード再設定画面入力値チェック↓
		//もし各messageListになんらかのメッセージが入っていた場合、入力した情報は要件を満たしていないのでreturnする
		//もし各messageListにメッセージが何も入っていない場合、入力した情報は要件を満たしている
		if(	messageList_user_id.size() > 0
		||	messageList_now_password.size() > 0
		||	messageList_new_password.size() > 0
		||	messageList_new_password_confirm.size() > 0) {
			return result;
		}

		//新しいパスワードと新しいパスワード（再確認）が一致しているかどうかを確認する処理
		message_password_match = input.doPasswordCheck(new_password, new_password_confirm);
		if(	message_password_match != null) {
			return result;
		}

		//データベースの会員情報テーブルにユーザが存在しているかどうか確認する処理
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if(	userInfoDAO.resetPassword_check(user_id,now_password) <= 0) {
			//存在していないとき
			message_user_match = "ユーザーIDまたは現在のパスワードが異なります。";
		}else {
			//パスワードを隠蔽処理
			CommonUtility commonUtility = new CommonUtility();
			concealedPassword = commonUtility.concealPassword(new_password);
			//パスワード再設定確認画面で情報を表示するためセッションに新しいパスワードの値をプット
			session.put("reset_new_password", new_password);

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

	public String getNow_password() {
		return now_password;
	}

	public void setNow_password(String now_password) {
		this.now_password = now_password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getNew_password_confirm() {
		return new_password_confirm;
	}

	public void setNew_password_confirm(String new_password_confirm) {
		this.new_password_confirm = new_password_confirm;
	}

	public List<String> getMessageList_user_id() {
		return messageList_user_id;
	}

	public void setMessageList_user_id(List<String> messageList_user_id) {
		this.messageList_user_id = messageList_user_id;
	}

	public List<String> getMessageList_now_password() {
		return messageList_now_password;
	}

	public void setMessageList_now_password(List<String> messageList_now_password) {
		this.messageList_now_password = messageList_now_password;
	}

	public List<String> getMessageList_new_password() {
		return messageList_new_password;
	}

	public void setMessageList_new_password(List<String> messageList_new_password) {
		this.messageList_new_password = messageList_new_password;
	}

	public List<String> getMessageList_new_password_confirm() {
		return messageList_new_password_confirm;
	}

	public void setMessageList_new_password_confirm(List<String> messageList_new_password_confirm) {
		this.messageList_new_password_confirm = messageList_new_password_confirm;
	}

	public String getMessage_password_match() {
		return message_password_match;
	}

	public void setMessage_password_match(String message_password_match) {
		this.message_password_match = message_password_match;
	}

	public String getMessage_user_match() {
		return message_user_match;
	}

	public void setMessage_user_match(String message_user_match) {
		this.message_user_match = message_user_match;
	}

	public String getConcealedPassword() {
		return concealedPassword;
	}

	public void setConcealedPassword(String concealedPassword) {
		this.concealedPassword = concealedPassword;
	}

	public Map<String,Object> getSession(){
		return this.session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
