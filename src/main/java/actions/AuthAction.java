package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.EmployeeService;

public class AuthAction extends ActionBase {

    private EmployeeService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new EmployeeService();
        invoke();
        service.close();
    }

    /**
     * ログイン画面を表示する
     */
    public void showLogin() throws ServletException, IOException {
        //CSRF対策用トークンを設定
        putRequestScope(AttributeConst.TOKEN, getTokenId());
        //セッションにフラッシュメッセージが登録されている場合はリクエストスコープに設定する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        //ログイン画面を表示
        forward(ForwardConst.FW_LOGIN);
    }

    /**
     * ログイン処理
     */
    public void login() throws ServletException, IOException {
        String code = getRequestParam(AttributeConst.EMP_CODE);
        String plainPass = getRequestParam(AttributeConst.EMP_PASS);
        String pepper = getContextScope(PropertyConst.PEPPER);

        //有効な従業員か認証する
        Boolean isValidEmployee = service.validateLogin(code, plainPass, pepper);
        if(isValidEmployee) {
            //認証成功の場合
            if(checkToken()) {
                //ログインした従業員のDBデータを取得
                EmployeeView ev = service.findOne(code, plainPass, pepper);
                //セッションにログインした従業員を設定
                putSessionScope(AttributeConst.LOGIN_EMP, ev);
                //セッションにログイン完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGINED.getMessage());
                //トップページにリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        } else {
            //認証失敗の場合
            //入力値（社員番号）、CSRF対策用token、エラーフラグtrue、をリクエストスコープにセット
            putRequestScope(AttributeConst.EMP_CODE, code);
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.LOGIN_ERR, true);
            //ログイン画面を再表示
            forward(ForwardConst.FW_LOGIN);
        }
    }

    /**
     * ログアウト処理
     */
    public void logout() throws ServletException, IOException {
        //セッションに設定した従業員を削除
        removeSessionScope(AttributeConst.LOGIN_EMP);
        //セッションにログアウトのフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGOUT.getMessage());
        //ログインページにリダイレクト
        redirect(ForwardConst.ACT_AUTH, ForwardConst.CMD_SHOW_LOGIN);
    }

}
