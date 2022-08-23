package javaProject;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javaProject.scoreGraph;
import javaProject.studentFile;

public class scorePieChart extends JFrame{ // 과목별 학점 비율로 만든 파이차트 GUI 
	public static Panel scorePieChart_p = new Panel();
	public scorePieChart() {
		scorePieChart_p.setLayout(new BorderLayout());

		PieChartPanel piechartPanel = new PieChartPanel();
		Box b = new Box(BoxLayout.X_AXIS);
		JLabel la = new JLabel("과목 별 학점 비율  "+"     ");
		la.setFont(new Font("Gothic", Font.BOLD, 13));
		
		// 파이차트의 색깔 구분을 위한 라벨 출력
		JLabel s1 = new JLabel("A학점  ");
		s1.setForeground(new Color(0x1F, 0xDA, 0x11));
		JLabel s2 = new JLabel("B학점  ");
		s2.setForeground(new Color(0xAB, 0xF2, 0x00));
		JLabel s3 = new JLabel("C학점  ");;     
		s3.setForeground(new Color(0xFF, 0xE4, 0x00));
		JLabel s4 = new JLabel("D학점  ");      
		s4.setForeground(new Color(0xFF, 0xBB, 0x00));
		JLabel s5 = new JLabel("F학점  ");      
		s5.setForeground(new Color(0xFF, 0x5E, 0x00));
		b.add(la);
		b.add(s1);
		b.add(s2);
		b.add(s3);
		b.add(s4);
		b.add(s5);
		
		scorePieChart_p.add(b, BorderLayout.NORTH);
		scorePieChart_p.add(piechartPanel, BorderLayout.CENTER);
	}
}

class PieChartPanel extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 과목별 점수 벡터
		Vector<String> score_java = scoreGraph.javaScores;
		Vector<String> score_soft = scoreGraph.softScores;
		Vector<String> score_internet = scoreGraph.internetScores;
		Vector<String> score_computer = scoreGraph.computerScores;

		// 과목별 학점 벡터
		Vector<String> grades_java = new Vector<String>();
		Vector<String> grades_soft = new Vector<String>();
		Vector<String> grades_internet = new Vector<String>();
		Vector<String> grades_computer = new Vector<String>();

		// 과목별 학점 내기
		for(int i=0; i < studentFile.javas.size(); i++) { // 자바 학점 내기
			if(Integer.parseInt(score_java.get(i))>90)      grades_java.addElement("A");
			else if(Integer.parseInt(score_java.get(i))>80)   grades_java.addElement("B");
			else if(Integer.parseInt(score_java.get(i))>65)   grades_java.addElement("C");
			else if(Integer.parseInt(score_java.get(i))>50)   grades_java.addElement("D");
			else                   grades_java.addElement("F");
		}

		for(int i=0; i < studentFile.softs.size(); i++) { // 소분설 학점 내기
			if(Integer.parseInt(score_soft.get(i))>90)      grades_soft.addElement("A");
			else if(Integer.parseInt(score_soft.get(i))>80)   grades_soft.addElement("B");
			else if(Integer.parseInt(score_soft.get(i))>65)   grades_soft.addElement("C");
			else if(Integer.parseInt(score_soft.get(i))>50)   grades_soft.addElement("D");
			else                   grades_soft.addElement("F");
		}

		for(int i=0; i < studentFile.internets.size(); i++) { // 인기초 학점 내기
			if(Integer.parseInt(score_internet.get(i))>90)      grades_internet.addElement("A");
			else if(Integer.parseInt(score_internet.get(i))>80)   grades_internet.addElement("B");
			else if(Integer.parseInt(score_internet.get(i))>65)   grades_internet.addElement("C");
			else if(Integer.parseInt(score_internet.get(i))>50)   grades_internet.addElement("D");
			else                   grades_internet.addElement("F");
		}

		for(int i=0; i < studentFile.computers.size(); i++) { // 컴구 학점 내기
			if(Integer.parseInt(score_computer.get(i))>90)      grades_computer.addElement("A");
			else if(Integer.parseInt(score_computer.get(i))>80)   grades_computer.addElement("B");
			else if(Integer.parseInt(score_computer.get(i))>65)   grades_computer.addElement("C");
			else if(Integer.parseInt(score_computer.get(i))>50)   grades_computer.addElement("D");
			else                   grades_computer.addElement("F");
		}

		// 과목별 학점 비율 내기

		// 자바 학점 비율
		int sumjA = 0, sumjB = 0, sumjC = 0, sumjD = 0, sumjF = 0;
		for(int i=0; i < studentFile.javas.size(); i++) {
			if (grades_java.get(i) == "A" )   sumjA += 1;
			else if(grades_java.get(i) == "B" ) sumjB += 1;
			else if(grades_java.get(i) == "C" ) sumjC += 1;
			else if(grades_java.get(i) == "D" ) sumjD += 1;
			else if(grades_java.get(i) == "F" ) sumjF += 1;         
		}

		// 소분설 학점 비율
		int sumsA = 0, sumsB = 0, sumsC = 0, sumsD = 0, sumsF = 0;
		for(int i=0; i < studentFile.softs.size(); i++) {
			if (grades_soft.get(i) == "A" )   sumsA += 1;
			else if(grades_soft.get(i) == "B" ) sumsB += 1;
			else if(grades_soft.get(i) == "C" ) sumsC += 1;
			else if(grades_soft.get(i) == "D" ) sumsD += 1;
			else if(grades_soft.get(i) == "F" ) sumsF += 1;         
		}

		// 인기초 학점 비율
		int sumiA = 0, sumiB = 0, sumiC = 0, sumiD = 0, sumiF = 0;
		for(int i=0; i < studentFile.internets.size(); i++) {
			if (grades_internet.get(i) == "A" )   sumiA += 1;
			else if(grades_internet.get(i) == "B" ) sumiB += 1;
			else if(grades_internet.get(i) == "C" ) sumiC += 1;
			else if(grades_internet.get(i) == "D" ) sumiD += 1;
			else if(grades_internet.get(i) == "F" ) sumiF += 1;         
		}

		// 컴구 학점 비율
		int sumcA = 0, sumcB = 0, sumcC = 0, sumcD = 0, sumcF = 0;
		for(int i=0; i < studentFile.computers.size(); i++) {
			if (grades_computer.get(i) == "A" )   sumcA += 1;
			else if(grades_computer.get(i) == "B" ) sumcB += 1;
			else if(grades_computer.get(i) == "C" ) sumcC += 1;
			else if(grades_computer.get(i) == "D" ) sumcD += 1;
			else if(grades_computer.get(i) == "F" ) sumcF += 1;         
		}

		// 파이차트 그리기
		float stAng, arcAng;
		// 자바 절대평가 등급 비율
		stAng = 90;
		// F학점
		g.setColor(new Color(0xFF, 0x5E, 0x00));
		arcAng = ((float)sumjF/((float)studentFile.javas.size()))*360;
		g.fillArc(0, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// D학점
		g.setColor(new Color(0xFF, 0xBB, 0x00));
		arcAng = ((float)sumjD/((float)studentFile.javas.size()))*360;
		g.fillArc(0, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// C학점
		g.setColor(new Color(0xFF, 0xE4, 0x00));
		arcAng = ((float)sumjC/((float)studentFile.javas.size()))*360;
		g.fillArc(0, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// B학점
		g.setColor(new Color(0xAB, 0xF2, 0x00));
		arcAng = ((float)sumjB/((float)studentFile.javas.size()))*360;
		g.fillArc(0, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// A학점
		g.setColor(new Color(0x1F, 0xDA, 0x11));
		arcAng = ((float)sumjA/((float)studentFile.javas.size()))*360;
		g.fillArc(0, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;

		// 소분설 절대평가 등급 비율
		// F학점
		stAng = 90;
		g.setColor(new Color(0xFF, 0x5E, 0x00));
		arcAng = ((float)sumsF/((float)studentFile.softs.size()))*360;
		g.fillArc(230, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// D학점
		g.setColor(new Color(0xFF, 0xBB, 0x00));
		arcAng = ((float)sumsD/((float)studentFile.softs.size()))*360;
		g.fillArc(230, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// C학점
		g.setColor(new Color(0xFF, 0xE4, 0x00));
		arcAng = ((float)sumsC/((float)studentFile.softs.size()))*360;
		g.fillArc(230, 17, 180, 180,(int)stAng, (int)arcAng);
		stAng += arcAng;
		// B학점
		g.setColor(new Color(0xAB, 0xF2, 0x00));
		arcAng = ((float)sumsB/((float)studentFile.softs.size()))*360;
		g.fillArc(230, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// A학점
		g.setColor(new Color(0x1F, 0xDA, 0x11));
		arcAng = ((float)sumsA/((float)studentFile.softs.size()))*360;
		g.fillArc(230, 17, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;

		// 인기초 절대평가 등급 비율
		stAng = 90;
		// F학점
		g.setColor(new Color(0xFF, 0x5E, 0x00));
		arcAng = ((float)sumiF/((float)studentFile.internets.size()))*360;
		g.fillArc(0, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// D학점
		g.setColor(new Color(0xFF, 0xBB, 0x00));
		arcAng = ((float)sumiD/((float)studentFile.internets.size()))*360;
		g.fillArc(0, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// C학점
		g.setColor(new Color(0xFF, 0xE4, 0x00));
		arcAng = ((float)sumiC/((float)studentFile.internets.size()))*360;
		g.fillArc(0, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// B학점
		g.setColor(new Color(0xAB, 0xF2, 0x00));
		arcAng = ((float)sumiB/((float)studentFile.internets.size()))*360;
		g.fillArc(0, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// A학점
		g.setColor(new Color(0x1F, 0xDA, 0x11));
		arcAng = ((float)sumiA/((float)studentFile.internets.size()))*360;
		g.fillArc(0, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;

		// 컴구 절대평가 등급 비율
		stAng = 90;
		// F학점
		g.setColor(new Color(0xFF, 0x5E, 0x00));
		arcAng = ((float)sumcF/((float)studentFile.computers.size()))*360;
		g.fillArc(230, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// D학점
		g.setColor(new Color(0xFF, 0xBB, 0x00));
		arcAng = ((float)sumcD/((float)studentFile.computers.size()))*360;
		g.fillArc(230, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// C학점
		g.setColor(new Color(0xFF, 0xE4, 0x00));
		arcAng = ((float)sumcC/((float)studentFile.computers.size()))*360;
		g.fillArc(230, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// B학점
		g.setColor(new Color(0xAB, 0xF2, 0x00));
		arcAng = ((float)sumcB/((float)studentFile.computers.size()))*360;
		g.fillArc(230, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;
		// A학점
		g.setColor(new Color(0x1F, 0xDA, 0x11));
		arcAng = ((float)sumcA/((float)studentFile.computers.size()))*360;
		g.fillArc(230, 237, 180, 180, (int)stAng, (int)arcAng);
		stAng += arcAng;


		g.setFont(new Font("Gothic", Font.BOLD, 12));
		g.setColor(Color.black);
		g.drawString("java프로그래밍",45,217);
		g.drawString("소프트웨어분석설계",260,217);
		g.drawString("인터넷기초",55,435);
		g.drawString("컴퓨터구조",289,435);
	}
}