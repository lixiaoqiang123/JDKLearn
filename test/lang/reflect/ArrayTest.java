package lang.reflect;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

public class ArrayTest {

    public static void main(String[] args) {
        HashMap<String, Object> resultMap = new HashMap<>();
        String[] serviceCodeArray = {"tysl_ol","tysl","qytb_ol","qytb","ztjc_ol","ztjc","qpp_ysb","qpp_other","wechat_oa","wechat_lp","alipay_lp",
                "tysl_ol_transact","tysl_transact","qytb_ol_transact","qytb_transact","ztjc_o_transactl","ztjc_transact","qpp_ysb_transact","qpp_other_transact","wechat_oa_transact","wechat_lp_transact","alipay_lp_transact"};
        for (String s : serviceCodeArray) {
            resultMap.put(s, 0);
        }
        System.out.println(JSONUtil.toJsonStr(resultMap));
    }

    private HashMap<String, Object> initResultMap(){
        HashMap<String, Object> resultMap = new HashMap<>();
        String[] serviceCodeArray = {"tysl_ol","tysl","qytb_ol","qytb","ztjc_ol","ztjc","qpp_ysb","qpp_other","wechat_oa","wechat_lp","alipay_lp",
                "tysl_ol_transact","tysl_transact","qytb_ol_transact","qytb_transact","ztjc_o_transactl","ztjc_transact","qpp_ysb_transact","qpp_other_transact","wechat_oa_transact","wechat_lp_transact","alipay_lp_transact"};
        for (String s : serviceCodeArray) {
            resultMap.put(s, 0);
        }
        return resultMap;
    }


    @Test
    public void testParse(){
        String text = "[{\"Summary\":\"修改2\",\"ModifyTime\":\"2018-09-13T02:11:23Z\",\"CreateTime\":\"2018-09-13T02:03:14Z\",\"PerspectiveIds\":[\"1001\"],\"PlainText\":\"修改2\",\"SolutionId\":10000003071,\"Content\":\"修改2\"}]";
        List<Solution> solutions = JSONObject.parseArray(text).toJavaList(Solution.class);
        for(Solution solution:solutions){
            System.out.println(solution.getContent());
        }

    }

    @Test
    public void testGetInetAddress(){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAddress = address.getHostAddress();
        System.out.println(hostAddress);
    }
}
