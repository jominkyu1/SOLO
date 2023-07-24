package net.daum.controller;

import net.daum.dao.GuDAOImp1;
import net.daum.vo.GuVO;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

public class GuFrameList extends JFrame {
    //Gu_List gl = new Gu_List();
    GuDAOImp1 gd = new GuDAOImp1();
    JPanel panel = new JPanel(new GridLayout(1, 2));
    JTree tree;
    JTextArea textArea;
    JScrollPane treeScroll;
    DefaultTreeModel model;
    DefaultMutableTreeNode root;
    List<GuVO> arr;
    String nodeName;

    public GuFrameList() {
            //setLayout(new GridLayout(3,1));
            setTitle("JDBC List");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            textArea = new JTextArea();
            tree = new JTree();
            root = new DefaultMutableTreeNode("tbl_gu");
            model = new DefaultTreeModel(root);
            tree.setModel(model);
            tree.setVisibleRowCount(10);
            

            treeScroll = new JScrollPane(tree);
            panel.add(treeScroll);
            panel.add(textArea);
            add(panel);

            arr = gd.getGuList();
            treeAdd(arr);
            setVisible(true);

            tree.addTreeSelectionListener(e -> {
                DefaultMutableTreeNode node;
                node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                nodeName = (String) node.getUserObject();

                arr.forEach(f -> {
                    if (f.getGtitle().equals(nodeName)) {
                        textArea.setText(f.getGcont());
                    }
                });
            });
    }

    public void treeAdd(List<GuVO> arr){
        arr.forEach(e -> {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(e.getGtitle());
            root.add(child);
        });
    }
}
