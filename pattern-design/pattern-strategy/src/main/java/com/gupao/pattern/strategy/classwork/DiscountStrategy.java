package com.gupao.pattern.strategy.classwork;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 22:17
 * @email soda931vzr@163.com
 * @description
 */
public class DiscountStrategy {

    private static SimpleDateFormat sdf = new SimpleDateFormat("MM");

    public static DiscountActivity get(Date date) {

        DiscountActivity discountActivity;
        String dateStr = sdf.format(date);
        int month = Integer.parseInt(dateStr);

        if(month >= 1 && month <=3){
            discountActivity = new SpringDiscount();
        }else if(month <= 6){
            discountActivity = new SummerDiscount();
        } else if (month <= 9) {
            discountActivity = new AutumnDiscount();
        }else {
            discountActivity = new WinterDiscount();
        }

        return discountActivity;
    }
}