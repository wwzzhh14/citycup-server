package com.springmvc.utils;

import com.springmvc.config.UrlConstant;
import com.springmvc.dao.FutureInfoDao;
import com.springmvc.dataservice.FutureInfoDataService;
import com.springmvc.entities.StaticFutureInfoEntity;
import com.springmvc.vo.StaticFutureInfo;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import javax.annotation.Resource;

/**
 * Created by wzh on 16/8/14.
 */
public class CrawlerUtil {



    public static StaticFutureInfo grabStaticInfo(String content,String code){


        Parser parser = Parser.createParser(content, UrlConstant.ECODING_FORMATE);
        HasAttributeFilter filter = new HasAttributeFilter("id","table-futures-basic-data");
        Node node = null;
        NodeList nodeList = null;
        try {
            nodeList = parser.parse(filter);
            node = nodeList.elementAt(0);
            TagNameFilter tagNameFilter = new TagNameFilter("td");
//            System.out.println(node.toHtml());
            parser = Parser.createParser(node.toHtml(), UrlConstant.ECODING_FORMATE);
            NodeList resultList = parser.parse(tagNameFilter);
//            for (int i = 0; i < resultList.size(); ++i) {
//                node = resultList.elementAt(i);
//                System.out.println(node.toPlainTextString());
//            }

//            return new StaticFutureInfo(resultList.elementAt(0).toPlainTextString(),code,
//                    resultList.elementAt(5).toPlainTextString(),resultList.elementAt(14).toPlainTextString(),0);

            return  null;

//
//    private String name;
//    @Id
//    private String code;
//    //交割月份
//    private String deliveryMonth;
//    //上市交易所
//    private String place;
//    //最低保证金比率
//    private String minMarginRatio;



        } catch (ParserException e) {

            e.printStackTrace();
            return null;
        }

    }


}
