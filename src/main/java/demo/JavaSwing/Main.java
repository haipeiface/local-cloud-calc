package demo.JavaSwing;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitorInputStream;
import java.io.FileInputStream;


public class Main {

    public static void main(String args[]) {
        ProgressMonitorInputStream monitor;
        try {
            monitor = new ProgressMonitorInputStream(null, "Loading ",
                    new FileInputStream("C:\\Users\\peifs\\Desktop\\FI\\debugfisms.sql"));
            while (monitor.available() > 0) {
                byte[] data = new byte[38];
                monitor.read(data);
                System.out.write(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to find file: yourFile.dat",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
