package javaProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javaProject.studentFile;

public class javaGui extends JFrame {
	public static Panel javaGui_p = new Panel();

	// 학생 성적 확인 버튼
	public static JRadioButton stu_score = new JRadioButton("학생 성적 확인");
	// 과목별 성적 확인 버튼
	public static JRadioButton sub_score = new JRadioButton("과목별 성적 확인");
	// 과목 선택란
	public static String[] subject = {"java프로그래밍", "소프트웨어분석설계", "인터넷기초", "컴퓨터구조"};
	public static JLabel subl = new JLabel("과목 선택 : ");
	public static JComboBox<String> sub = new JComboBox<String>(subject);
	// 학번 입력란
	public static JLabel idl = new JLabel("학번 입력 : ");
	public static JTextField idInput = new JTextField();
	// 이름 입력란
	public static JLabel namel = new JLabel("이름 입력 : ");
	public static JTextField nameInput = new JTextField();
	// 성적 조회 버튼
	public static JButton check_okey = new JButton("성적 조회");


	// 전체화면 
	public javaGui() {
		javaGui_p.setLayout(new BorderLayout());
		
		idInput.setBackground(new Color(0xEA,0xEA,0xEA));
		nameInput.setBackground(new Color(0xEA,0xEA,0xEA));

		sub.setEnabled(false);         // 과목 선택란
		idInput.setEnabled(false);      // 학번 입력란
		nameInput.setEditable(false);   // 이름 입력란
		check_okey.setEnabled(false);   // 성적 조회 버튼

		// 리스너 달기
		stu_score.addItemListener(new MyIL());
		sub_score.addItemListener(new MyIL());
		check_okey.addActionListener(new MyAL());

		// 라디오버튼 그룹
		ButtonGroup g = new ButtonGroup();   
		g.add(stu_score);
		g.add(sub_score);

		Panel p1 = new Panel(new GridLayout(5,2));
		p1.add(stu_score);		// 학생성적 확인 라디오버튼
		p1.add(sub_score);  	// 과목별성적 확인 라디오버튼
		p1.add(subl);     		// 과목선택 라벨
		p1.add(sub);      		// 과목선택 콤보박스
		p1.add(idl);      		// 학번입력 라벨
		p1.add(idInput);   		// 학번입력 텍스트필드
		p1.add(namel);      	// 이름입력 라벨
		p1.add(nameInput);  	// 이름입력 텍스트필드
		p1.add(new Label(""));
		p1.add(check_okey);     // 성적조회 버튼

		Label l = new Label("<공과대 성적처리 프로그램>");
		l.setFont(new Font("Gothic", Font.BOLD, 30));

		javaGui_p.add(l, BorderLayout.NORTH);     // 날짜 출력
		javaGui_p.add(p1, BorderLayout.CENTER);   // 선택창 GUI 출력
	}

	// 라디오버튼에 적용될 리스너
	class MyIL implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.DESELECTED)   return;   // 아무것도 선택하지 않았을 때
			if(stu_score.isSelected()) {        // 학생 성적 확인 버튼 누름
				// 버튼, 텍스트 필드 활성화 or 비활성화
				sub.setEnabled(false);
				idInput.setEnabled(true);
				idInput.setBackground(Color.white);
				nameInput.setEditable(true);
				nameInput.setBackground(Color.white);
				check_okey.setEnabled(true);
			} 
			else if(sub_score.isSelected()) {   // 과목별 성적 확인 버튼 누름
				// 버튼, 텍스트 필드 활성화 or 비활성화
				sub.setEnabled(true);
				idInput.setEnabled(false);
				idInput.setBackground(new Color(0xEA,0xEA,0xEA));
				nameInput.setEditable(false);
				nameInput.setBackground(new Color(0xEA,0xEA,0xEA));
				check_okey.setEnabled(true);
			}
		}   
	}

	// 성적조회 버튼에 적용될 리스너
	class MyAL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == check_okey) {   // 성적 조회 버튼 누르면
				if(stu_score.isSelected()) {         // 학생 성적 확인 버튼 눌렀을 때
					String s1 = idInput.getText();   // 입력된 학번 저장
					String s2 = nameInput.getText(); // 입력된 이름 저장 
					int n=0;

					for(int i=0; i<studentFile.ids.size(); i++) {
						// 입력된 학번 또는 이름이 저장되어 있지 않을 때
						n=0;
						if(studentFile.ids.get(i).equals(s1) && studentFile.names.get(i).equals(s2)) {	//학생 성적 확인 GUI
							new scoreInput(s1, s2);
							idInput.setText("");	// 학번 입력창 초기화
							nameInput.setText("");	// 이름 입력창 초기화
							break; 
						}else    n++;   // 입력된 학번 또는 이름이 저장되어 있을 때
					} 
					if(n!=0) {
						JOptionPane.showMessageDialog(null, "학번 또는 이름을 확인해주세요.");   // 경고창 출력
						idInput.setText("");   	// 학번 입력창 초기화
						nameInput.setText("");  // 이름 입력창 초기화
					}               
				}
				else if(sub_score.isSelected()) {   // 과목별 성적 확인 버튼 눌렀을 때
					int index1 = sub.getSelectedIndex(); // 선택된 과목 인덱스값 저장            
					new scoreCheck(subject[index1]);     // 과목별 성적 확인 GUI
				}
			}
		}
	}
}