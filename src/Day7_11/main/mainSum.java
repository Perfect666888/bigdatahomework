package Day7_11.main;


import Day7_11.util.SumUtils;
import Day7_11.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class mainSum {
    public static void main(String[] args) throws Exception {

       // SumUtils.SumSortByArts();
        HashMap<String, ArrayList<String>> classMap = SumUtils.SumSortByClass();

        //输出到文件
        for (Map.Entry<String, ArrayList<String>> entry : classMap.entrySet()) {
            //输出到文件
            String filename ="F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\"+entry.getKey()+".txt";
            Utils.ArrToFile(entry.getValue(), filename);
        }


    }
}
