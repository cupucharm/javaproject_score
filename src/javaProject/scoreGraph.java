package javaProject;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Vector;

public class scoreGraph extends JFrame{ // 과목별 평균 막대 그래프 GUI
   public static Panel scoreGraph_p = new Panel();
   public static int averageJava, averageSoft, averageInternet, averageComputer;

   public static Vector<String> scores = new Vector<String>();          // java프로그래밍 점수 저장 벡터

   public static Vector<String> javaScores = new Vector<String>();          // java프로그래밍 점수 저장 벡터
   public static Vector<String> softScores = new Vector<String>();          // 소프트웨어분석설계 점수 저장 벡터
   public static Vector<String> internetScores = new Vector<String>();       // 인터넷기초 점수 저장 벡터
   public static Vector<String> computerScores = new Vector<String>();       // 컴퓨터구조 점수 저장 벡터

   public scoreGraph() {
      scoreGraph_p.setLayout(new BorderLayout());

      DrawingPanel drawingPanel = new DrawingPanel(); //막대 그래프를 그린 Panel
      JLabel la = new JLabel("     과목 별 평균 그래프");
      la.setFont(new Font("Gothic", Font.BOLD, 13));
      scoreGraph_p.add(la, BorderLayout.NORTH);
      scoreGraph_p.add(drawingPanel, BorderLayout.CENTER);

      String javaScore, softScore, internetScore, computerScore;

      try {
         BufferedReader in = new BufferedReader(new FileReader("src/javaProject/학생정보.txt"));

         String score;
         String s;
         while((s = in.readLine()) != null) {
            score = s;      // 한 줄 씩 읽어서 변수에 저장
            scores.add(score);   // 벡터에 저장

            String[] split = s.split(" ");   // " "를 기준으로 쪼갬

            //변수에 저장
            javaScore = split[4];
            softScore = split[5];
            internetScore = split[6];
            computerScore = split[7];

            //벡터에 저장
            javaScores.add(javaScore);
            softScores.add(softScore);
            internetScores.add(internetScore);
            computerScores.add(computerScore);
         }
         in.close();

      }catch(IOException e){
         System.out.println("File not found");
      }
   }

   class DrawingPanel extends JPanel{
      public void paint(Graphics g){
         g.clearRect(50,50,getWidth(),getHeight());
         g.drawLine(50,220,550,220); // x축
         g.drawLine(50,10,50,220); // y축

         for(int i=1; i<=10; i++) {
            g.drawString(i * 10 +"", 25, 225 - 20 * i); // 그래프 옆 숫자 표시
            g.drawLine(50, 220 - 20 * i, 550, 220 - 20 * i);
         }
         
         g.setFont(new Font("Gothic", Font.BOLD, 10));
         g.drawString("java프로그래밍",55,240);
         g.drawString("소프트웨어분석설계",138,240);
         g.drawString("인터넷기초",253,240);
         g.drawString("컴퓨터구조",330,240);

         int javaSum = 0;
         int softSum = 0;
         int internetSum = 0;
         int computerSum = 0;

         for(int i=0; i<scores.size(); i++) {
            int javaEle = Integer.parseInt(javaScores.get(i));
            int softEle = Integer.parseInt(softScores.get(i));
            int internetEle = Integer.parseInt(internetScores.get(i));
            int computerEle = Integer.parseInt(computerScores.get(i));

            javaSum += javaEle;				// 자바 성적의 합
            softSum += softEle;				// 소분설 성적의 합
            internetSum += internetEle;		// 인기초 성적의 합
            computerSum += computerEle;		// 컴구 성적의 합
         }

         averageJava = javaSum / javaScores.size();				// 자바 성적의 평균
         averageSoft = softSum / softScores.size();				// 소분설 성적의 평균
         averageInternet = internetSum / internetScores.size();	// 인기초 성적의 평균
         averageComputer = computerSum / computerScores.size();	// 컴구 성적의 평균
         
         g.setFont(new Font("Gothic", Font.BOLD, 10));	// 각 막대 그래프 별 평균 성적 글자로 출력
         g.setColor(new Color(0x5C, 0xD1, 0xE5));
         g.drawString(Integer.toString(averageJava)+"점",81,255);
         g.setColor(new Color(0x66, 0x99, 0xFF));
         g.drawString(Integer.toString(averageSoft)+"점",174,255);
         g.setColor(new Color(0x6B, 0x66, 0xFF));
         g.drawString(Integer.toString(averageInternet)+"점",264,255);
         g.setColor(new Color(0xA3, 0x66, 0xFF));
         g.drawString(Integer.toString(averageComputer)+"점",347,255);

         int aJ = averageJava;
         int aS = averageSoft;
         int aI = averageInternet;
         int aC = averageComputer;

         if (aJ>0) {
            g.setColor(new Color(0x5C, 0xD1, 0xE5)); //자바 막대그래프
            g.fillRect(77, 220-aJ*2, 33, aJ*2);
         } if (aS>0) {
            g.setColor(new Color(0x66, 0x99, 0xFF)); //소분설 막대그래프
            g.fillRect(169, 220-aS*2, 33, aS*2);
         } if (aI>0) {
            g.setColor(new Color(0x6B, 0x66, 0xFF)); //인기초 막대 그래프
            g.fillRect(259, 220-aI*2, 33, aI*2);
         } if (aC>0) {
            g.setColor(new Color(0xA3, 0x66, 0xFF)); //컴구 막대그래프
            g.fillRect(340, 220-aC*2, 33, aC*2);
         }
      }
   }
}