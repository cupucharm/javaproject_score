package javaProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javaProject.studentFile;

public class javaGui extends JFrame {
	public static Panel javaGui_p = new Panel();

	// �л� ���� Ȯ�� ��ư
	public static JRadioButton stu_score = new JRadioButton("�л� ���� Ȯ��");
	// ���� ���� Ȯ�� ��ư
	public static JRadioButton sub_score = new JRadioButton("���� ���� Ȯ��");
	// ���� ���ö�
	public static String[] subject = {"java���α׷���", "����Ʈ����м�����", "���ͳݱ���", "��ǻ�ͱ���"};
	public static JLabel subl = new JLabel("���� ���� : ");
	public static JComboBox<String> sub = new JComboBox<String>(subject);
	// �й� �Է¶�
	public static JLabel idl = new JLabel("�й� �Է� : ");
	public static JTextField idInput = new JTextField();
	// �̸� �Է¶�
	public static JLabel namel = new JLabel("�̸� �Է� : ");
	public static JTextField nameInput = new JTextField();
	// ���� ��ȸ ��ư
	public static JButton check_okey = new JButton("���� ��ȸ");


	// ��üȭ�� 
	public javaGui() {
		javaGui_p.setLayout(new BorderLayout());
		
		idInput.setBackground(new Color(0xEA,0xEA,0xEA));
		nameInput.setBackground(new Color(0xEA,0xEA,0xEA));

		sub.setEnabled(false);         // ���� ���ö�
		idInput.setEnabled(false);      // �й� �Է¶�
		nameInput.setEditable(false);   // �̸� �Է¶�
		check_okey.setEnabled(false);   // ���� ��ȸ ��ư

		// ������ �ޱ�
		stu_score.addItemListener(new MyIL());
		sub_score.addItemListener(new MyIL());
		check_okey.addActionListener(new MyAL());

		// ������ư �׷�
		ButtonGroup g = new ButtonGroup();   
		g.add(stu_score);
		g.add(sub_score);

		Panel p1 = new Panel(new GridLayout(5,2));
		p1.add(stu_score);		// �л����� Ȯ�� ������ư
		p1.add(sub_score);  	// ���񺰼��� Ȯ�� ������ư
		p1.add(subl);     		// ������ ��
		p1.add(sub);      		// ������ �޺��ڽ�
		p1.add(idl);      		// �й��Է� ��
		p1.add(idInput);   		// �й��Է� �ؽ�Ʈ�ʵ�
		p1.add(namel);      	// �̸��Է� ��
		p1.add(nameInput);  	// �̸��Է� �ؽ�Ʈ�ʵ�
		p1.add(new Label(""));
		p1.add(check_okey);     // ������ȸ ��ư

		Label l = new Label("<������ ����ó�� ���α׷�>");
		l.setFont(new Font("Gothic", Font.BOLD, 30));

		javaGui_p.add(l, BorderLayout.NORTH);     // ��¥ ���
		javaGui_p.add(p1, BorderLayout.CENTER);   // ����â GUI ���
	}

	// ������ư�� ����� ������
	class MyIL implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.DESELECTED)   return;   // �ƹ��͵� �������� �ʾ��� ��
			if(stu_score.isSelected()) {        // �л� ���� Ȯ�� ��ư ����
				// ��ư, �ؽ�Ʈ �ʵ� Ȱ��ȭ or ��Ȱ��ȭ
				sub.setEnabled(false);
				idInput.setEnabled(true);
				idInput.setBackground(Color.white);
				nameInput.setEditable(true);
				nameInput.setBackground(Color.white);
				check_okey.setEnabled(true);
			} 
			else if(sub_score.isSelected()) {   // ���� ���� Ȯ�� ��ư ����
				// ��ư, �ؽ�Ʈ �ʵ� Ȱ��ȭ or ��Ȱ��ȭ
				sub.setEnabled(true);
				idInput.setEnabled(false);
				idInput.setBackground(new Color(0xEA,0xEA,0xEA));
				nameInput.setEditable(false);
				nameInput.setBackground(new Color(0xEA,0xEA,0xEA));
				check_okey.setEnabled(true);
			}
		}   
	}

	// ������ȸ ��ư�� ����� ������
	class MyAL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == check_okey) {   // ���� ��ȸ ��ư ������
				if(stu_score.isSelected()) {         // �л� ���� Ȯ�� ��ư ������ ��
					String s1 = idInput.getText();   // �Էµ� �й� ����
					String s2 = nameInput.getText(); // �Էµ� �̸� ���� 
					int n=0;

					for(int i=0; i<studentFile.ids.size(); i++) {
						// �Էµ� �й� �Ǵ� �̸��� ����Ǿ� ���� ���� ��
						n=0;
						if(studentFile.ids.get(i).equals(s1) && studentFile.names.get(i).equals(s2)) {	//�л� ���� Ȯ�� GUI
							new scoreInput(s1, s2);
							idInput.setText("");	// �й� �Է�â �ʱ�ȭ
							nameInput.setText("");	// �̸� �Է�â �ʱ�ȭ
							break; 
						}else    n++;   // �Էµ� �й� �Ǵ� �̸��� ����Ǿ� ���� ��
					} 
					if(n!=0) {
						JOptionPane.showMessageDialog(null, "�й� �Ǵ� �̸��� Ȯ�����ּ���.");   // ���â ���
						idInput.setText("");   	// �й� �Է�â �ʱ�ȭ
						nameInput.setText("");  // �̸� �Է�â �ʱ�ȭ
					}               
				}
				else if(sub_score.isSelected()) {   // ���� ���� Ȯ�� ��ư ������ ��
					int index1 = sub.getSelectedIndex(); // ���õ� ���� �ε����� ����            
					new scoreCheck(subject[index1]);     // ���� ���� Ȯ�� GUI
				}
			}
		}
	}
}