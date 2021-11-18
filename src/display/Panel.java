package display;

import java.awt.*;
import javax.swing.JPanel;

public class Panel extends JPanel{
    
    private int width, height;
    
    public Panel(int _width, int _height){
        width = _width;
        height = _height;
    }
    
    private void createPanel(){
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }
    
}
