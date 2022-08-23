package javaProject;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class majorAvg extends JFrame{   // 과목별 학과평균 그래프 GUI
   public static Panel majorAvg_p = new Panel();

   private JCheckBox[] subs = new JCheckBox[4];
   private String[] sub = {"java프로그래밍", "소프트웨어분석설계", "인터넷기초", "컴퓨터구조"};

   public majorAvg () {
      majorAvg_p.setLayout(new BorderLayout());
      
      // 과목별 그래프 그리는 클래스 선언
      javaPanel javaPanel = new javaPanel();
      softPanel softPanel = new softPanel();
      internetPanel internetPanel = new internetPanel();
      computerPanel computerPanel = new computerPanel();
      
      // 그래프 비활성화
      javaPanel.setVisible(false);
      softPanel.setVisible(false);
      internetPanel.setVisible(false);
      computerPanel.setVisible(false);
      
      Panel panel = new Panel();   // 체크박스 붙이는 패널 생성
      panel.setLayout(new GridLayout(4,1));
      for(int i=0; i<sub.length; i++) {
         subs[i] = new JCheckBox(sub[i]);   // 과목별 체크박스 생성
         subs[i].setBorderPainted(true);
         panel.add(subs[i]);   // 패널에 부착
         
         // 체크박스 리스너
         subs[i].addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
               boolean b = true;
               if(e.getStateChange() == ItemEvent.SELECTED) {   // 선택 되었을 떄
                  if(e.getItem()==subs[0])   // java프로그래밍 버튼을 클릭했을 떄
                     javaPanel.setVisible(b);
                  else if(e.getItem() == subs[1])   // 소프트웨어분석설계 버튼을 클릭했을 떄
                     softPanel.setVisible(b);
                  else if(e.getItem() == subs[2])   // 인터넷기초 버튼을 클릭했을 때
                     internetPanel.setVisible(b);
                  else if(e.getItem() == subs[3])   // 컴퓨터구조 버튼을 클릭했을 떄
                     computerPanel.setVisible(b);
               }
               else {   // 선택되지 않았을 떄
                  if(e.getItem()==subs[0])   // java프로그래밍 버튼을 클릭했을 떄
                     javaPanel.setVisible(!b);
                  else if(e.getItem() == subs[1])  // 소프트웨어분석설계 버튼을 클릭했을 떄
                     softPanel.setVisible(!b);
                  else if(e.getItem() == subs[2])  // 인터넷기초 버튼을 클릭했을 때
                     internetPanel.setVisible(!b);
                  else if(e.getItem() == subs[3])  // 컴퓨터구조 버튼을 클릭했을 떄
                     computerPanel.setVisible(!b);
               }
            }
         });
         
      }
      
      // 과목별 그래프 패널에 합체
      Panel box = new Panel(new GridLayout(4,1));
      box.add(javaPanel);
      box.add(softPanel);
      box.add(internetPanel);
      box.add(computerPanel);
      
      Box b = new Box(BoxLayout.X_AXIS);
      JLabel la = new JLabel("과목별 학과 평균 그래프  "+ "    ");
      la.setFont(new Font("Gothic", Font.BOLD, 13));
      JLabel s1 = new JLabel("컴퓨터학과  ");
      s1.setForeground(new Color(0xF1, 0x5F, 0x5F));
      JLabel s2 = new JLabel("IT미디어공학과  ");
      s2.setForeground(new Color(0xF2, 0x96, 0x61));
      JLabel s3 = new JLabel("바이오공학과  ");
      s3.setForeground(new Color(0xF2, 0xCB, 0x61));
      b.add(la);
      b.add(s1);
      b.add(s2);
      b.add(s3);

      majorAvg_p.add(b, BorderLayout.NORTH);
      majorAvg_p.add(panel, BorderLayout.WEST);
      majorAvg_p.add(box, BorderLayout.CENTER);
   }


   // 자바 그래프 그리기
   class javaPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;         // 전공 저장 벡터
         Vector<String> java_s = studentFile.javas;         // java프로그래밍 점수 저장 벡터
         Vector<Integer> cmajor = new Vector<Integer>();    // 컴퓨터학과 학생들의 인덱스
         Vector<Integer> imajor = new Vector<Integer>();    // IT미디어공학과 학생들의 인덱스
         Vector<Integer> bmajor = new Vector<Integer>();    // 바이오공학과 학생들의 인덱스
         int javaC = 0, javaI = 0, javaB = 0;
         
         g.drawLine(30,0,30,63); //y축
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // 학과별 인덱스 저장
            if(major.get(i).equals("컴퓨터학과"))   cmajor.add(i);
            else if(major.get(i).equals("IT미디어공학과")) imajor.add(i);
            else if(major.get(i).equals("바이오공학과")) bmajor.add(i);   
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // 학과별 성적 저장
            if(cmajor.contains(i)) javaC += Integer.parseInt(java_s.get(i)); 
            else if(imajor.contains(i)) javaI += Integer.parseInt(java_s.get(i)); 
            else if(bmajor.contains(i)) javaB += Integer.parseInt(java_s.get(i));
         }
         
         int javaAvg[] = {javaC / cmajor.size(), javaI / imajor.size(), javaB / bmajor.size()}; 
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // 컴퓨터학과 막대그래프
         g.fillRect(31, 7, javaAvg[0]*3, 10);
         g.drawString(Integer.toString(javaAvg[0])+"점",javaAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT미디어공학과 막대그래프
         g.fillRect(31, 20, javaAvg[1]*3, 10);
         g.drawString(Integer.toString(javaAvg[1])+"점",javaAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // 바이오공학과 막대그래프
         g.fillRect(31, 33, javaAvg[2]*3, 10);
         g.drawString(Integer.toString(javaAvg[2])+"점",javaAvg[2]*3 + 45 , 42);
      }
   }
   // 소분설 그래프 그리기
   class softPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;         // 전공 저장 벡터
         Vector<String> soft_s = studentFile.softs;         // 소프트웨어분석설계 점수 저장 벡터
         Vector<Integer> cmajor = new Vector<Integer>();    // 컴퓨터학과 학생들의 인덱스
         Vector<Integer> imajor = new Vector<Integer>();    // IT미디어공학과 학생들의 인덱스
         Vector<Integer> bmajor = new Vector<Integer>();    // 바이오공학과 학생들의 인덱스
         int softC = 0, softI = 0, softB = 0;
         
         g.drawLine(30,0,30,64); //y축
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // 학과별 인덱스 저장
            if(major.get(i).equals("컴퓨터학과"))   cmajor.add(i);
            else if(major.get(i).equals("IT미디어공학과")) imajor.add(i);
            else if(major.get(i).equals("바이오공학과")) bmajor.add(i);   
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // 학과별 성적 저장
            if(cmajor.contains(i)) softC += Integer.parseInt(soft_s.get(i)); 
            else if(imajor.contains(i)) softI += Integer.parseInt(soft_s.get(i)); 
            else if(bmajor.contains(i)) softB += Integer.parseInt(soft_s.get(i));
         }
         
         int softAvg[] = {softC / cmajor.size(), softI / imajor.size(), softB / bmajor.size()}; 
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // 컴퓨터학과 막대그래프
         g.fillRect(31, 7, softAvg[0]*3, 10);
         g.drawString(Integer.toString(softAvg[0])+"점",softAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT미디어공학과 막대그래프
         g.fillRect(31, 20, softAvg[1]*3, 10);
         g.drawString(Integer.toString(softAvg[1])+"점",softAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // 바이오공학과 막대그래프
         g.fillRect(31, 33, softAvg[2]*3, 10);
         g.drawString(Integer.toString(softAvg[2])+"점",softAvg[2]*3 + 45 , 42);
      }
   }
   //인기초 그래프 그리기
   class internetPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;          // 전공 저장 벡터
         Vector<String> internet_s = studentFile.internets;  // 인터넷기초 점수 저장 벡터
         Vector<Integer> cmajor = new Vector<Integer>();     // 컴퓨터학과 학생들의 인덱스
         Vector<Integer> imajor = new Vector<Integer>();     // IT미디어공학과 학생들의 인덱스
         Vector<Integer> bmajor = new Vector<Integer>();     // 바이오공학과 학생들의 인덱스
         int internetC = 0, internetI = 0, internetB = 0;
         
         g.drawLine(30,0,30,64); //y축
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // 학과별 인덱스 저장
            if(major.get(i).equals("컴퓨터학과"))   cmajor.add(i);
            else if(major.get(i).equals("IT미디어공학과")) imajor.add(i);
            else if(major.get(i).equals("바이오공학과")) bmajor.add(i);   
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // 학과별 성적 저장
            if(cmajor.contains(i)) internetC += Integer.parseInt(internet_s.get(i)); 
            else if(imajor.contains(i)) internetI += Integer.parseInt(internet_s.get(i)); 
            else if(bmajor.contains(i)) internetB += Integer.parseInt(internet_s.get(i));
         }
         
         int internetAvg[] = {internetC / cmajor.size(), internetI / imajor.size(), internetB / bmajor.size()};
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // 컴퓨터학과 막대그래프
         g.fillRect(31, 7, internetAvg[0]*3, 10);
         g.drawString(Integer.toString(internetAvg[0])+"점",internetAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT미디어공학과 막대그래프
         g.fillRect(31, 20, internetAvg[1]*3, 10);
         g.drawString(Integer.toString(internetAvg[1])+"점",internetAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // 바이오공학과 막대그래프
         g.fillRect(31, 33, internetAvg[2]*3, 10);
         g.drawString(Integer.toString(internetAvg[2])+"점",internetAvg[2]*3 + 45 , 42);
      }
   }
   // 컴구 그래프 그리기
   class computerPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;          // 전공 저장 벡터
         Vector<String> computer_s = studentFile.computers;  // 컴퓨터구조 점수 저장 벡터
         Vector<Integer> cmajor = new Vector<Integer>();     // 컴퓨터학과 학생들의 인덱스
         Vector<Integer> imajor = new Vector<Integer>();     // IT미디어공학과 학생들의 인덱스
         Vector<Integer> bmajor = new Vector<Integer>();     // 바이오공학과 학생들의 인덱스
         int computerC = 0, computerI = 0, computerB = 0;
         
         g.drawLine(30,0,30,50); //y축
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // 학과별 인덱스 저장
            if(major.get(i).equals("컴퓨터학과"))   cmajor.add(i);
            else if(major.get(i).equals("IT미디어공학과")) imajor.add(i);
            else if(major.get(i).equals("바이오공학과")) bmajor.add(i);  
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // 학과별 성적 저장
            if(cmajor.contains(i)) computerC += Integer.parseInt(computer_s.get(i)); 
            else if(imajor.contains(i)) computerI += Integer.parseInt(computer_s.get(i)); 
            else if(bmajor.contains(i)) computerB += Integer.parseInt(computer_s.get(i));
         }
         
         // 그래프 그리기
         int computerAvg[] = {computerC / cmajor.size(), computerI / imajor.size(), computerB / bmajor.size()};
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // 컴퓨터학과 막대그래프
         g.fillRect(31, 7, computerAvg[0]*3, 10);
         g.drawString(Integer.toString(computerAvg[0])+"점",computerAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT미디어공학과 막대그래프
         g.fillRect(31, 20, computerAvg[1]*3, 10);
         g.drawString(Integer.toString(computerAvg[1])+"점",computerAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // 바이오공학과 막대그래프
         g.fillRect(31, 33, computerAvg[2]*3, 10);
         g.drawString(Integer.toString(computerAvg[2])+"점",computerAvg[2]*3 + 45 , 42);
      }
   }
}