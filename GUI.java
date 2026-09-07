import java.awt.*;
import java.awt.event.*;

public class GUI {

    public static
    void main(String[] args){
        Frame f = new Frame("Predator Prey");

        Panel panel = new Panel();
        panel.setBounds(0,0,1000,800);
        panel.setBackground(Color.gray);

        //Add panel
        f.add(panel);
        f.setSize(1000,800);
        f.setLayout(null);
        f.setVisible(true);

        //close the program.
        f.addWindowListener(
            new WindowAdapter(){
                public void
                    windowClosing(WindowEvent we){
                        System.exit(0);
                    }
            }
        );
      }
}