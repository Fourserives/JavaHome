package dao;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.ms.System.Exception;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class World {
    //学生姓名
    String name;
    //学生学号
    String ID;
    //成绩
    int score;
    //实验文档
    Document document;
    //导入文档
    //有修改
    public void loadWorld(Search SL,Student student){
        document = new Document();
        //加载WPS文字文档
        document.loadFromFile("D:\\大作业\\测试文档\\实验报告"+"\\"+SL.s1+"\\"+SL.s2+"\\"+SL.t+"+"+student.name+".docx", FileFormat.WPS);
    }

    //获取文档学生信息;
    public void getMessage() throws IOException {
        //调用txt文件
        String fileName = "C:\\Users\\Administrator\\IdeaProjects\\BHwkTest\\BHwkTest01.txt";
        //选择要读取的行数
            //获取第十七行 — 姓名
        name = readPointLIne(fileName, 17);
            //获取第二十一行 — 学号
        ID = readPointLIne(fileName, 21);
            //获取第四十五行 — 教师签名

            //获取第四十七行 — 成绩

    }

    //检查文件名与文档信息是否匹配
    public boolean check(Student student) throws IOException {
        //判断
        if (!student.name.equals(name)){
            changeMessage(student,name,17);
        }
        if (!student.ID.equals(ID)){
            changeMessage(student,ID,21);
        }
        //判断结束后才能使用查询成绩的方法(getScore)
        return false;
    }

    //获取成绩
    public int getScore(String sc) {
        this.score = Integer.parseInt(sc);
        return score;
    }

    //实现签名
    public void print(String str){

    }
    //Word文档内容转成txt文档
    public static void writeStringToTxt(String content, String txtFileName) throws IOException {

        FileWriter fWriter= new FileWriter(txtFileName,true);
        try {
            fWriter.write(content);
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            try{
                fWriter.flush();
                fWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    //遍历txt文档的每一行内容
    public static String readPointLIne(String fileName,int readLine) throws IOException{
        String line;//读取每行的内容
        try (
                BufferedReader br = Files.newBufferedReader(Paths.get(fileName))){
            int i=0;
            //每次读取一行，一行一行的读取 br.readLine()
            while ((line = br.readLine()) != null) {
                i++;
                if(i==readLine){
                    return line;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //修改对应行的内容
    public void changeMessage(Student stu,String s,int i) throws IOException {
        int modifyline=2;//要修改的行
        BufferedReader in=new BufferedReader(new FileReader("BHwkTest01.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ChangeTest01.txt")));
        String line;
        int c1=i;
        while((line=in.readLine())!=null){
            if(c1==modifyline){
                out.println(line.replace(stu.name,s));  //替换abc成def
            } else if(c1==modifyline){
                out.println(line.replace(stu.ID,s));
            }else{
                out.println(line);
            }
            c1++;
        }
        in.close();
        out.close();
    }
}
