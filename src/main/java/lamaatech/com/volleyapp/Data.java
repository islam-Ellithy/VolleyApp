package lamaatech.com.volleyapp;

/**
 * Created by Developer on 2/16/2018.
 */

public class Data {

    private Double qmag;
    private String qplace;
    private  long  qtime;


    public Data(Double mag, String place, long time){
        this.qmag = mag;
        this.qplace = place;
        this.qtime = time;
    }

    public Double getQmag() {
        return qmag;
    }

    public String getQplace() {
        return qplace;
    }

    public long getQtime() {
        return qtime;
    }
}
