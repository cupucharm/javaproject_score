package javaProject;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class majorAvg extends JFrame{   // ���� �а���� �׷��� GUI
   public static Panel majorAvg_p = new Panel();

   private JCheckBox[] subs = new JCheckBox[4];
   private String[] sub = {"java���α׷���", "����Ʈ����м�����", "���ͳݱ���", "��ǻ�ͱ���"};

   public majorAvg () {
      majorAvg_p.setLayout(new BorderLayout());
      
      // ���� �׷��� �׸��� Ŭ���� ����
      javaPanel javaPanel = new javaPanel();
      softPanel softPanel = new softPanel();
      internetPanel internetPanel = new internetPanel();
      computerPanel computerPanel = new computerPanel();
      
      // �׷��� ��Ȱ��ȭ
      javaPanel.setVisible(false);
      softPanel.setVisible(false);
      internetPanel.setVisible(false);
      computerPanel.setVisible(false);
      
      Panel panel = new Panel();   // üũ�ڽ� ���̴� �г� ����
      panel.setLayout(new GridLayout(4,1));
      for(int i=0; i<sub.length; i++) {
         subs[i] = new JCheckBox(sub[i]);   // ���� üũ�ڽ� ����
         subs[i].setBorderPainted(true);
         panel.add(subs[i]);   // �гο� ����
         
         // üũ�ڽ� ������
         subs[i].addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
               boolean b = true;
               if(e.getStateChange() == ItemEvent.SELECTED) {   // ���� �Ǿ��� ��
                  if(e.getItem()==subs[0])   // java���α׷��� ��ư�� Ŭ������ ��
                     javaPanel.setVisible(b);
                  else if(e.getItem() == subs[1])   // ����Ʈ����м����� ��ư�� Ŭ������ ��
                     softPanel.setVisible(b);
                  else if(e.getItem() == subs[2])   // ���ͳݱ��� ��ư�� Ŭ������ ��
                     internetPanel.setVisible(b);
                  else if(e.getItem() == subs[3])   // ��ǻ�ͱ��� ��ư�� Ŭ������ ��
                     computerPanel.setVisible(b);
               }
               else {   // ���õ��� �ʾ��� ��
                  if(e.getItem()==subs[0])   // java���α׷��� ��ư�� Ŭ������ ��
                     javaPanel.setVisible(!b);
                  else if(e.getItem() == subs[1])  // ����Ʈ����м����� ��ư�� Ŭ������ ��
                     softPanel.setVisible(!b);
                  else if(e.getItem() == subs[2])  // ���ͳݱ��� ��ư�� Ŭ������ ��
                     internetPanel.setVisible(!b);
                  else if(e.getItem() == subs[3])  // ��ǻ�ͱ��� ��ư�� Ŭ������ ��
                     computerPanel.setVisible(!b);
               }
            }
         });
         
      }
      
      // ���� �׷��� �гο� ��ü
      Panel box = new Panel(new GridLayout(4,1));
      box.add(javaPanel);
      box.add(softPanel);
      box.add(internetPanel);
      box.add(computerPanel);
      
      Box b = new Box(BoxLayout.X_AXIS);
      JLabel la = new JLabel("���� �а� ��� �׷���  "+ "    ");
      la.setFont(new Font("Gothic", Font.BOLD, 13));
      JLabel s1 = new JLabel("��ǻ���а�  ");
      s1.setForeground(new Color(0xF1, 0x5F, 0x5F));
      JLabel s2 = new JLabel("IT�̵����а�  ");
      s2.setForeground(new Color(0xF2, 0x96, 0x61));
      JLabel s3 = new JLabel("���̿����а�  ");
      s3.setForeground(new Color(0xF2, 0xCB, 0x61));
      b.add(la);
      b.add(s1);
      b.add(s2);
      b.add(s3);

      majorAvg_p.add(b, BorderLayout.NORTH);
      majorAvg_p.add(panel, BorderLayout.WEST);
      majorAvg_p.add(box, BorderLayout.CENTER);
   }


   // �ڹ� �׷��� �׸���
   class javaPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;         // ���� ���� ����
         Vector<String> java_s = studentFile.javas;         // java���α׷��� ���� ���� ����
         Vector<Integer> cmajor = new Vector<Integer>();    // ��ǻ���а� �л����� �ε���
         Vector<Integer> imajor = new Vector<Integer>();    // IT�̵����а� �л����� �ε���
         Vector<Integer> bmajor = new Vector<Integer>();    // ���̿����а� �л����� �ε���
         int javaC = 0, javaI = 0, javaB = 0;
         
         g.drawLine(30,0,30,63); //y��
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // �а��� �ε��� ����
            if(major.get(i).equals("��ǻ���а�"))   cmajor.add(i);
            else if(major.get(i).equals("IT�̵����а�")) imajor.add(i);
            else if(major.get(i).equals("���̿����а�")) bmajor.add(i);   
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // �а��� ���� ����
            if(cmajor.contains(i)) javaC += Integer.parseInt(java_s.get(i)); 
            else if(imajor.contains(i)) javaI += Integer.parseInt(java_s.get(i)); 
            else if(bmajor.contains(i)) javaB += Integer.parseInt(java_s.get(i));
         }
         
         int javaAvg[] = {javaC / cmajor.size(), javaI / imajor.size(), javaB / bmajor.size()}; 
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // ��ǻ���а� ����׷���
         g.fillRect(31, 7, javaAvg[0]*3, 10);
         g.drawString(Integer.toString(javaAvg[0])+"��",javaAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT�̵����а� ����׷���
         g.fillRect(31, 20, javaAvg[1]*3, 10);
         g.drawString(Integer.toString(javaAvg[1])+"��",javaAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // ���̿����а� ����׷���
         g.fillRect(31, 33, javaAvg[2]*3, 10);
         g.drawString(Integer.toString(javaAvg[2])+"��",javaAvg[2]*3 + 45 , 42);
      }
   }
   // �Һм� �׷��� �׸���
   class softPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;         // ���� ���� ����
         Vector<String> soft_s = studentFile.softs;         // ����Ʈ����м����� ���� ���� ����
         Vector<Integer> cmajor = new Vector<Integer>();    // ��ǻ���а� �л����� �ε���
         Vector<Integer> imajor = new Vector<Integer>();    // IT�̵����а� �л����� �ε���
         Vector<Integer> bmajor = new Vector<Integer>();    // ���̿����а� �л����� �ε���
         int softC = 0, softI = 0, softB = 0;
         
         g.drawLine(30,0,30,64); //y��
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // �а��� �ε��� ����
            if(major.get(i).equals("��ǻ���а�"))   cmajor.add(i);
            else if(major.get(i).equals("IT�̵����а�")) imajor.add(i);
            else if(major.get(i).equals("���̿����а�")) bmajor.add(i);   
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // �а��� ���� ����
            if(cmajor.contains(i)) softC += Integer.parseInt(soft_s.get(i)); 
            else if(imajor.contains(i)) softI += Integer.parseInt(soft_s.get(i)); 
            else if(bmajor.contains(i)) softB += Integer.parseInt(soft_s.get(i));
         }
         
         int softAvg[] = {softC / cmajor.size(), softI / imajor.size(), softB / bmajor.size()}; 
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // ��ǻ���а� ����׷���
         g.fillRect(31, 7, softAvg[0]*3, 10);
         g.drawString(Integer.toString(softAvg[0])+"��",softAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT�̵����а� ����׷���
         g.fillRect(31, 20, softAvg[1]*3, 10);
         g.drawString(Integer.toString(softAvg[1])+"��",softAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // ���̿����а� ����׷���
         g.fillRect(31, 33, softAvg[2]*3, 10);
         g.drawString(Integer.toString(softAvg[2])+"��",softAvg[2]*3 + 45 , 42);
      }
   }
   //�α��� �׷��� �׸���
   class internetPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;          // ���� ���� ����
         Vector<String> internet_s = studentFile.internets;  // ���ͳݱ��� ���� ���� ����
         Vector<Integer> cmajor = new Vector<Integer>();     // ��ǻ���а� �л����� �ε���
         Vector<Integer> imajor = new Vector<Integer>();     // IT�̵����а� �л����� �ε���
         Vector<Integer> bmajor = new Vector<Integer>();     // ���̿����а� �л����� �ε���
         int internetC = 0, internetI = 0, internetB = 0;
         
         g.drawLine(30,0,30,64); //y��
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // �а��� �ε��� ����
            if(major.get(i).equals("��ǻ���а�"))   cmajor.add(i);
            else if(major.get(i).equals("IT�̵����а�")) imajor.add(i);
            else if(major.get(i).equals("���̿����а�")) bmajor.add(i);   
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // �а��� ���� ����
            if(cmajor.contains(i)) internetC += Integer.parseInt(internet_s.get(i)); 
            else if(imajor.contains(i)) internetI += Integer.parseInt(internet_s.get(i)); 
            else if(bmajor.contains(i)) internetB += Integer.parseInt(internet_s.get(i));
         }
         
         int internetAvg[] = {internetC / cmajor.size(), internetI / imajor.size(), internetB / bmajor.size()};
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // ��ǻ���а� ����׷���
         g.fillRect(31, 7, internetAvg[0]*3, 10);
         g.drawString(Integer.toString(internetAvg[0])+"��",internetAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT�̵����а� ����׷���
         g.fillRect(31, 20, internetAvg[1]*3, 10);
         g.drawString(Integer.toString(internetAvg[1])+"��",internetAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // ���̿����а� ����׷���
         g.fillRect(31, 33, internetAvg[2]*3, 10);
         g.drawString(Integer.toString(internetAvg[2])+"��",internetAvg[2]*3 + 45 , 42);
      }
   }
   // �ı� �׷��� �׸���
   class computerPanel extends JPanel {
      public void paint(Graphics g) {
         Vector<String> major = studentFile.majors;          // ���� ���� ����
         Vector<String> computer_s = studentFile.computers;  // ��ǻ�ͱ��� ���� ���� ����
         Vector<Integer> cmajor = new Vector<Integer>();     // ��ǻ���а� �л����� �ε���
         Vector<Integer> imajor = new Vector<Integer>();     // IT�̵����а� �л����� �ε���
         Vector<Integer> bmajor = new Vector<Integer>();     // ���̿����а� �л����� �ε���
         int computerC = 0, computerI = 0, computerB = 0;
         
         g.drawLine(30,0,30,50); //y��
         g.setFont(new Font("Gothic", Font.BOLD, 10));

         for(int i=0; i<major.size(); i++) {   // �а��� �ε��� ����
            if(major.get(i).equals("��ǻ���а�"))   cmajor.add(i);
            else if(major.get(i).equals("IT�̵����а�")) imajor.add(i);
            else if(major.get(i).equals("���̿����а�")) bmajor.add(i);  
         }
         
         for(int i=0; i<studentFile.studentIndexs.size(); i++) {   // �а��� ���� ����
            if(cmajor.contains(i)) computerC += Integer.parseInt(computer_s.get(i)); 
            else if(imajor.contains(i)) computerI += Integer.parseInt(computer_s.get(i)); 
            else if(bmajor.contains(i)) computerB += Integer.parseInt(computer_s.get(i));
         }
         
         // �׷��� �׸���
         int computerAvg[] = {computerC / cmajor.size(), computerI / imajor.size(), computerB / bmajor.size()};
         g.setColor(new Color(0xF1, 0x5F, 0x5F)); // ��ǻ���а� ����׷���
         g.fillRect(31, 7, computerAvg[0]*3, 10);
         g.drawString(Integer.toString(computerAvg[0])+"��",computerAvg[0]*3 + 45 , 16);
         g.setColor(new Color(0xF2, 0x96, 0x61)); // IT�̵����а� ����׷���
         g.fillRect(31, 20, computerAvg[1]*3, 10);
         g.drawString(Integer.toString(computerAvg[1])+"��",computerAvg[1]*3 + 45 , 29);
         g.setColor(new Color(0xF2, 0xCB, 0x61)); // ���̿����а� ����׷���
         g.fillRect(31, 33, computerAvg[2]*3, 10);
         g.drawString(Integer.toString(computerAvg[2])+"��",computerAvg[2]*3 + 45 , 42);
      }
   }
}