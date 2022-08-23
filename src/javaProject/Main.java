package javaProject;

//20171061 최수진, 20171056 송민영, 20171069 서예슬 입니다.

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Main extends JFrame{
   private static javaGui javaGui_c = new javaGui();               		// 선택창 GUI
   private static stuAddDel stuAddDel_c = new stuAddDel();            	// 학생 추가 삭제 GUI
   private static scoreGraph scoreGraph_c = new scoreGraph();         	// 막대그래프 GUI
   private static scorePieChart scorePieChart_c = new scorePieChart();  // 파이차트그래프 GUI
   private static majorAvg majorAvg_c = new majorAvg();
   
   public static int s;
   public Main() {
      setTitle("공과대 성적 처리 프로그램");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane();
      setLayout(null);
      
      // 글자 크기 확대 축소 슬라이더
      JSlider slider = new JSlider(JSlider.VERTICAL,0,10,5);
      slider.setPaintLabels(true);
      slider.setPaintTicks(true);
      slider.setPaintTrack(true);
      slider.setMajorTickSpacing(10);
      slider.setMinorTickSpacing(5);
      slider.addChangeListener(new ChangeListener() {   // 슬라이더 리스너
        public void stateChanged(ChangeEvent e) {
           s = slider.getValue();
           stuAddDel_c.table.setFont(new Font("Gothic", Font.PLAIN, 12+(Main.s-5)));   // 테이블 글자 크기 변경
        }
      });
      
      slider.setSize(50,230);
      slider.setLocation(10,20);
      add(slider);
      
      // 선택창 GUI
      javaGui_c.javaGui_p.setSize(395,245);
      javaGui_c.javaGui_p.setLocation(80,10);
      add(javaGui_c.javaGui_p); 
      // 과목별 학과 평균 막대그래프 GUI
      majorAvg_c.majorAvg_p.setSize(500,250);
      majorAvg_c.majorAvg_p.setLocation(520,10);
      add(majorAvg_c.majorAvg_p);   
      // 학생 추가 삭제 GUI
      stuAddDel_c.stuAddDel_p.setSize(995,470);
      stuAddDel_c.stuAddDel_p.setLocation(10,280);
      add(stuAddDel_c.stuAddDel_p);   
      // 막대그래프 GUI
      scoreGraph_c.scoreGraph_p.setSize(395,280);
      scoreGraph_c.scoreGraph_p.setLocation(1020,10);
      add(scoreGraph_c.scoreGraph_p);   
      // 파이차트 GUI
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