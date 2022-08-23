package javaProject;

//20171061 �ּ���, 20171056 �۹ο�, 20171069 ������ �Դϴ�.

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Main extends JFrame{
   private static javaGui javaGui_c = new javaGui();               		// ����â GUI
   private static stuAddDel stuAddDel_c = new stuAddDel();            	// �л� �߰� ���� GUI
   private static scoreGraph scoreGraph_c = new scoreGraph();         	// ����׷��� GUI
   private static scorePieChart scorePieChart_c = new scorePieChart();  // ������Ʈ�׷��� GUI
   private static majorAvg majorAvg_c = new majorAvg();
   
   public static int s;
   public Main() {
      setTitle("������ ���� ó�� ���α׷�");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane();
      setLayout(null);
      
      // ���� ũ�� Ȯ�� ��� �����̴�
      JSlider slider = new JSlider(JSlider.VERTICAL,0,10,5);
      slider.setPaintLabels(true);
      slider.setPaintTicks(true);
      slider.setPaintTrack(true);
      slider.setMajorTickSpacing(10);
      slider.setMinorTickSpacing(5);
      slider.addChangeListener(new ChangeListener() {   // �����̴� ������
        public void stateChanged(ChangeEvent e) {
           s = slider.getValue();
           stuAddDel_c.table.setFont(new Font("Gothic", Font.PLAIN, 12+(Main.s-5)));   // ���̺� ���� ũ�� ����
        }
      });
      
      slider.setSize(50,230);
      slider.setLocation(10,20);
      add(slider);
      
      // ����â GUI
      javaGui_c.javaGui_p.setSize(395,245);
      javaGui_c.javaGui_p.setLocation(80,10);
      add(javaGui_c.javaGui_p); 
      // ���� �а� ��� ����׷��� GUI
      majorAvg_c.majorAvg_p.setSize(500,250);
      majorAvg_c.majorAvg_p.setLocation(520,10);
      add(majorAvg_c.majorAvg_p);   
      // �л� �߰� ���� GUI
      stuAddDel_c.stuAddDel_p.setSize(995,470);
      stuAddDel_c.stuAddDel_p.setLocation(10,280);
      add(stuAddDel_c.stuAddDel_p);   
      // ����׷��� GUI
      scoreGraph_c.scoreGraph_p.setSize(395,280);
      scoreGraph_c.scoreGraph_p.setLocation(1020,10);
      add(scoreGraph_c.scoreGraph_p);   
      // ������Ʈ GUI
      scorePieChart_c.scorePieChart_p.setSize(500,500);
      scorePieChart_c.scorePieChart_p.setLocation(1040,295);
      add(scorePieChart_c.scorePieChart_p);   

      setSize(1500,820);
      setVisible(true);
   }

   public static void main(String[] args) {
      new studentFile();
      new Main();
   }
}