package javaProject;

import java.io.*;
import java.util.*;

public class studentFile { // �л��� ����IO
   public static Vector<String> studentIndexs = new Vector<String>();   // �л� ���� ����
   
   public static Vector<String> names = new Vector<String>();      // �̸� ���� ����
   public static Vector<String> majors = new Vector<String>();     // ���� ���� ����
   public static Vector<String> ids = new Vector<String>();        // �й� ���� ����
   public static Vector<String> phones = new Vector<String>();     // ��ȭ��ȣ ���� ����
   
   public static Vector<String> javas = new Vector<String>();      // java���α׷��� ���� ���� ����
   public static Vector<String> softs = new Vector<String>();      // ����Ʈ����м����� ���� ���� ����
   public static Vector<String> internets = new Vector<String>();  // ���ͳݱ��� ���� ���� ����
   public static Vector<String> computers = new Vector<String>();  // ��ǻ�ͱ��� ���� ���� ����
   
   public studentFile() {
      String name, major, id, phone, java, soft, internet, computer;
      try {
         BufferedReader in = new BufferedReader(new FileReader("src/javaProject/�л�����.txt")); // ��ο��� ���� �о����
         PrintWriter pw = new PrintWriter("src/javaProject/grade.dat");	// grade.dat���� ����
         
         String studentIndex;
         String s;
         
         while((s = in.readLine()) != null) {
            studentIndex = s;      // �� �� �� �о ������ ����
            studentIndexs.add(studentIndex);   // ���Ϳ� ����
            
            String[] split = s.split(" ");   // " "�� �������� �ɰ�
            
            // ������ ����
            major = split[0];
            id = split[1];
            name = split[2];
            phone = split[3];
            java = split[4];
            soft = split[5];
            internet = split[6];
            computer = split[7];

            pw.println(major+" "+id+" "+name+" "+phone+" "+java+" "+soft+" "+internet+" "+computer);   // ���Ͽ� �Է�
            
            // ���Ϳ� ����
            names.add(name);
            majors.add(major);
            ids.add(id);
            phones.add(phone);
            javas.add(java);
            softs.add(soft);
            internets.add(internet);
            computers.add(computer);
         }
         in.close();
         pw.close();
         
      }catch(IOException e){
         System.out.println("File not found");
      }
   }

   

}