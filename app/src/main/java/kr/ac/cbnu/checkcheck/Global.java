package kr.ac.cbnu.checkcheck;



public class Global {
    private static Global ourInstance = new Global();
    public static Global getInstance(){
        return ourInstance;
    }
    private Global(){
        classnumber = 1;
    }

    private int profnumber;
    private String profname;

    private int classnumber;

    private int attend_date;
    private String attend_date_string;


    public int getProfnumber() {
        return profnumber;
    }

    public void setProfnumber(int profnumber) {
        this.profnumber = profnumber;
    }

    public String getProfname() {
        return profname;
    }

    public void setProfname(String profname) {
        this.profname = profname;
    }

    public int getClassnumber() {
        return classnumber;
    }

    public String getClassnumberString() {
        return Integer.toString(classnumber);
    }

    public void setClassnumber(int classnumber) {
        this.classnumber = classnumber;
    }

    public int getAttend_date() {
        return attend_date;
    }

    public void setAttend_date(int attend_date) {
        this.attend_date = attend_date;
        switch (attend_date){
            case 1 : attend_date_string = "1st"; break;
            case 2 : attend_date_string = "2nd"; break;
            case 3 : attend_date_string = "3rd"; break;
            case 4 : attend_date_string = "4th"; break;
            case 5 : attend_date_string = "5th"; break;
            case 6 : attend_date_string = "6th"; break;
            case 7 : attend_date_string = "7th"; break;
            case 8 : attend_date_string = "8th"; break;
            case 9 : attend_date_string = "9th"; break;
            case 10 : attend_date_string = "10th"; break;
            case 11 : attend_date_string = "11th"; break;
            case 12 : attend_date_string = "12th"; break;
            case 13 : attend_date_string = "13th"; break;
            case 14 : attend_date_string = "14th"; break;
            case 15 : attend_date_string = "15th"; break;
            case 16 : attend_date_string = "16th"; break;
        }
    }


    public String getAttend_date_string() {
        return attend_date_string;
    }

    public void setAttend_date_string(String attend_date_string) {
        this.attend_date_string = attend_date_string;
    }
}
