import java.util.ArrayList;

public class DataPoint {
    //Sets all variables to initial values
    private int year = -1;
    private int month = -1;
    private int day = -1;
    private double yrAsDec =-1.0;
    private double sunspotNum = -1;
    private double nSunspotNum = -1;
    private double sSunspotNum = -1;
    private double standDev =-1.0;
    private double nStandDev =-1.0;
    private double sStandDev = -1.0;
    private int numObs = -1;
    private int nNumObs = -1;
    private int sNumObs = -1;
    private String provInd;
    private String type;
    private boolean provBool;


  public DataPoint(ArrayList<String> data){
      //Creates a data point if 'daily' data is used
      if(data.size() == 8){
          year = Integer.parseInt(data.get(0));
          month = Integer.parseInt(data.get(1));
          day = Integer.parseInt(data.get(2));
          yrAsDec = Double.parseDouble(data.get(3));
          sunspotNum = Double.parseDouble(data.get(4));
          standDev = Double.parseDouble(data.get(5));
          numObs = Integer.parseInt(data.get(6));
          provInd = data.get(7);
          type = "daily";

      //Creates a datapoint if 'monthly' or 'monthly smoothed' data is used
      }else if(data.size() == 7){
          year = Integer.parseInt(data.get(0));
          month = Integer.parseInt(data.get(1));
          yrAsDec = Double.parseDouble(data.get(2));
          sunspotNum = Double.parseDouble(data.get(3));
          standDev = Double.parseDouble(data.get(4));
          numObs = Integer.parseInt(data.get(5));
          provInd = data.get(6);
          type = "monthly";

      //Creates data point if 'yearly' data is used
      }else if(data.size() == 5){
          yrAsDec = Double.parseDouble(data.get(0));
          sunspotNum = Double.parseDouble(data.get(1));
          standDev = Double.parseDouble(data.get(2));
          numObs = Integer.parseInt(data.get(3));
          provInd = data.get(4);
          type = "yearly";

      //Creates data point if 'daily hemispheric' data is used
      } else if (data.size() == 14) {
          year = Integer.parseInt(data.get(0));
          month = Integer.parseInt(data.get(1));
          day = Integer.parseInt(data.get(2));
          yrAsDec = Double.parseDouble(data.get(3));
          sunspotNum = Double.parseDouble(data.get(4));
          nSunspotNum = Double.parseDouble(data.get(5));
          sSunspotNum = Double.parseDouble(data.get(6));
          standDev = Double.parseDouble(data.get(7));
          nStandDev = Double.parseDouble(data.get(8));
          sStandDev = Double.parseDouble(data.get(9));
          numObs = Integer.parseInt(data.get(10));
          nNumObs = Integer.parseInt(data.get(11));
          sNumObs = Integer.parseInt(data.get(12));
          provInd = data.get(13);
          type = "hemDaily";

      //Creates data point if 'monthly hemispheric' or 'monthly smoothed hemispheric' data is used
      }else if(data.size() == 13){
          year = Integer.parseInt(data.get(0));
          month = Integer.parseInt(data.get(1));
          yrAsDec = Double.parseDouble(data.get(2));
          sunspotNum = Double.parseDouble(data.get(3));
          nSunspotNum = Double.parseDouble(data.get(4));
          sSunspotNum = Double.parseDouble(data.get(5));
          standDev = Double.parseDouble(data.get(6));
          nStandDev = Double.parseDouble(data.get(7));
          sStandDev = Double.parseDouble(data.get(8));
          numObs = Integer.parseInt(data.get(9));
          nNumObs = Integer.parseInt(data.get(10));
          sNumObs = Integer.parseInt(data.get(11));
          provInd = data.get(12);
          type = "hemMonthly";
      }

      //'true' (is provisional data) if provInd = *, 'false' (not provisional data) if provInd = anything else
      provBool = provInd.equals("*");

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

    public double getSunspotNum() {
        return sunspotNum;
    }
    public double getnSunspotNum() {
        return nSunspotNum;
    }
    public double getsSunspotNum() {
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

    public boolean isProv() {
        return provBool;
    }

    //toString
    public String toString(){
        String returnString = "";
        returnString+=getYrAsDec()+":\n";
        if(year != -1) {
            returnString += "\t(" + year;
            if (month != -1) {
                returnString += "/" + getMonth();

                if (day != -1) {
                    returnString += "/" + day;
                }
            }
            returnString+=")\n";
        }
        if(sunspotNum != -1) {
            returnString += "\tMean sunspot number: " + sunspotNum;
            if(nSunspotNum != -1){
                returnString += "\n\t\tNorth mean sunspot number: "+nSunspotNum;
            }
            if (sSunspotNum != -1) {
                returnString += "\n\t\tSouth mean sunspot number: " + sSunspotNum;
            }
            returnString += "\n";
        }
        if(standDev != -1) {
            returnString += "\tMean standard deviation: " + standDev;
            if(nStandDev != -1){
                returnString += "\n\t\tNorth mean standard deviation: "+nStandDev;
            }
            if (sStandDev != -1) {
                returnString += "\n\t\tSouth mean standard deviation: " + sStandDev;
            }
            returnString += "\n";
        }
        if(numObs != -1) {
            returnString += "\tNumber of observations: " + numObs;
            if(nSunspotNum != -1){
                returnString += "\n\t\tNorth number of observations: "+nNumObs;
            }
            if (sSunspotNum != -1) {
                returnString += "\n\t\tSouth number of observations: " + sNumObs;
            }
            returnString += "\n";
        }
        returnString+="\tProvisional: "+provBool;
        returnString+="\n\tType: "+type;
        return returnString;
    }

}
