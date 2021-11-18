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
        //Sets the size of the panel
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g){
        //Sets the background of the Panel
        g.setColor(new Color(180, 180, 180));
        g.fillRect(0, 0, width, height);
    }
    
}
