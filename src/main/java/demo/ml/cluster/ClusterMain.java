package demo.ml.cluster;

import java.util.List;

public class ClusterMain {

    public static void main(String[] args) {

        int width = 600;
        int height = 400;
        int K = 13;
        XYCluster xyCluster = new XYCluster();
        for (int i = 0; i < 20000; i++) {
            int x = (int)(Math.random() * width) + 1;
            int y = (int)(Math.random() * height) + 1;
            xyCluster.addRecord(new XYbean(x, y));
        }
        xyCluster.setK(K);
        long a = System.currentTimeMillis();
        List<List<XYbean>> cresult = xyCluster.clustering();
        List<XYbean> center = xyCluster.getClusteringCenterT();
//        System.out.println(JsonUtil.parseJson(center));
        long b = System.currentTimeMillis();
        System.out.println("耗时：" + (b - a) + "ms");

        new ImgUtil(ImgUtil.drawXYbeans(cresult));
//        new ImgUtil().drawXYbeans(width, height, cresult, "d:/2.png", 0, 0);
    }
}
