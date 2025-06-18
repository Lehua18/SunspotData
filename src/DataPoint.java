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


}
