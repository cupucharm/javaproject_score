package javaProject;

import javaProject.studentFile;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class stuAddDel extends JFrame { // 학생 추가 삭제 GUI
	public static Panel stuAddDel_p = new Panel();

	public static Vector<String> vname = new Vector<String>();           // 이름 저장 벡터
	public static Vector<String> vmajor = new Vector<String>();        	// 전공 저장 벡터
	public static Vector<String> vid = new Vector<String>();           	// 학번 저장 벡터
	public static Vector<String> vphone = new Vector<String>();         	// 전화번호 저장 벡터
	public static Vector<String> vjava = new Vector<String>();         	// java프로그래밍 점수 저장 벡터
	public static Vector<String> vsoft = new Vector<String>();          	// 소프트웨어분석설계 점수 저장 벡터
	public static Vector<String> vinternet = new Vector<String>();       // 인터넷기초 점수 저장 벡터
	public static Vector<String> vcomputer = new Vector<String>();       // 컴퓨터구조 점수 저장 벡터
	public static JTable table;

	public stuAddDel() {
		stuAddDel_p.setLayout(new BorderLayout());

		String name, major, id, phone, java, soft, internet, computer;
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/javaProject/학생정보.txt"));

			String studentIndex;
			String s;

			while((s = in.readLine()) != null) {
				studentIndex = s;      // 한 줄 씩 읽어서 변수에 저장
				studentFile.studentIndexs.add(studentIndex);   // 벡터에 저장

				String[] split = s.split(" ");   // " "를 기준으로 쪼갬

				//변수에 저장
				major = split[0];
				id = split[1];
				name = split[2];
				phone = split[3];
				java = split[4];
				soft = split[5];
				internet = split[6];
				computer = split[7];

				//벡터에 저장
				vname.add(name);
				vmajor.add(major);
				vid.add(id);
				vphone.add(phone);
				vjava.add(java);
				vsoft.add(soft);
				vinternet.add(internet);
				vcomputer.add(computer);
			}
			in.close();

		}catch(IOException e){
			System.out.println("File not found");
		}

		// 상단 열 이름 벡터
		Vector<String> header = new Vector<String>();
		header.addElement("학과");
		header.addElement("학번");
		header.addElement("이름");
		header.addElement("전화번호");
		header.addElement("java프로그래밍");
		header.addElement("소프트웨어분석설계");
		header.addElement("인터넷기초");
		header.addElement("컴퓨터구조");

		// 행 벡터
		Vector<Vector> rowData = new Vector<Vector>();

		// 0~studentIndexs.size()까지의 각 요소를 한 행에 저장, 반복하여 여러행 생성
		for(int i = 0; i < studentFile.studentIndexs.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.addElement(vmajor.get(i));
			row.addElement(vid.get(i));
			row.addElement(vname.get(i));
			row.addElement(vphone.get(i)); 
			row.addElement(vjava.get(i));
			row.addElement(vsoft.get(i));
			row.addElement(vinternet.get(i));
			row.addElement(vcomputer.get(i));

			rowData.addElement(row);
		}

		DefaultTableModel model = new DefaultTableModel(rowData, header);
		table = new JTable(rowData, header);
		JScrollPane scrollPane = new JScrollPane(table);

		// 추가할 데이터를 입력받을 JTextField 생성
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JTextField majorField = new JTextField(5);
		JTextField idField = new JTextField(5);
		JTextField nameField = new JTextField(5);
		JTextField phoneField = new JTextField(5);
		JTextField javaField = new JTextField(5);
		JTextField softField = new JTextField(5);
		JTextField internetField = new JTextField(5);
		JTextField computerField = new JTextField(5);

		// 추가 입력 텍스트 필드 패널에 부착
		panel.add(majorField);
		panel.add(idField);
		panel.add(nameField);
		panel.add(phoneField);
		panel.add(javaField);
		panel.add(softField);
		panel.add(internetField);
		panel.add(computerField);

		// 추가 버튼
		JButton addBtn = new JButton("추가");
		// 추가 버튼 이벤트 리스너
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String inputStr[] = new String[8];
				inputStr[0] = majorField.getText();
				inputStr[1] = idField.getText();
				inputStr[2] = nameField.getText();
				inputStr[3] = phoneField.getText();
				inputStr[4] = javaField.getText();
				inputStr[5] = softField.getText();
				inputStr[6] = internetField.getText();
				inputStr[7] = computerField.getText();
				model.addRow(inputStr);

				// 벡터에 저장
				vmajor.add(inputStr[0]);
				vid.add(inputStr[1]);
				vname.add(inputStr[2]);
				vphone.add(inputStr[3]);
				vjava.add(inputStr[4]);
				vsoft.add(inputStr[5]);
				vinternet.add(inputStr[6]);
				vcomputer.add(inputStr[7]);

				// 텍스트 필드 초기화
				majorField.setText("");
				idField.setText("");
				nameField.setText("");
				phoneField.setText("");
				javaField.setText("");
				softField.setText("");
				internetField.setText("");
				computerField.setText("");

				update();
			}
		});

		// 삭제 버튼
		JButton delBtn = new JButton("삭제");
		// 삭제 버튼 리스너
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					update();   // 변경된 파일 정보 업데이트
					return;
				}
				else {
					model.removeRow(table.getSelectedRow());
					update();   // 변경된 파일 정보 업데이트
				}
			}
		});

		// 저장 버튼
		JButton saveBtn = new JButton("저장");
		// 저장 버튼 리스너
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();   // 변경된 파일 정보 업데이트
			}
		});


		table.setRowSorter(new TableRowSorter(model)); // 테이블 오름차순, 내림차순 정렬
		table.setModel(model); 

		panel.add(addBtn);
		panel.add(delBtn);
		panel.add(saveBtn);

		JLabel la = new JLabel("학생정보(더블클릭하여 학생정보 수정)");
		la.setFont(new Font("Gothic",Font.BOLD,13));

		stuAddDel_p.add(la, BorderLayout.NORTH);
		stuAddDel_p.add(scrollPane, BorderLayout.CENTER);
		stuAddDel_p.add(panel, BorderLayout.SOUTH);
		stuAddDel_p.setEnabled(true);
	}

	// 업데이트 메소드
	public static void update() {
		try {
			File file = new File("src/javaProject/학생정보.txt");
			if(!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new   FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i<table.getRowCount(); i++) {
				for (int j = 0; j<table.getColumnCount(); j++) {
					bw.write(table.getModel().getValueAt(i, j)+" ");
				}
				bw.write("\n");
			}
			bw.close();
			fw.close();

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}