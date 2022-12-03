package dao;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.ms.System.Exception;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class World {
    //学生姓名
    String name;
    //学生学号
    String ID;
    //成绩
    String score;
    //实验文档
    Document document;
    //异常文档类
    ThrowLog log;
    //标记
    int k;
    int t = 0;
    int[] flag;
    //存储路径地址
    String address;
    File[] array;

    //遍历该班该实验下的所有文档
    public void searchAll(String str,Search SL){
        this.address = str+SL.s1+"\\"+SL.s2;
        this.flag = new int[50];
        File file = new File(address);
        array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile())//如果是文件
            {
                // 只输出文件名字
                array[i].getName();
            }
        }
    }

    //导入文档
    public void loadWorld(Search SL,String str,Excel excel) throws IOException {
        //记录当前文档对应学生在学生数组里的下标
        k = 0;
        document = new Document();
        //利用正则表达式判断文档名字是否符合要求
        String nameregex="[1-9]{1}\\+[\u4e00-\u9fa5]{2,4}\\.[a-z]{4}";
        String regx = "[\u4e00-\u9fa5]{2,4}";
        String text = null;
        Pattern pattern = Pattern.compile(nameregex);
        Pattern pattern1 = Pattern.compile(regx);
        Matcher isNum = pattern.matcher(str);
        Matcher Name = pattern1.matcher(str);
        while(Name.find()){
            text = Name.group();
        }
        //第一次检验：检查文件名与文档信息是否匹配
        k = excel.getStudent(text);
//        System.out.print("需要查找的字符串:"+text+" ");
        flag[t++] = k;
        if(!isNum.matches()){
            excel.student[k].flag++;
            log.input(excel.student[k]);
        }
        document.loadFromFile(address + "\\" +str, FileFormat.WPS);
        getMessage();
        //第二次检验：检测学生world文档信息是否有误并记录
        check(excel.student[k]);
        //将成绩录入到对应的学生类属性中
        excel.inputStudent(k,getScore());
 }

    //获取文档学生信息;
    public void getMessage() throws IOException {
        //1.1 将Word获取的文本内容转成新的txt文件
        writeStringToTxt(document.getText(),"EndTest01.txt");
        //调用txt文件(默认路径)
        String fileName = "C:\\Users\\Administrator\\IdeaProjects\\BHwkTest\\EndTest01.txt";

        //1.2 选择要读取的行数
        //获取第十七行 — 姓名
        name = readPointLIne(fileName, 17);
        //获取第二十一行 — 学号
        ID = readPointLIne(fileName, 21);
        //获取第四十七行 — 成绩
        score = readPointLIne(fileName,47);
    }


    public boolean check(Student stu) throws IOException {
        //判断
//        System.out.println(name);
//        System.out.println(stu.name+" "+name);
//        System.out.println(stu.name.equals(name)+" "+stu.ID.equals(ID));
        if (!stu.name.equals(name)||!stu.ID.equals(ID)) {
//            System.out.println(stu.name);
            stu.flag++;
            log.input(stu);
            return true;
        }
        return false;
    }

    //获取成绩
    public String getScore() {
        return score;
    }

    //实现签名
    public void print(Search SL, Student student, String str) {
        //匹配以$开头并用Spire.Doc替换
        Pattern c = Pattern.compile("[$]");
        document.loadFromFile(address + "\\" +SL.t + "+" + student.name + ".docx", FileFormat.WPS);
        document.replace(c,str);
        //保存文档
        document.saveToFile(address + "\\" + SL.t + "+" + student.name + ".docx", FileFormat.Docx_2013);
    }

    //添加异常信息存储
    public void addExceptionExcel(String str){
        log = new ThrowLog(str);
    }
    //Word文档内容转成txt文档
    public static void writeStringToTxt(String content, String txtFileName) throws IOException {

        FileWriter fWriter = new FileWriter(txtFileName, false);
        try {
            fWriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fWriter.flush();
                fWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //遍历txt文档的每一行内容
    public static String readPointLIne(String fileName, int readLine) throws IOException {
        String line;//读取每行的内容
        try (
                BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            int i = 0;
            //每次读取一行，一行一行的读取 br.readLine()
            while ((line = br.readLine()) != null) {
                i++;
                if (i == readLine) {
                    return line;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
