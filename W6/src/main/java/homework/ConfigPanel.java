package homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConfigPanel extends JPanel
{
    private GameFrame frame;
    private JSpinner dotsSpinner;
    private JButton addButton;

    public ConfigPanel(GameFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        setLayout(new FlowLayout());

        add(new JLabel("Nr of dots: "));
        dotsSpinner = new JSpinner(new SpinnerNumberModel(10, 3, 100, 1));
        add(dotsSpinner);

        addButton = new JButton("New Game");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.createNewGame();
            }
        });
        add(addButton);
    }

    public int getNumberDots()
    {
        return (Integer)dotsSpinner.getValue();
    }

}
