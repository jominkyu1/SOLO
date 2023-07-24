package net.daum.controller;

import net.daum.dao.GuDAOImp1;
import net.daum.vo.GuVO;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

public class Gu_List extends JFrame {

    JPanel panel = new JPanel(new GridLayout(1, 2));
    JTree tree;
    JTextArea textArea;
    JScrollPane treeScroll;
    DefaultTreeModel model;
    DefaultMutableTreeNode root;
    List<GuVO> arr;

    Gu_List(){
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        textArea = new JTextArea();

        tree = new JTree();
        root = new DefaultMutableTreeNode("night");
        model = new DefaultTreeModel(root);
        tree.setModel(model);
        tree.setVisibleRowCount(10);

        treeScroll = new JScrollPane(tree);
        panel.add(treeScroll);
        panel.add(textArea);
        add(panel);


        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            String nodeName = (String) node.getUserObject();

            arr.forEach(f -> {
                if(f.getGtitle().equals(nodeName)){
                    textArea.setText(f.getGcont());
                }
            });
        });
    }
    public static void main(String[] args) {
        Gu_List gl = new Gu_List();
        GuDAOImp1 gd = new GuDAOImp1();

        List<GuVO> arr = gd.getGuList();
        arr.forEach(e -> {
            System.out.printf("글번호: %d\n글쓴이: %s\n글제목: %s\n글내용: %s\n작성일자: %s\n=========\n",
                             e.getGno(), e.getGname(), e.getGtitle(), e.getGcont(), e.getGdate());

            DefaultMutableTreeNode child = new DefaultMutableTreeNode(e.getGtitle());
            gl.root.add(child);
        });

        gl.setArr(arr);
        gl.setVisible(true);
    }

    public void setArr(List<GuVO> arr){
        this.arr=arr;
    }
}