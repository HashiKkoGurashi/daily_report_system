package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import constants.ForwardConst;

public class UnknownAction extends ActionBase {

    /**
     * 共通エラー画面「お探しのページは見つかりませんでした。」を表示する
     */
    @Override
    public void process() throws ServletException, IOException {
        forward(ForwardConst.FW_ERR_UNKNOWN);
    }

}
