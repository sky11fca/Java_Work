package labWork;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel
{
    private GameFrame frame;
    private List<Point> dots;
    private List<Line> lines;
    private static final int DOT_SIZE = 10;
    private Random rand = new Random();

    private class Line{
        Point start;
        Point end;
        Color color;

        Line(Point start, Point end, Color color)
        {
            this.start = start;
            this.end = end;
            this.color = color;
        }
    }

    public DrawingPanel(GameFrame frame) {
        this.frame = frame;
        dots = new ArrayList<>();
        lines = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.start.x, line.start.y, line.end.x, line.end.y);
        }

        for(Point p : dots) {
            g.setColor(Color.BLACK);
            g.fillOval(p.x-DOT_SIZE/2, p.y-DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
        }
    }

    public void createBoard(int numDots)
    {
        dots.clear();
        lines.clear();

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numDots; i++)
        {
            int x = rand.nextInt(width-20)+10;
            int y = rand.nextInt(height-20)+10;
            dots.add(new Point(x, y));
        }

        repaint();
    }
}
