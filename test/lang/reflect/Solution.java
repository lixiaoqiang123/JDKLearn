package lang.reflect;


import java.util.ArrayList;
import java.util.List;

/**
 * ⽂本型知识答案
 */
public class Solution {

    /**
     * 富⽂本内容
     */
    private String Content;
    /**
     * 纯⽂本内容
     */
    private String PlainText;
    /**
     * 视⻆id列表
     */
    private List<String> PerspectiveIds;

    /**
     * Solution Id主键, 新增时可空
     */
    private Long SolutionId;

    /**
     *  操作类型：追加：ADD，修改：UPD，移除：DEL
     */
    private String Action;

    public void setPerspectiveIds(List<String> PerspectiveIds){
        List<String> list = new ArrayList<>();
        list.add("1001");
        this.PerspectiveIds = list;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPlainText() {
        return PlainText;
    }

    public void setPlainText(String plainText) {
        PlainText = plainText;
    }

    public List<String> getPerspectiveIds() {
        return PerspectiveIds;
    }

    public Long getSolutionId() {
        return SolutionId;
    }

    public void setSolutionId(Long solutionId) {
        SolutionId = solutionId;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}
