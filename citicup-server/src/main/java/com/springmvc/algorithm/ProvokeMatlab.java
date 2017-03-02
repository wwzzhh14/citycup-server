package com.springmvc.algorithm;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProvokeMatlab {

    String CMD = "matlab -nojvm -nodesktop -nosplash -r link2java";
    final String PATH_MATLAB = System.getProperty("user.dir") + "/matlab/";

    /**
     * process matrix in matlab.
     */
    private boolean invokeMatlab() {
        System.out.println("begin invoking matlab");
        int[] end = { 62, 62, 32 };
        int ptr = 0;
        int receive = -1;

        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(CMD, null, new File(PATH_MATLAB));
        } catch (IOException e1) {
            return false;
        }

        while (true) {
            try {
                receive = proc.getInputStream().read();
            } catch (IOException e) {
                return false;
            }
            if (receive == end[ptr]) {
                ptr++;
            } else {
                ptr = 0;
            }
            if (ptr == 3) {
                break;
            }
        }


        proc.destroyForcibly();
        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            return false;
        }

        System.out.println("Matlab finishes processing");
        return true;
    }

    private void readPair(){
        String filePath = System.getProperty("user.dir") + "/matlab/" + "pair.txt";
        List<Double> delta = new ArrayList<Double>();
        double[][] array = new double[10000][5];
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int i=0;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] split = lineTxt.split("\t");
                    delta.add(Double.parseDouble(split[2]));
                    array[i][2] = Double.parseDouble(split[2]);
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

        System.out.println(delta.get(2));
        System.out.println(array[2][2]);

    }


    private void readSignal(String fileName){
        String filePath = System.getProperty("user.dir") + "/matlab/" + "signal_result.txt";
        List<String> time = new ArrayList<String>();
        double[] DIF = new double[4757];
        double[] DEA = new double[4757];
        double[] MACD = new double[4757];
        double[] RSI = new double[4757];
        double[] Yield = new double[4757];
        try{
            File file = new File(filePath);
            if(file.isFile() && file.exists()){
                InputStreamReader ir = new InputStreamReader(new FileInputStream((file)));
                BufferedReader bufferedReader = new BufferedReader(ir);
                String lineTxt = null;
                int i=0;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] split = lineTxt.split("\t");
                    time.add(split[0]);
                    DIF[i] = Double.parseDouble(split[4]);
                    DEA[i] = Double.parseDouble(split[5]);
                    MACD[i] = Double.parseDouble(split[6]);
                    RSI[i] = Double.parseDouble(split[7]);
                    Yield[i] = Double.parseDouble(split[17]);
                    i++;
                }
                ir.close();
            }
            else{
                System.out.println("找不到指定文件");
            }
        }catch(Exception e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        System.out.print("Time: ");
        for(String t:time) {
            System.out.print(t);
            System.out.print(",");
        }
        System.out.println();
        System.out.print("DIF: ");
        for(int i=0; i<DIF.length;i++){
            System.out.print(DIF[i]);
            System.out.print(",");
        }
        System.out.println();
        System.out.print("DEA: ");
        for(int i=0;i<DEA.length;i++){
            System.out.print(DEA[i]);
            System.out.print(",");
        }
        System.out.println();
        System.out.print("MACD: ");
        for(int i=0; i<MACD.length;i++){
            System.out.print(MACD[i]);
            System.out.print(",");
        }
        System.out.println();
        System.out.print("RSI: ");
        for(int i=0; i<RSI.length;i++){
            System.out.print(RSI[i]);
            System.out.print(",");
        }
        System.out.println();
        System.out.print("收益率: ");
        for(int i=0; i<Yield.length;i++){
            System.out.print(Yield[i]);
            System.out.print(",");
        }
    }



    public static void main(String[] args){
        ProvokeMatlab provoker = new ProvokeMatlab();
//        provoker.invokeMatlab();
        provoker.readSignal("");

    }
}