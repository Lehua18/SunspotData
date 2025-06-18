public class DataPoint {
    private int year;
    private int month;
    private int day;
    private double yrAsDec;
    private int sunspotNum;
    private int nSunspotNum;
    private int sSunspotNum;
    private double standDev;
    private double nStandDev;
    private double sStandDev;
    private int numObs;
    private int nNumObs;
    private int sNumObs;
    private String provInd;
    private String type;


    //constructor for hemDaily
    public DataPoint(int year, int month, int day, double yrAsDec, int sunspotNum, int nSunspotNum, int sSunspotNum, double standDev, double nStandDev,   double sStandDev, int numObs, int nNumObs, int sNumObs, String provInd, String type){
        year = this.year;
        month = this.month;
        day = this.day;
        yrAsDec = this.yrAsDec;
        sunspotNum = this.sunspotNum;
        nSunspotNum = this.nSunspotNum;
        sSunspotNum = this.sSunspotNum;
        standDev = this.standDev;
        nStandDev = this.nStandDev;
        sStandDev = this.sStandDev;
        numObs = this.numObs;
        nNumObs = this.nNumObs;
        sNumObs = this.sNumObs;
        provInd = this.provInd;
        type = this.type;
    }

    //constructor for hemMonthly
    public DataPoint(int year, int month, double yrAsDec, int sunspotNum, int nSunspotNum, int sSunspotNum, double standDev, double nStandDev,   double sStandDev, int numObs, int nNumObs, int sNumObs, String provInd, String type){
        year = this.year;
        month = this.month;
        yrAsDec = this.yrAsDec;
        sunspotNum = this.sunspotNum;
        nSunspotNum = this.nSunspotNum;
        sSunspotNum = this.sSunspotNum;
        standDev = this.standDev;
        nStandDev = this.nStandDev;
        sStandDev = this.sStandDev;
        numObs = this.numObs;
        nNumObs = this.nNumObs;
        sNumObs = this.sNumObs;
        provInd = this.provInd;
        type = this.type;

    }

    //constructor for daily
    public DataPoint(int year, int month, int day, double yrAsDec, int sunspotNum, double standDev, int numObs, String provInd, String type){
        year = this.year;
        month = this.month;
        day = this.day;
        yrAsDec = this.yrAsDec;
        sunspotNum = this.sunspotNum;
        standDev = this.standDev;
        numObs = this.numObs;
        provInd = this.provInd;
        type = this.type;

    }

    //constructor for monthly
    public DataPoint(int year, int month, double yrAsDec, int sunspotNum, double standDev, int numObs, String provInd, String type){
        year = this.year;
        month = this.month;
        yrAsDec = this.yrAsDec;
        sunspotNum = this.sunspotNum;
        standDev = this.standDev;
        numObs = this.numObs;
        provInd = this.provInd;
        type = this.type;

    }

    //constructor for yearly
    public DataPoint( double yrAsDec, int sunspotNum, double standDev, int numObs, String provInd, String type){
        yrAsDec = this.yrAsDec;
        sunspotNum = this.sunspotNum;
        standDev = this.standDev;
        numObs = this.numObs;
        provInd = this.provInd;
        type = this.type;

    }

    //getters
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public double getYrAsDec() {
        return yrAsDec;
    }

    public double getnStandDev() {
        return nStandDev;
    }
    public double getsStandDev() {
        return sStandDev;
    }
    public double getStandDev() {
        return standDev;
    }

    public int getSunspotNum() {
        return sunspotNum;
    }
    public int getnSunspotNum() {
        return nSunspotNum;
    }
    public int getsSunspotNum() {
        return sSunspotNum;
    }

    public int getNumObs() {
        return numObs;
    }
    public int getnNumObs() {
        return nNumObs;
    }
    public int getsNumObs() {
        return sNumObs;
    }

    public String getProvInd() {
        return provInd;
    }

    public String getType() {
        return type;
    }

    //toString
    public String toString(){
        return
    }

}
