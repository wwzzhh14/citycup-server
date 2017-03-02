package bltestdriver;

import com.springmvc.bl.FutureInfoImp;
import com.springmvc.blservice.CapitourBLService;
import com.springmvc.blservice.FutureInfoBLService;
import com.springmvc.vo.FutureSummaryVO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 16/8/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class FutureInfoTestDriver extends TestCase {

    @Resource
    FutureInfoBLService futureInfoBLService;

    @Resource
    CapitourBLService capitourBLService;

    @Test
    public void testGetMinInfo(){

//        futureInfoBLService.getMinHistory("AU1612");
//        System.out.println(futureInfoBLService.getFutureByCode("AUTD").getNowPrice());


       capitourBLService.init();
        List<String> date = capitourBLService.getPairDate();
        System.out.println(date.get(0));
        String r="[";
        for(int i=2;i<date.size();i++){
            r=r+"\""+date.get(i)+"\",";
        }
        System.out.println(r);
        double[] a = capitourBLService.getPairDelta();
        String l="[";
        for(int i=2;i<a.length;i++){
            l=l+a[i]+",";
        }
        System.out.println(l);
        System.out.println(a.length);
        System.out.println(date.size());
    }

}
