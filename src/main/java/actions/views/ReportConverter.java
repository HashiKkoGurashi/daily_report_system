package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Report;

/*
 * 日報データのDTOモデル ⇔ Viewモデル 相互変換を行うクラス
 */
public class ReportConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     */
    public static Report toModel(ReportView rv) {
        Report r = new Report(
                rv.getId(),
                EmployeeConverter.toModel(rv.getEmployee()),
                rv.getReportDate(),
                rv.getTitle(),
                rv.getContent(),
                rv.getCreatedAt(),
                rv.getUpdatedAt(),
                rv.getAttendAt(),
                rv.getLeaveAt());

        return r;
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     */
    public static ReportView toView(Report r) {

        if(r == null) {
            return null;
        }

        ReportView rv = new ReportView(
                r.getId(),
                EmployeeConverter.toView(r.getEmployee()),
                r.getReportDate(),
                r.getTitle(),
                r.getContent(),
                r.getCreatedAt(),
                r.getUpdatedAt(),
                r.getAttendAt(),
                r.getLeaveAt());
        return rv;
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     */
    public static List<ReportView> toViewList(List<Report> list) {
        List<ReportView> viewList = new ArrayList<ReportView>();
            for(Report r : list) {
                viewList.add(toView(r));
            }
        return viewList;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     */
    public static void copyViewToModel(Report r, ReportView rv) {
        r.setId(rv.getId());
        r.setEmployee(EmployeeConverter.toModel(rv.getEmployee()));
        r.setReportDate(rv.getReportDate());
        r.setTitle(rv.getTitle());
        r.setContent(rv.getContent());
        r.setCreatedAt(rv.getCreatedAt());
        r.setUpdatedAt(rv.getUpdatedAt());
        r.setAttendAt(rv.getAttendAt());
        r.setLeaveAt(rv.getLeaveAt());
    }

}
