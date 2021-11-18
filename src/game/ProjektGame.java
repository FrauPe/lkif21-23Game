package game;

import display.Panel;
import display.Window;

public class ProjektGame {
    
    public static void main(String[] args) {
        Window window = new Window(1280, 720);
        window.add(new Panel(window.GetWidth(), window.GetHeight()));
    }
    
}
