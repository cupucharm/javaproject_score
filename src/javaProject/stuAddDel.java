package javaProject;

import javaProject.studentFile;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class stuAddDel extends JFrame { // �л� �߰� ���� GUI
	public static Panel stuAddDel_p = new Panel();

	public static Vector<String> vname = new Vector<String>();           // �̸� ���� ����
	public static Vector<String> vmajor = new Vector<String>();        	// ���� ���� ����
	public static Vector<String> vid = new Vector<String>();           	// �й� ���� ����
	public static Vector<String> vphone = new Vector<String>();         	// ��ȭ��ȣ ���� ����
	public static Vector<String> vjava = new Vector<String>();         	// java���α׷��� ���� ���� ����
	public static Vector<String> vsoft = new Vector<String>();          	// ����Ʈ����м����� ���� ���� ����
	public static Vector<String> vinternet = new Vector<String>();       // ���ͳݱ��� ���� ���� ����
	public static Vector<String> vcomputer = new Vector<String>();       // ��ǻ�ͱ��� ���� ���� ����
	public static JTable table;

	public stuAddDel() {
		stuAddDel_p.setLayout(new BorderLayout());

		String name, major, id, phone, java, soft, internet, computer;
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/javaProject/�л�����.txt"));

			String studentIndex;
			String s;

			while((s = in.readLine()) != null) {
				studentIndex = s;      // �� �� �� �о ������ ����
				studentFile.studentIndexs.add(studentIndex);   // ���Ϳ� ����

				String[] split = s.split(" ");   // " "�� �������� �ɰ�

				//������ ����
				major = split[0];
				id = split[1];
				name = split[2];
				phone = split[3];
				java = split[4];
				soft = split[5];
				internet = split[6];
				computer = split[7];

				//���Ϳ� ����
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

		// ��� �� �̸� ����
		Vector<String> header = new Vector<String>();
		header.addElement("�а�");
		header.addElement("�й�");
		header.addElement("�̸�");
		header.addElement("��ȭ��ȣ");
		header.addElement("java���α׷���");
		header.addElement("����Ʈ����м�����");
		header.addElement("���ͳݱ���");
		header.addElement("��ǻ�ͱ���");

		// �� ����
		Vector<Vector> rowData = new Vector<Vector>();

		// 0~studentIndexs.size()������ �� ��Ҹ� �� �࿡ ����, �ݺ��Ͽ� ������ ����
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

		// �߰��� �����͸� �Է¹��� JTextField ����
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

		// �߰� �Է� �ؽ�Ʈ �ʵ� �гο� ����
		panel.add(majorField);
		panel.add(idField);
		panel.add(nameField);
		panel.add(phoneField);
		panel.add(javaField);
		panel.add(softField);
		panel.add(internetField);
		panel.add(computerField);

		// �߰� ��ư
		JButton addBtn = new JButton("�߰�");
		// �߰� ��ư �̺�Ʈ ������
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

				// ���Ϳ� ����
				vmajor.add(inputStr[0]);
				vid.add(inputStr[1]);
				vname.add(inputStr[2]);
				vphone.add(inputStr[3]);
				vjava.add(inputStr[4]);
				vsoft.add(inputStr[5]);
				vinternet.add(inputStr[6]);
				vcomputer.add(inputStr[7]);

				// �ؽ�Ʈ �ʵ� �ʱ�ȭ
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

		// ���� ��ư
		JButton delBtn = new JButton("����");
		// ���� ��ư ������
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					update();   // ����� ���� ���� ������Ʈ
					return;
				}
				else {
					model.removeRow(table.getSelectedRow());
					update();   // ����� ���� ���� ������Ʈ
				}
			}
		});

		// ���� ��ư
		JButton saveBtn = new JButton("����");
		// ���� ��ư ������
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();   // ����� ���� ���� ������Ʈ
			}
		});


		table.setRowSorter(new TableRowSorter(model)); // ���̺� ��������, �������� ����
		table.setModel(model); 

		panel.add(addBtn);
		panel.add(delBtn);
		panel.add(saveBtn);

		JLabel la = new JLabel("�л�����(����Ŭ���Ͽ� �л����� ����)");
		la.setFont(new Font("Gothic",Font.BOLD,13));

		stuAddDel_p.add(la, BorderLayout.NORTH);
		stuAddDel_p.add(scrollPane, BorderLayout.CENTER);
		stuAddDel_p.add(panel, BorderLayout.SOUTH);
		stuAddDel_p.setEnabled(true);
	}

	// ������Ʈ �޼ҵ�
	public static void update() {
		try {
			File file = new File("src/javaProject/�л�����.txt");
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