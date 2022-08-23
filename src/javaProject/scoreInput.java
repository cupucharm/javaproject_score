package javaProject;

import javaProject.studentFile;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class scoreInput extends JFrame {   // 학생 성적 확인 GUI
   public static String id;   // 선택된 학번
   public static String name;   // 선택된 이름

   // 학번,이름 받아올 생성자
   public scoreInput(String id, String name) {
      this.id = id;
      this.name = name;
      new scoreInput();   // 개별 성적 확인 GUI 출력
   }

   public scoreInput() {   // 개별 성적 확인 GUI
      super(id + " " + name + " 성적 확인");
      setLayout(new BorderLayout());
      float average = 0;   // 평균
      float g_average = 0; // 학점 평균

      Vector<Integer> scores = new Vector<Integer>();  // 점수 저장 벡터
      Vector <String> grades = new Vector<String>();   // 학점(문자) 저장 벡터
      Vector <Double> n_grades = new Vector<Double>(); // 학점(숫자) 저장 벡터

      // 학번을 통해 해당 학생의 인덱스값 찾기
      int j = 0;
      for(int i=0; i<studentFile.ids.size(); i++) {
         if(studentFile.ids.get(i).equals(id)) {
            j = i;
            break;
         }
      }
      
      // 과목별 점수 저장
      scores.addElement(Integer.parseInt(studentFile.javas.get(j)));
      scores.addElement(Integer.parseInt(studentFile.softs.get(j)));
      scores.addElement(Integer.parseInt(studentFile.internets.get(j)));
      scores.addElement(Integer.parseInt(studentFile.computers.get(j)));
      
      // 학점 계산
      for(int i=0; i<scores.size(); i++) {	// 점수에 따른 학점 부여
         if(scores.get(i)>90)   {   grades.add("A");   n_grades.add(4.0); }		//90점 이상 A(4.0)
         else if(scores.get(i)>80) {   grades.add("B");   n_grades.add(3.0); }	//80~90점 B(3.0)
         else if(scores.get(i)>65) {   grades.add("C");   n_grades.add(2.0); }	//65~80점 C(2.0)
         else if(scores.get(i)>50) {   grades.add("D");   n_grades.add(1.0); }	//50~65점 D(1.0)
         else                {   grades.add("F");   n_grades.add(0.0); }		//50점 이하 F(0.0)
      }

      // 성적 출력 테이블 작성
      // 상단 열 이름 벡터
      Vector<String> header = new Vector<String>();
      header.addElement("과목");
      header.addElement("점수");
      header.addElement("학점");

      int sum = 0;   // 점수 합계
      double grades_sum = 0;   // 학점 점수 합계
      int good_score = 0;   // 가장 좋은 점수
      String good_sub = "";   // 가장 좋은 점수를 가진 과목
      int bad_score = 100; // 가장 나쁜 점수
      String bad_sub = "";       // 가장 나쁜 점수를 가진 과목
      Vector<Vector> rowData = new Vector<Vector>();   // 행 벡터
      for(int i = 0; i < scores.size(); i++) {
         Vector<String> row = new Vector<String>();   // 행 벡터

         row.addElement(javaGui.subject[i]);
         row.addElement(Integer.toString(scores.get(i)));
         row.addElement(grades.get(i));

         rowData.addElement(row);

         sum += scores.get(i);
         grades_sum += n_grades.get(i);
         if(good_score < scores.get(i)) {	// 가장 좋은 점수 찾기 
            good_score = scores.get(i);
            good_sub = javaGui.subject[i];            
         }
         if(bad_score > scores.get(i)) { // 가장 나쁜 점수 찾기
            bad_score = scores.get(i);
            bad_sub = javaGui.subject[i];
         }
      }
      DefaultTableModel model = new DefaultTableModel(rowData, header);
      JTable table = new JTable(model);
      JScrollPane scrollPane = new JScrollPane(table);

      average = sum/scores.size(); //평균 계산
      g_average = (float)(grades_sum/n_grades.size());   // 학점 평균 계산

      Box box = new Box(BoxLayout.Y_AXIS);
      JLabel l1 = new JLabel("  점수 평균 : "+average);
      JLabel l2 = new JLabel("  학점 평균 : "+g_average);
      JLabel l3 = new JLabel("  가장 점수가 좋은 과목 : " + good_sub);
      JLabel l4 = new JLabel("  가장 점수가 안좋은 과목 : " + bad_sub);

      box.add(l1);
      box.add(l2);
      box.add(l3);
      box.add(l4);

      // 테이블 가운데 정렬
      DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
      tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
      TableColumnModel tcmSchedule = table.getColumnModel();
      tcmSchedule.getColumn(1).setCellRenderer(tScheduleCellRenderer);
      tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
      // 셀 넓이 설정
      table.getColumnModel().getColumn(0).setMaxWidth(170); 
      table.getColumnModel().getColumn(0).setMinWidth(170); 
      table.getColumnModel().getColumn(0).setWidth(170); 
      //행 높이 설정
      table.setRowHeight(53);
      table.setFont(new Font("Gothic", Font.PLAIN, 15));
      box.setFont(new Font("Gothic", Font.PLAIN, 50));

      JLabel la = new JLabel(" 학과: "+studentFile.majors.get(j)+"   학번 : "+id+"   이름 : "+name);
      la.setFont(new Font("Gothic", Font.BOLD, 15));
      add(la, BorderLayout.NORTH);
      add(scrollPane, BorderLayout.CENTER);
      add(box, BorderLayout.SOUTH);
      setSize(400,400);
      setVisible(true);
   }
}