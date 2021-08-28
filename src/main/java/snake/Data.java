package snake;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import java.net.URL;

public class Data {
    public static URL headerURL = Data.class.getResource("statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    public static URL upURL = Data.class.getResource("statics/shang.png");
    public static URL downURL = Data.class.getResource("statics/xia.png");
    public static URL leftRL = Data.class.getResource("statics/zuo.png");
    public static URL rightURL = Data.class.getResource("statics/you.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftRL);
    public static ImageIcon right = new ImageIcon(rightURL);

    public static URL douURL = Data.class.getResource("statics/dou.png");
    public static ImageIcon food = new ImageIcon(douURL);

    public static URL bodyURL = Data.class.getResource("statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);


}
