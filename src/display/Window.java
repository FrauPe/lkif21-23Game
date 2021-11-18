package display;

import javax.swing.JFrame;

public class Window extends JFrame{
    
    private int width, height;

    public Window(int _width, int _height){
        width = _width;
        height = _height;
        
        createWindow();
    }
    
    private void createWindow(){
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    public int GetWidth(){
        return width;
    }
    
    public int GetHeight(){
        return height;
    }
    
}
