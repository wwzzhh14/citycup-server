package javaSpider;

import com.springmvc.config.FutureConstant;
import com.springmvc.dao.FutureInfoDao;
import com.springmvc.dao.UserDao;
import com.springmvc.dataservice.FutureInfoDataService;
import com.springmvc.dataservice.UserDataService;
import com.springmvc.entities.StaticFutureInfoEntity;
import com.springmvc.utils.CrawlerUtil;
import com.springmvc.utils.NetUtil;
import junit.framework.TestCase;

import javax.annotation.Resource;

/**
 * Created by wzh on 16/8/14.
 */
public class Spider extends TestCase {

//
//    @Resource
//    FutureInfoDataService futureInfoDao = new FutureInfoDao();
//    UserDataService userDao=new UserDao();
//    public void testSpider(){
//        userDao.getUserByName("1111");
//
//
//        String content = NetUtil.httpGet("http://finance.sina.com.cn/futures/quotes/RB1610.shtml");
//
//
//        String code = FutureConstant.getFutureCodes()[0];
//        String suffix = FutureConstant.getFutureSuffix()[0];
////        for(String code:codes){
////            System.out.println(code);
//            String myCode=code+suffix;
//            StaticFutureInfoEntity entity=CrawlerUtil.grabStaticInfo(NetUtil.httpGet("http://finance.sina.com.cn/futures/quotes/"+myCode+".shtml"),myCode);
//        futureInfoDao.saveStaticFutureInfo(entity);
//        }
//    }


}
