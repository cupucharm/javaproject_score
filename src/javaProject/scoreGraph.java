package javaProject;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Vector;

public class scoreGraph extends JFrame{ // ���� ��� ���� �׷��� GUI
   public static Panel scoreGraph_p = new Panel();
   public static int averageJava, averageSoft, averageInternet, averageComputer;

   public static Vector<String> scores = new Vector<String>();          // java���α׷��� ���� ���� ����

   public static Vector<String> javaScores = new Vector<String>();          // java���α׷��� ���� ���� ����
   public static Vector<String> softScores = new Vector<String>();          // ����Ʈ����м����� ���� ���� ����
   public static Vector<String> internetScores = new Vector<String>();       // ���ͳݱ��� ���� ���� ����
   public static Vector<String> computerScores = new Vector<String>();       // ��ǻ�ͱ��� ���� ���� ����

   public scoreGraph() {
      scoreGraph_p.setLayout(new BorderLayout());

      DrawingPanel drawingPanel = new DrawingPanel(); //���� �׷����� �׸� Panel
      JLabel la = new JLabel("     ���� �� ��� �׷���");
      la.setFont(new Font("Gothic", Font.BOLD, 13));
      scoreGraph_p.add(la, BorderLayout.NORTH);
      scoreGraph_p.add(drawingPanel, BorderLayout.CENTER);

      String javaScore, softScore, internetScore, computerScore;

      try {
         BufferedReader in = new BufferedReader(new FileReader("src/javaProject/�л�����.txt"));

         String score;
         String s;
         while((s = in.readLine()) != null) {
            score = s;      // �� �� �� �о ������ ����
            scores.add(score);   // ���Ϳ� ����

            String[] split = s.split(" ");   // " "�� �������� �ɰ�

            //������ ����
            javaScore = split[4];
            softScore = split[5];
            internetScore = split[6];
            computerScore = split[7];

            //���Ϳ� ����
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
         g.drawLine(50,220,550,220); // x��
         g.drawLine(50,10,50,220); // y��

         for(int i=1; i<=10; i++) {
            g.drawString(i * 10 +"", 25, 225 - 20 * i); // �׷��� �� ���� ǥ��
            g.drawLine(50, 220 - 20 * i, 550, 220 - 20 * i);
         }
         
         g.setFont(new Font("Gothic", Font.BOLD, 10));
         g.drawString("java���α׷���",55,240);
         g.drawString("����Ʈ����м�����",138,240);
         g.drawString("���ͳݱ���",253,240);
         g.drawString("��ǻ�ͱ���",330,240);

         int javaSum = 0;
         int softSum = 0;
         int internetSum = 0;
         int computerSum = 0;

         for(int i=0; i<scores.size(); i++) {
            int javaEle = Integer.parseInt(javaScores.get(i));
            int softEle = Integer.parseInt(softScores.get(i));
            int internetEle = Integer.parseInt(internetScores.get(i));
            int computerEle = Integer.parseInt(computerScores.get(i));

            javaSum += javaEle;				// �ڹ� ������ ��
            softSum += softEle;				// �Һм� ������ ��
            internetSum += internetEle;		// �α��� ������ ��
            computerSum += computerEle;		// �ı� ������ ��
         }

         averageJava = javaSum / javaScores.size();				// �ڹ� ������ ���
         averageSoft = softSum / softScores.size();				// �Һм� ������ ���
         averageInternet = internetSum / internetScores.size();	// �α��� ������ ���
         averageComputer = computerSum / computerScores.size();	// �ı� ������ ���
         
         g.setFont(new Font("Gothic", Font.BOLD, 10));	// �� ���� �׷��� �� ��� ���� ���ڷ� ���
         g.setColor(new Color(0x5C, 0xD1, 0xE5));
         g.drawString(Integer.toString(averageJava)+"��",81,255);
         g.setColor(new Color(0x66, 0x99, 0xFF));
         g.drawString(Integer.toString(averageSoft)+"��",174,255);
         g.setColor(new Color(0x6B, 0x66, 0xFF));
         g.drawString(Integer.toString(averageInternet)+"��",264,255);
         g.setColor(new Color(0xA3, 0x66, 0xFF));
         g.drawString(Integer.toString(averageComputer)+"��",347,255);

         int aJ = averageJava;
         int aS = averageSoft;
         int aI = averageInternet;
         int aC = averageComputer;

         if (aJ>0) {
            g.setColor(new Color(0x5C, 0xD1, 0xE5)); //�ڹ� ����׷���
            g.fillRect(77, 220-aJ*2, 33, aJ*2);
         } if (aS>0) {
            g.setColor(new Color(0x66, 0x99, 0xFF)); //�Һм� ����׷���
            g.fillRect(169, 220-aS*2, 33, aS*2);
         } if (aI>0) {
            g.setColor(new Color(0x6B, 0x66, 0xFF)); //�α��� ���� �׷���
            g.fillRect(259, 220-aI*2, 33, aI*2);
         } if (aC>0) {
            g.setColor(new Color(0xA3, 0x66, 0xFF)); //�ı� ����׷���
            g.fillRect(340, 220-aC*2, 33, aC*2);
         }
      }
   }
}