package pizzaplace;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.*;

class MyCanvas extends JPanel implements ActionListener {
    Label myPrompt1, myPrompt2, myPrompt3;
    TextField value1, value2, value3;
    Button addButton, addPepperoniButton, addOliveButton;
    int ovalWidth = 300;
    int ovalHeight = 300;
    boolean drawPepperoni = false;
    boolean drawOlive = false;
    int numPepperoni = 1;  // Default number of pepperoni
    int numOlives = 0;     // Default number of olives

    public MyCanvas() {
        init();
    }

    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth();
        int height = getHeight();
        int x = (width - ovalWidth) / 2;
        int y = (height - ovalHeight) / 2;
        g.setColor(Color.yellow);
        g.fillOval(x, y, ovalWidth, ovalHeight);

        if (drawPepperoni) {
            g.setColor(Color.red);
            int pepperoniWidth = ovalWidth / 10;
            int pepperoniHeight = ovalHeight / 10;

            for (int i = 0; i < numPepperoni; i++) {
                double angle = 2 * Math.PI * i / numPepperoni;
                int pepperoniX = (int) (x + ovalWidth / 2 + (ovalWidth / 3 * Math.cos(angle)) - pepperoniWidth / 2);
                int pepperoniY = (int) (y + ovalHeight / 2 + (ovalHeight / 3 * Math.sin(angle)) - pepperoniHeight / 2);
                g.fillOval(pepperoniX, pepperoniY, pepperoniWidth, pepperoniHeight);
            }
        }

        if (drawOlive) {
            g.setColor(Color.black);
            int oliveWidth = ovalWidth / 20;
            int oliveHeight = ovalHeight / 20;

            for (int i = 0; i < numOlives; i++) {
                double angle = 2 * Math.PI * i / numOlives + Math.PI / numOlives;
                int oliveX = (int) (x + ovalWidth / 2 + (ovalWidth / 3.5 * Math.cos(angle)) - oliveWidth / 2);
                int oliveY = (int) (y + ovalHeight / 2 + (ovalHeight / 3.5 * Math.sin(angle)) - oliveHeight / 2);
                g.fillOval(oliveX, oliveY, oliveWidth, oliveHeight);
            }
        }
    }

    public void init() {
        myPrompt1 = new Label("Enter pizza size:");
        value1 = new TextField(10);
        myPrompt2 = new Label("Enter number of pepperoni:");
        value2 = new TextField(10);
        myPrompt3 = new Label("Enter number of olives:");
        value3 = new TextField(10);
        addButton = new Button("Change Size");
        addPepperoniButton = new Button("Add pepperoni");
        addOliveButton = new Button("Add olives");

        add(myPrompt1);
        add(value1);
        add(myPrompt2);
        add(value2);
        add(myPrompt3);
        add(value3);
        add(addButton);
        add(addPepperoniButton);
        add(addOliveButton);

        addButton.addActionListener(this);
        addPepperoniButton.addActionListener(this);
        addOliveButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            int newSize = Integer.parseInt(value1.getText());
            if (newSize > 0) {
                ovalWidth = newSize;
                ovalHeight = newSize;
                repaint();
            }
        } else if (e.getSource() == addPepperoniButton) {
            drawPepperoni = true;
            numPepperoni = Integer.parseInt(value2.getText());
            repaint();
        } else if (e.getSource() == addOliveButton) {
            drawOlive = true;
            numOlives = Integer.parseInt(value3.getText());
            repaint();
        }
    }
}

public class pizza {
    public static void main(String[] a) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 600, 600);
        window.getContentPane().add(new MyCanvas());
        window.setVisible(true);
    }
}
