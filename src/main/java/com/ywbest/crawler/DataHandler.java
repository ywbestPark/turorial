package com.ywbest.crawler;

import com.ywbest.common.DBManager;

import java.sql.SQLException;
import java.util.List;

public class DataHandler {
    public static final String PRICE_INSERT_QUERY = "insert into price (date, symbol, name, close, open, high, low, volume) "
            + " values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";

    public static final String PRICE_SELECT_QUERY = "select symbol from price where date='%s' and symbol='%s'";

    private DBManager dbManager;

    public DataHandler(){
        try{
            dbManager = new DBManager();
        }catch (SQLException e){
            e.printStackTrace();
            dbManager = null;
        }
    }

    public int addPriceInfo(List<String> params){
        if(dbManager==null){
            return 0;
        }
        return dbManager.excuteUpdate(String.format(PRICE_INSERT_QUERY,
                params.get(0),params.get(1),params.get(2),params.get(3),
                params.get(4),params.get(5),params.get(6),params.get(7)));
    }

    public boolean exist(List<String> params){
        if(dbManager==null){
            return false;
        }
        return dbManager.exist(String.format(PRICE_SELECT_QUERY, params.get(0),params.get(1)));
    }

    public void close() {
        if(dbManager!=null){
            dbManager.close();
        }
    }
}
