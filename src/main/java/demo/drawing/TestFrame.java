package demo.drawing;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {

    public TestFrame() {
        super();
        initGUI();
    }

    private void initGUI() {
        // 设置窗体大小
        setSize(600, 600);
        // 设置关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置窗体打开处于屏幕中央
        setLocationRelativeTo(null);
        // 设置窗体可见
        setVisible(true);

        // 默认的内容Container，可以自己替换掉
//        getContentPane();

        // 自定义内容面板
        final JPanel contentPane = new JPanel() {
            @Override
            public void paint(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.ORANGE);

                // 消除画图锯齿
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // 消除文字锯齿
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                // 坐标移动
                g2.translate(10, 10);

                // 画弧形，可以用作百分比，也可以用来画圆
                g2.fillArc(0, 200, 100, 100, 0, 100);

                // 画圆形
                g2.fillOval(0, 300, 100, 100);

                // 绘制3D矩形
                g2.fill3DRect(100, 100, 100, 100, true);

                // 画矩形，可以设定边框弧度
                g2.fillRoundRect(200, 200, 100, 100, 50, 50);

                // 绘制矩形
                g2.fillRect(300, 300, 100, 100);

                // 在原来的基础上进行复制，(0,0)是原图的左上角
                g2.copyArea(300, 300, 100, 100, 100, 0);

                // 画一个多边形，指定好多边形的几个坐标点进行连接
                // 最后一个参数，如果传少了，后面的坐标就会被舍弃
                g2.drawPolygon(new int[]{100, 200, 250, 150, 50}, new int[]{100, 100, 200, 300, 200}, 5);

                g2.setFont(new Font("微软雅黑", Font.PLAIN, 50));
                g2.shear(0.1, 0.5);

                g2.setPaint(new GradientPaint(150, 100, Color.GREEN, 200, 100, Color.BLUE, true));
                g2.drawString("hello world", 100, 100);
                g2.rotate(1.5);

                // 设置画笔粗细
                g2.setStroke(new BasicStroke(30));
                g2.drawOval(100, 100, 100, 100);
            }
        };
        // 空布局，个人感觉更好控制
        contentPane.setLayout(null);
        // 设置边框
        // contentPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        // 替换默认的Container
        setContentPane(contentPane);
    }
    public static void main(String[] args) {
        new TestFrame();
    }
}
