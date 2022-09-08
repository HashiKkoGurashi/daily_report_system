package models.validators;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import actions.views.ReportView;
import constants.MessageConst;

/**
 * 日報インスタンスに設定されている値のバリデーションを行うクラス
 */
public class ReportValidator {

    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     */
    public static List<String> validate(ReportView rv) {
        List<String> errors = new ArrayList<String>();

        //タイトルのチェック
        String titleError = validateTitle(rv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }

        //内容のチェック
        String contentError = validateContent(rv.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        //出勤時間のチェック
        String attendAtError = validateAttendAt(rv.getAttendAt());
        if (!attendAtError.equals("")) {
            errors.add(attendAtError);
        }

        //退社時間のチェック
        String leaveAtError = validateLeaveAt(rv.getLeaveAt());
        if (!leaveAtError.equals("")) {
            errors.add(leaveAtError);
        }

        return errors;
    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     */
    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
    * 出勤時間の有無＆型をチェック
    */
   private static String validateAttendAt(Time attendAt) {
       String attendAtStr = attendAt.toString();
       //入力値があるかチェック
       if (attendAtStr == null || attendAtStr.equals("")) {
           return MessageConst.E_NOATTEND_AT.getMessage();
       }

       /**
       //型チェック
       Pattern pattern = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$|^([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
       if (!pattern.matcher(attendAtStr).matches()) {
           return MessageConst.E_TYPE_MISMATCH.getMessage();
       }
       **/

       //入力値があり、型も正しければ空文字を返却
       return "";
   }

   /**
    * 退勤時間の有無＆型チェック
    */
   private static String validateLeaveAt(Time leaveAt) {
       String leaveAtStr = leaveAt.toString();
       //入力値があるかチェック
       if (leaveAtStr == null || leaveAtStr.equals("")) {
           return MessageConst.E_NOLEAVE_AT.getMessage();
       }

       /**
       //型チェック
       Pattern pattern = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$|^([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
       if (!pattern.matcher(leaveAtStr).matches()) {
           return MessageConst.E_TYPE_MISMATCH.getMessage();
       }
       **/

       //入力値があり、型も正しければ空文字を返却
       return "";
   }
}
