package dao;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class Excel {
    //文档
    Workbook book;
    //工作表
    Worksheet sheet;
    //学生信息
    Student[] student;
    int count;
    //导入文档
    public void input(String str){
        book = new Workbook();
//        sheet = new Worksheet();
        book.loadFromFile(str);
        this.count = 0;
        //初始化学生类对象数组
        this.student = new Student[50];
    }

    //切换表格
    public void switchsheet(int s){
        sheet = book.getWorksheets().get(s);
    }

    //遍历学生
    public void studentlist(){
        //用学生类进行存储学生信息
        for (int i = 3; i <100 ; i++) {
            int k = 2;
            if(sheet.get(i,k).getText()!=""){
                String m = sheet.get(i,k).getText();
                k++;
                if(sheet.get(i,k).getText()!=""){
                    String n = sheet.get(i,k).getText();
                    Student stu = new Student(m,n);
                    student[count++] = stu;
//                    count++;
                }
            }
        }
    }



}
