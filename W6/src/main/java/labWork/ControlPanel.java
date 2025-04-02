package labWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel
{
    private GameFrame frame;
    private JButton loadButton;
    private JButton saveButton;
    private JButton exitButton;

    public ControlPanel(GameFrame frame) {
        this.frame = frame;
        init();
    }

    private void init()
    {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(frame, "I still didn't implemented the load functionality");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "I haven't implemented save functionality");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        add(loadButton);
        add(saveButton);
        add(exitButton);
    }
}
