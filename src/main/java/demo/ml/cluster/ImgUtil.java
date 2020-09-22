package demo.ml.cluster;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class ImgUtil extends JFrame{

    public ImgUtil(JPanel jPanel){
        super();
        draw(jPanel);
    }

    private void draw(JPanel jPanel) {
        // 设置窗体大小
        setSize(1200, 960);
        // 设置关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置窗体打开处于屏幕中央
        setLocationRelativeTo(null);
        // 设置窗体可见
        setVisible(true);

        // 空布局，个人感觉更好控制
        jPanel.setLayout(null);
        // 设置边框
        // contentPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        // 替换默认的Container
        setContentPane(jPanel);
    }

//    public void drawDot(Graphics g) {
//        super.paint(g);//加上这一句，窗体背景色就会画出来
//        Graphics gr = g;
//        gr.setColor(Color.RED);
//        gr.fillOval(80, 80, 2, 2);
//    }

    public static JPanel drawXYbeans(List<List<XYbean>> cresult) {
        JPanel Jpanel = new JPanel() {
            @Override
            public void paint(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;

                for (List<XYbean> list: cresult) {
                    Random rand = new Random();
                    int r = rand.nextInt(256);
                    int gc = rand.nextInt(256);
                    int b = rand.nextInt(256);
                    Color color = new Color(r,gc,b);
                    g2.setColor(color);
                    for (XYbean xYbean : list) {

                        g2.fillOval(xYbean.getX(), xYbean.getY(), 2, 2);
                    }
                }

            }
        };
        return Jpanel;
    }

//    public static void main(String[] args) {
//
//        new ImgUtil();
//    }
}

