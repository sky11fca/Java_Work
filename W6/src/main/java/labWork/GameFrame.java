package labWork;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    private ConfigPanel configPanel;
    private DrawingPanel drawingPanel;
    private ControlPanel controlPanel;

    public GameFrame()
    {
        super("Silly Java Game");
        init();
    }

    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

    public void createNewGame()
    {
        int numDots = configPanel.getNumberDots();
        drawingPanel.createBoard(numDots);
        repaint();
    }

    public DrawingPanel getDrawingPanel()
    {
        return drawingPanel;
    }
}
