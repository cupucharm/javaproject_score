package javaProject;

import javaProject.studentFile;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class scoreInput extends JFrame {   // �л� ���� Ȯ�� GUI
   public static String id;   // ���õ� �й�
   public static String name;   // ���õ� �̸�

   // �й�,�̸� �޾ƿ� ������
   public scoreInput(String id, String name) {
      this.id = id;
      this.name = name;
      new scoreInput();   // ���� ���� Ȯ�� GUI ���
   }

   public scoreInput() {   // ���� ���� Ȯ�� GUI
      super(id + " " + name + " ���� Ȯ��");
      setLayout(new BorderLayout());
      float average = 0;   // ���
      float g_average = 0; // ���� ���

      Vector<Integer> scores = new Vector<Integer>();  // ���� ���� ����
      Vector <String> grades = new Vector<String>();   // ����(����) ���� ����
      Vector <Double> n_grades = new Vector<Double>(); // ����(����) ���� ����

      // �й��� ���� �ش� �л��� �ε����� ã��
      int j = 0;
      for(int i=0; i<studentFile.ids.size(); i++) {
         if(studentFile.ids.get(i).equals(id)) {
            j = i;
            break;
         }
      }
      
      // ���� ���� ����
      scores.addElement(Integer.parseInt(studentFile.javas.get(j)));
      scores.addElement(Integer.parseInt(studentFile.softs.get(j)));
      scores.addElement(Integer.parseInt(studentFile.internets.get(j)));
      scores.addElement(Integer.parseInt(studentFile.computers.get(j)));
      
      // ���� ���
      for(int i=0; i<scores.size(); i++) {	// ������ ���� ���� �ο�
         if(scores.get(i)>90)   {   grades.add("A");   n_grades.add(4.0); }		//90�� �̻� A(4.0)
         else if(scores.get(i)>80) {   grades.add("B");   n_grades.add(3.0); }	//80~90�� B(3.0)
         else if(scores.get(i)>65) {   grades.add("C");   n_grades.add(2.0); }	//65~80�� C(2.0)
         else if(scores.get(i)>50) {   grades.add("D");   n_grades.add(1.0); }	//50~65�� D(1.0)
         else                {   grades.add("F");   n_grades.add(0.0); }		//50�� ���� F(0.0)
      }

      // ���� ��� ���̺� �ۼ�
      // ��� �� �̸� ����
      Vector<String> header = new Vector<String>();
      header.addElement("����");
      header.addElement("����");
      header.addElement("����");

      int sum = 0;   // ���� �հ�
      double grades_sum = 0;   // ���� ���� �հ�
      int good_score = 0;   // ���� ���� ����
      String good_sub = "";   // ���� ���� ������ ���� ����
      int bad_score = 100; // ���� ���� ����
      String bad_sub = "";       // ���� ���� ������ ���� ����
      Vector<Vector> rowData = new Vector<Vector>();   // �� ����
      for(int i = 0; i < scores.size(); i++) {
         Vector<String> row = new Vector<String>();   // �� ����

         row.addElement(javaGui.subject[i]);
         row.addElement(Integer.toString(scores.get(i)));
         row.addElement(grades.get(i));

         rowData.addElement(row);

         sum += scores.get(i);
         grades_sum += n_grades.get(i);
         if(good_score < scores.get(i)) {	// ���� ���� ���� ã�� 
            good_score = scores.get(i);
            good_sub = javaGui.subject[i];            
         }
         if(bad_score > scores.get(i)) { // ���� ���� ���� ã��
            bad_score = scores.get(i);
            bad_sub = javaGui.subject[i];
         }
      }
      DefaultTableModel model = new DefaultTableModel(rowData, header);
      JTable table = new JTable(model);
      JScrollPane scrollPane = new JScrollPane(table);

      average = sum/scores.size(); //��� ���
      g_average = (float)(grades_sum/n_grades.size());   // ���� ��� ���

      Box box = new Box(BoxLayout.Y_AXIS);
      JLabel l1 = new JLabel("  ���� ��� : "+average);
      JLabel l2 = new JLabel("  ���� ��� : "+g_average);
      JLabel l3 = new JLabel("  ���� ������ ���� ���� : " + good_sub);
      JLabel l4 = new JLabel("  ���� ������ ������ ���� : " + bad_sub);

      box.add(l1);
      box.add(l2);
      box.add(l3);
      box.add(l4);

      // ���̺� ��� ����
      DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
      tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
      TableColumnModel tcmSchedule = table.getColumnModel();
      tcmSchedule.getColumn(1).setCellRenderer(tScheduleCellRenderer);
      tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
      // �� ���� ����
      table.getColumnModel().getColumn(0).setMaxWidth(170); 
      table.getColumnModel().getColumn(0).setMinWidth(170); 
      table.getColumnModel().getColumn(0).setWidth(170); 
      //�� ���� ����
      table.setRowHeight(53);
      table.setFont(new Font("Gothic", Font.PLAIN, 15));
      box.setFont(new Font("Gothic", Font.PLAIN, 50));

      JLabel la = new JLabel(" �а�: "+studentFile.majors.get(j)+"   �й� : "+id+"   �̸� : "+name);
      la.setFont(new Font("Gothic", Font.BOLD, 15));
      add(la, BorderLayout.NORTH);
      add(scrollPane, BorderLayout.CENTER);
      add(box, BorderLayout.SOUTH);
      setSize(400,400);
      setVisible(true);
   }
}