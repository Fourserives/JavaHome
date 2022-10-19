package dao;

import java.io.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class Modifytxt{
    public static void main(String args[])throws IOException {
        int m1 = 1;
        int m2 = 2;
        //    int modifyline=2;//要修改的行
    BufferedReader in=new BufferedReader(new FileReader("D:\\大作业\\233.txt"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\大作业\\244.txt")));
    String line;
    int count=1;
    while((line=in.readLine())!=null){
        if (count==m1){
            out.println(line.replace("abc","233"));
        }else if(count==m2){
            out.println(line.replace("def","666"));
        }else{
            out.println(line);
        }
        count++;
    }
    in.close();
    out.close();
    }
}