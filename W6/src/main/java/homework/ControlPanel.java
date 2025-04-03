package homework;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControlPanel extends JPanel
{
    private GameFrame frame;
    private JButton loadButton;
    private JButton saveButton;
    private JButton exportButton;
    private JButton exitButton;

    public ControlPanel(GameFrame frame) {
        this.frame = frame;
        init();
    }


    private void init()
    {
        setLayout(new FlowLayout());

        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exportButton = new JButton("Export as png");
        exitButton = new JButton("Exit");

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showConfirmDialog(frame, "I still didn't implemented the load functionality");

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Game Files", "dat"));
                int returnVal = fileChooser.showOpenDialog(frame);

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    frame.loadGame(fileChooser.getSelectedFile());
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, "I haven't implemented save functionality");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Game Files", "dat"));
                int returnVal = fileChooser.showSaveDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if(!file.getName().toLowerCase().endsWith(".dat")) {
                        file = new File(file.getParentFile(), file.getName()+".dat");
                    }
                    frame.saveGame(file);
                }
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
                int returnVal = fileChooser.showSaveDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if(!file.getName().toLowerCase().endsWith(".png")) {
                        file = new File(file.getParentFile(), file.getName()+".png");
                    }
                    frame.exportAsPng(file);
                }
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
        add(exportButton);
        add(exitButton);
    }
}
