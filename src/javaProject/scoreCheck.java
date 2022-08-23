package javaProject;

import javaProject.studentFile;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class scoreCheck extends JFrame{   // 성적 확인 GUI
	public static String sub;      // 선택된 과목

	// 과목명,평가방법 받아올 생성자
	public scoreCheck(String sub) {
		this.sub = sub;
		new scoreCheck();   // 과목별 성적 확인 GUI 출력
	}

	public scoreCheck() {   // 과목별 성적 확인 GUI
		super(sub + " 성적 확인");

		float average = 0;      // 평균
		float highScore = 0;    // 최고점
		float lowScore = 100;   // 최저점

		Vector<Integer> scores = new Vector<Integer>();   // 점수 저장 벡터
		Vector <String> grades = new Vector<String>();   // 학점 저장 벡터

		// 과목별 점수 저장
		if(sub=="java프로그래밍")
			for(int i=0; i<studentFile.javas.size(); i++) scores.addElement(Integer.parseInt(studentFile.javas.get(i)));
		else if(sub=="소프트웨어분석설계")
			for(int i=0; i<studentFile.softs.size(); i++) scores.addElement(Integer.parseInt(studentFile.softs.get(i)));
		else if(sub=="인터넷기초")
			for(int i=0; i<studentFile.internets.size(); i++) scores.addElement(Integer.parseInt(studentFile.internets.get(i)));
		else if(sub=="컴퓨터구조")
			for(int i=0; i<studentFile.computers.size(); i++) scores.addElement(Integer.parseInt(studentFile.computers.get(i)));
		else
			JOptionPane.showMessageDialog(null,"해당 과목이 존재하지 않습니다.");

		for(int i=0; i<scores.size(); i++) { // 점수에 따른 학점 부여
			if(scores.get(i)>90)      grades.add("A");		//90점 이상 A
			else if(scores.get(i)>80)   grades.add("B");	//80~90점 B
			else if(scores.get(i)>65)   grades.add("C");	//65~80점 C
			else if(scores.get(i)>50)   grades.add("D");	//50~65점 D
			else                   grades.add("F");			//50점 이하 F
		}

		// 성적 출력 테이블 작성
		// 상단 열 이름 벡터
		Vector<String> header = new Vector<String>();
		header.addElement("학과");
		header.addElement("학번");
		header.addElement("이름");
		header.addElement("점수");
		header.addElement("학점");

		int sum = 0;   // 점수 합계
		Vector<Vector> rowData = new Vector<Vector>();   // 행 벡터
		for(int i = 0; i < scores.size(); i++) {
			Vector<String> row = new Vector<String>();   // 행 벡터

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


		// 평균, 최고점, 최저점 계산
		for(int i=0; i<scores.size(); i++) {
			if(scores.get(i) > highScore)
				highScore = scores.get(i);
			if(scores.get(i) < lowScore)
				lowScore = scores.get(i);
		} average = sum / scores.size();

		JLabel la = new JLabel("과목명 : " + sub);
		la.setFont(new Font("Gothic", Font.BOLD, 20));
		add(la, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		JLabel l = new JLabel("평균 : "+average+ "점, "+ " 최고점 : "+highScore +"점, "+ " 최저점 : "+lowScore + "점");
		l.setFont(new Font("Gothic", Font.BOLD, 15));
		add(l, BorderLayout.SOUTH);
		table.setRowSorter(new TableRowSorter(model)); // 테이블 오름차순, 내림차순 정렬

		pack();
		setVisible(true);

	}
}