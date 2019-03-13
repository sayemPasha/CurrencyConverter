package com.currencyconverter;

import android.util.Log;

import java.util.Hashtable;

/**
 * Created by Sayem on 12-Mar-19.
 */

public class Currency {
    //public static final int totalCountries = 7;
    static int fromCountryIndex = 0;
    static int toCountryIndex = 0;

//    public static double[][] factor =
//            {
//            {1, 4, 2} ,
//            {3, 1, 7} ,
//            {5, 6, 1}};

    public static double[][] factor = new double[100][100];
    //public static final String[] indexToCountry = {"a", "b", "c"}; // array index as country index
    public static final String[] indexToCountry =
            { "EUR",
                    "GBP",
                    "AUD",
                    "CAD",
                    "INR",
                    "BDT",
                    "JPY"};
    public static final double [] us_1_unit =
            {0.69881,
                    0.61095,
                    0.93188,
                    0.96680,
                    44.72,
                    73.14,
                    80.55};

    public static int getTotalCountries()
    {
        return indexToCountry.length;
    }

    //To determine the rate of mutual convertion
    //example: 1 BDT - > ? INR
    public static double baseConverter(double baseRateFrom, double baseRateTo )
    {
        return (1.0/baseRateFrom) * baseRateTo;
    }

    public static int getCountryToIndex(String str)
    {
        int totalCountries = getTotalCountries();
        for(int i=0; i<totalCountries; i++){
            if(str == indexToCountry[i]){
                return i;
            }
        }
        return -1;
    }

    public static double convert(double amount)
    {
        return baseConverter(us_1_unit[fromCountryIndex], us_1_unit[toCountryIndex]) * amount;
    }
}
