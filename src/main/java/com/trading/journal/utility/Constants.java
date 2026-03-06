package com.trading.journal.utility;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String RECORD_ALREADY_EXISTS= "Record already exists, duplicate record restricted.";
    public static String RECORD_ADDED_SUCCESSFULLY= "Record added successfully.";
    public static String RECORD_REMOVED_SUCCESSFULLY=  "Record removed successfully.";
    public static String RECORD_NOT_AVAILABLE= "Record doesn't exist in the collection to remove.";
    public static String RECORDS_EMPTY= "Collection is empty.";
    public static String TOTAL_RECORDS_COUNT= "Total :  %d records Cleared";

    public static String PROFIT_UPPERCASE= "PROFIT";
    public static String LOSS_UPPERCASE= "LOSS";
    public static String PROFIT= "profit";
    public static String LOSS= "loss";
    public static String BUY= "buy";
    public static String SELL= "sell";

    public static String MKT = "MKT";
    public static String NSE = "NSE";
    public static String NIFTY = "NIFTY";
    public static String OPTIONS = "Options";
    public static String INTRA_TRADE = "Intra trade";
    public static String CE = "CE";
}
