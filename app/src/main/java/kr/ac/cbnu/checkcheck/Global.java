package kr.ac.cbnu.checkcheck;

public class Global {
    private static Global ourInstance = new Global();
    public static Global getInstance(){
        return ourInstance;
    }
    private Global(){ }

    private int subjectNum;

    public int getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(int subjectNum) {
        this.subjectNum = subjectNum;
    }

}
