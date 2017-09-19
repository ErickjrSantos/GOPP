package com.example.admin.estoquescan;

/**
 * Created by Admin on 15/09/2017.
 */

public class Flags {
    private boolean firstScan;
    private static Flags flg = null;

    private Flags(boolean firstScan){
        this.firstScan = firstScan;
    }

    public static Flags getInstance(){
        if(flg == null)
            flg = new Flags(true);
        return flg;
    }

    public boolean isFirstScan() {
        return firstScan;
    }

    public void setFirstScan(boolean scanned) {
        firstScan = scanned;
    }
}
