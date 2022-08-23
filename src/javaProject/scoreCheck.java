package javaProject;

import javaProject.studentFile;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class scoreCheck extends JFrame{   // ���� Ȯ�� GUI
	public static String sub;      // ���õ� ����

	// �����,�򰡹�� �޾ƿ� ������
	public scoreCheck(String sub) {
		this.sub = sub;
		new scoreCheck();   // ���� ���� Ȯ�� GUI ���
	}

	public scoreCheck() {   // ���� ���� Ȯ�� GUI
		super(sub + " ���� Ȯ��");

		float average = 0;      // ���
		float highScore = 0;    // �ְ���
		float lowScore = 100;   // ������

		Vector<Integer> scores = new Vector<Integer>();   // ���� ���� ����
		Vector <String> grades = new Vector<String>();   // ���� ���� ����

		// ���� ���� ����
		if(sub=="java���α׷���")
			for(int i=0; i<studentFile.javas.size(); i++) scores.addElement(Integer.parseInt(studentFile.javas.get(i)));
		else if(sub=="����Ʈ����м�����")
			for(int i=0; i<studentFile.softs.size(); i++) scores.addElement(Integer.parseInt(studentFile.softs.get(i)));
		else if(sub=="���ͳݱ���")
			for(int i=0; i<studentFile.internets.size(); i++) scores.addElement(Integer.parseInt(studentFile.internets.get(i)));
		else if(sub=="��ǻ�ͱ���")
			for(int i=0; i<studentFile.computers.size(); i++) scores.addElement(Integer.parseInt(studentFile.computers.get(i)));
		else
			JOptionPane.showMessageDialog(null,"�ش� ������ �������� �ʽ��ϴ�.");

		for(int i=0; i<scores.size(); i++) { // ������ ���� ���� �ο�
			if(scores.get(i)>90)      grades.add("A");		//90�� �̻� A
			else if(scores.get(i)>80)   grades.add("B");	//80~90�� B
			else if(scores.get(i)>65)   grades.add("C");	//65~80�� C
			else if(scores.get(i)>50)   grades.add("D");	//50~65�� D
			else                   grades.add("F");			//50�� ���� F
		}

		// ���� ��� ���̺� �ۼ�
		// ��� �� �̸� ����
		Vector<String> header = new Vector<String>();
		header.addElement("�а�");
		header.addElement("�й�");
		header.addElement("�̸�");
		header.addElement("����");
		header.addElement("����");

		int sum = 0;   // ���� �հ�
		Vector<Vector> rowData = new Vector<Vector>();   // �� ����
		for(int i = 0; i < scores.size(); i++) {
			Vector<String> row = new Vector<String>();   // �� ����

			row.addElement(studentFile.majors.get(i));
			row.addElement(studentFile.ids.get(i));
			row.addElement(studentFile.names.get(i));
			row.addElement(Integer.toString(scores.get(i)));
			row.addElement(grades.get(i));

			rowData.addElement(row);
			sum += scores.get(i);
		}
		DefaultTableModel model = new DefaultTableModel(rowData, header);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);


		// ���, �ְ���, ������ ���
		for(int i=0; i<scores.size(); i++) {
			if(scores.get(i) > highScore)
				highScore = scores.get(i);
			if(scores.get(i) < lowScore)
				lowScore = scores.get(i);
		} average = sum / scores.size();

		JLabel la = new JLabel("����� : " + sub);
		la.setFont(new Font("Gothic", Font.BOLD, 20));
		add(la, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		JLabel l = new JLabel("��� : "+average+ "��, "+ " �ְ��� : "+highScore +"��, "+ " ������ : "+lowScore + "��");
		l.setFont(new Font("Gothic", Font.BOLD, 15));
		add(l, BorderLayout.SOUTH);
		table.setRowSorter(new TableRowSorter(model)); // ���̺� ��������, �������� ����

		pack();
		setVisible(true);

	}
}