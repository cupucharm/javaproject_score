package javaProject;

import java.io.*;
import java.util.*;

public class studentFile { // 학생들 파일IO
   public static Vector<String> studentIndexs = new Vector<String>();   // 학생 정보 벡터
   
   public static Vector<String> names = new Vector<String>();      // 이름 저장 벡터
   public static Vector<String> majors = new Vector<String>();     // 전공 저장 벡터
   public static Vector<String> ids = new Vector<String>();        // 학번 저장 벡터
   public static Vector<String> phones = new Vector<String>();     // 전화번호 저장 벡터
   
   public static Vector<String> javas = new Vector<String>();      // java프로그래밍 점수 저장 벡터
   public static Vector<String> softs = new Vector<String>();      // 소프트웨어분석설계 점수 저장 벡터
   public static Vector<String> internets = new Vector<String>();  // 인터넷기초 점수 저장 벡터
   public static Vector<String> computers = new Vector<String>();  // 컴퓨터구조 점수 저장 벡터
   
   public studentFile() {
      String name, major, id, phone, java, soft, internet, computer;
      try {
         BufferedReader in = new BufferedReader(new FileReader("src/javaProject/학생정보.txt")); // 경로에서 파일 읽어오기
         PrintWriter pw = new PrintWriter("src/javaProject/grade.dat");	// grade.dat으로 쓰기
         
         String studentIndex;
         String s;
         
         while((s = in.readLine()) != null) {
            studentIndex = s;      // 한 줄 씩 읽어서 변수에 저장
            studentIndexs.add(studentIndex);   // 벡터에 저장
            
            String[] split = s.split(" ");   // " "를 기준으로 쪼갬
            
            // 변수에 저장
            major = split[0];
            id = split[1];
            name = split[2];
            phone = split[3];
            java = split[4];
            soft = split[5];
            internet = split[6];
            computer = split[7];

            pw.println(major+" "+id+" "+name+" "+phone+" "+java+" "+soft+" "+internet+" "+computer);   // 파일에 입력
            
            // 벡터에 저장
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