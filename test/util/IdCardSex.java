package util;

import cn.hutool.core.util.IdcardUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 分辨身份证男女
 */
public class IdCardSex {


    @Test
    public void idCardSex(){
        List<String> idCardList = new ArrayList<>();
        idCardList.add("410901199708092012");
        idCardList.add("412702199601194120");
        idCardList.add("410526198603252316");
        idCardList.add("411081199604185963");
        idCardList.add("411422198905105418");
        idCardList.add("410325199405107019");
        for(String idCard:idCardList){
            System.out.println(getSex(idCard));
        }

    }

    private Integer getSex(String idCard){
        return IdcardUtil.getGenderByIdCard(idCard);
    }
}
