package demo1;

public class Student {
    private String name;
    private long stunumber;
    public Student(){

    }

    public Student(String name, long stunumber) {
        this.name = name;
        this.stunumber = stunumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStunumber() {
        return stunumber;
    }

    public void setStunumber(long stunumber) {
        this.stunumber = stunumber;
    }
}
