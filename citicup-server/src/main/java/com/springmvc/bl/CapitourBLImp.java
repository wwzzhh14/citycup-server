package com.springmvc.bl;

import com.springmvc.blservice.CapitourBLService;
import com.springmvc.dataservice.CapitourDataService;
import com.springmvc.dataservice.TransacDataService;
import com.springmvc.entities.CapitourEntity;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by guhan on 16/9/14.
 */
@Service
public class CapitourBLImp implements CapitourBLService {
    @Resource
    TransacDataService transacDataService;
    @Resource
    CapitourDataService capitourDataService;
    double[][] pair;
    List<String> date;

    public void init(){
        pair = new double[10000][4];
        date = new ArrayList<String>();
        String filePath = System.getProperty("user.dir") + "/matlab/" + "pair.txt";
        try {
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int i=0;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] split = lineTxt.split("\t");
                    date.add(split[0]);
                    pair[i][0] = Double.parseDouble(split[1]);
                    pair[i][1] = Double.parseDouble(split[2]);
                    pair[i][2] = Double.parseDouble(split[3]);
                    pair[i][3] = Double.parseDouble(split[4]);

                    i++;
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public ResultMessageVO startCapitour(String account, String userName, Date start, String end, double money) {
        double raw = capitourDataService.getMoney();
        capitourDataService.updateMoney(raw+money);
        return new ResultMessageVO(capitourDataService.addCapitour(new CapitourEntity(account, userName, start, end, money)),"");
    }



    //一个按天存的收益率列表
    public List<Double> viewProfit(String account, String userName, Date start) {
        List<Double> profitList = new ArrayList<Double>();
        CapitourEntity cap = capitourDataService.getCapitour(account, userName, start);
        String end = cap.getEnd();
        String[] endsplit = end.split("-");
        int end_year = Integer.parseInt(endsplit[0]);
        int end_month = Integer.parseInt(endsplit[1]);
        int end_day = Integer.parseInt(endsplit[2]);
        Date end_date = new Date();
        end_date.setYear(end_year);
        end_date.setMonth(end_month);
        end_date.setDate(end_day);
        int year_ptr = start.getYear();
        int month_ptr = start.getMonth();
        int day_ptr = start.getDay();
        Date ptr = new Date();
        ptr.setYear(year_ptr);
        ptr.setMonth(month_ptr);
        ptr.setDate(day_ptr);
        Calendar cal = new GregorianCalendar();
        cal.setTime(ptr);
        while(cal.getTime().before(end_date)){
            profitList.add(capitourDataService.getDailyRatio(cal.getTime().getYear()+"-"+cal.getTime().getMonth()+"-"+cal.getTime().getDay()));
            cal.add(cal.DATE,1);
        }
        return profitList;
    }



    public double[] getPairDelta() {
       double[] array = new double[date.size()];
        for(int i=0; i<array.length;i++){
            array[i] = pair[i][2];
        }
        return array;
    }

    public double[] getPairYield() {
        double[] array = new double[date.size()];
        for(int i=0; i<array.length;i++){
            array[i] = pair[i][3];
        }
        return array;

    }

    public List<String> getPairDate(){
        return this.date;
    }


}
