package net.daum.controller;

import net.daum.dao.GuDAOImp1;
import net.daum.vo.GuVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuFrame extends JFrame{
    private JPanel panel1;
    private JPanel jpCenter;
    private JPanel jpInnerTop;
    private JPanel jpButton;
    private JButton btnOk;
    private JTextField gname;
    private JTextField gtitle;
    private JTextArea gcont;
    private JButton btnList;
    GuVO gv = new GuVO();
    GuDAOImp1 gdi = new GuDAOImp1();
    int result = -1;

    public GuFrame() {
        setTitle("JDBC Insert");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gv.setGname(gname.getText());
                gv.setGtitle(gtitle.getText());
                gv.setGcont(gcont.getText());

                if(gv.getGname().equals("") || gv.getGtitle().equals("") || gv.getGcont().equals("")){
                    JOptionPane.showMessageDialog(null, "NULL 값 존재");
                }else {
                    result = gdi.insertGu(gv);

                    if(result==1){
                        JOptionPane.showMessageDialog(null, "Insert 완료");
                        gname.setText(null);
                        gtitle.setText(null);
                        gcont.setText(null);
                        gname.requestFocus();

                    }else {
                        JOptionPane.showMessageDialog(null, "Insert 실패");
                    }
                }
            }
        });
        btnList.addActionListener(e -> {
            new GuFrameList();
        });
    }
}