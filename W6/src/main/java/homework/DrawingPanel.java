package homework;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class DrawingPanel extends JPanel
{
    private GameFrame frame;
    private List<Point> dots;
    private List<Line> lines;
    private List<Line> p1Lines;
    private List<Line> p2Lines;
    private int currentPlayer = 1; //either 1 or 2
    private Point currentDot = null;
    private Point dragStart = null;
    private Line previewLine = null;
    private static final int DOT_SIZE = 10;
    private static final int DOT_HIT_SIZE = 15;
    private Random rand = new Random();
    private double optimalScore = 0;
    private double p1Score = 0;
    private double p2Score = 0;



    public DrawingPanel(GameFrame frame)
    {
        this.frame = frame;
        dots = new ArrayList<>();
        lines = new ArrayList<>();
        p1Lines = new ArrayList<>();
        p2Lines = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               handleMouseRelease(e);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //to draw the lines
        for (Line line : lines) {
            g2d.setColor(line.color);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(line.start.x, line.start.y, line.end.x, line.end.y);
        }

        //to draw preview lines

        if(previewLine != null)
        {
            g2d.setColor(new Color(previewLine.color.getRed(), previewLine.color.getGreen(), previewLine.color.getBlue(), 128));
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
            g2d.drawLine(previewLine.start.x, previewLine.start.y, previewLine.end.x, previewLine.end.y);
        }

        //to Draw Dots

        for(Point p : dots) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(p.x-DOT_SIZE/2, p.y-DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
        }

        //to Draw Selected Dots

        if(currentDot != null)
        {
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(currentDot.x - DOT_SIZE/2, currentDot.y-DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
        }

        drawGameStatus(g2d);
    }

    private void drawGameStatus(Graphics2D g2d)
    {
        g2d.setColor(Color.BLACK);
        g2d.drawString("Player " + currentPlayer + "'s turn", 10, 20);
        g2d.drawString("Player1: " + String.format("%.2f",p1Score), 10, 40);
        g2d.drawString("Player2: " + String.format("%.2f",p2Score), 10, 60);
        g2d.drawString("Optimal Score: " + String.format("%.2f",optimalScore), 10, 80);
    }

    private void handleMousePress(MouseEvent e)
    {
        Point point = e.getPoint();
        for(Point p : dots)
        {
            if(isPointNearDot(point, p))
            {
                currentDot = p;
                dragStart = p;
                repaint();
                return;
            }
        }
    }

    private void handleMouseRelease(MouseEvent e)
    {
        if(currentDot != null && dragStart != null)
        {
            Point point = e.getPoint();
            for(Point p : dots)
            {
                if(p != currentDot && isPointNearDot(point, p))
                {
                    addLine(currentDot, p);
                    break;
                }
            }
        }

        currentDot = null;
        dragStart = null;
        previewLine = null;
        repaint();
    }

    private void handleMouseDrag(MouseEvent e)
    {
        if(currentDot != null)
        {
            previewLine = new Line(currentDot, e.getPoint(), currentPlayer == 1 ? Color.BLUE : Color.RED);
            repaint();
        }
    }

    private boolean isPointNearDot(Point point, Point p)
    {
        return point.distance(p) <= DOT_HIT_SIZE;
    }

    private void addLine(Point start, Point end)
    {
        for(Line line : lines)
        {
            if((line.start.equals(start) && line.end.equals(end)) || (line.start.equals(end) && line.end.equals(start)))
            {
                return;
            }
        }

        Color color = currentPlayer == 1 ? Color.BLUE : Color.RED;
        Line newLine = new Line(start, end, color);
        lines.add(newLine);

        if(currentPlayer == 1)
        {
            p1Lines.add(newLine);
            p1Score+=start.distance(end);
        }
        else{
            p2Lines.add(newLine);
            p2Score+=start.distance(end);
        }

        currentPlayer=3-currentPlayer; //This method guarantees we will always alternate from 1 and 2

        //to win the game

        if(isGameComplete())
        {
            calculateOptimalScore();

            Object[] options = {"Reset Game", "Exit"};

            int choise = JOptionPane.showOptionDialog(frame, "GAME!!!\n" + "Player 1 score: "+ String.format("%.2f", p1Score) + "\n" + "Player 2 score: "+ String.format("%.2f", p2Score) + "\n" + "Optimal Score: "+ String.format("%.2f", optimalScore), "GAME!!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if(choise == 0)
            {
                resetGame();
            }
            else if(choise == 1)
            {
                frame.dispose();
            }
            //JOptionPane.showMessageDialog(frame, "GAME!!!\n" + "Player 1 score: "+ String.format("%.2f", p1Score) + "\n" + "Player 2 score: "+ String.format("%.2f", p2Score) + "\n" + "Optimal Score: "+ String.format("%.2f", optimalScore));
        }
    }

    private boolean isGameComplete()
    {
        if (dots.size() < 2) return false;

        Set<Point> visited = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(dots.get(0));

        while(!queue.isEmpty())
        {
            Point current = queue.poll();
            visited.add(current);

            for(Line line : lines)
            {
                if(line.start.equals(current) && !visited.contains(line.end)) queue.add(line.end);
                else if(line.end.equals(current) && !visited.contains(line.start)) queue.add(line.start);
            }
        }

        return visited.size() == dots.size();
    }

    private void calculateOptimalScore()
    {
        //Kruskal's algr to find the Minimum Spanning Tree

        List<Edge> edges = new ArrayList<>();

        for(int i=0; i<dots.size(); i++)
        {
            for(int j=i+1; j<dots.size(); j++)
            {
                edges.add(new Edge(i, j, dots.get(i).distance(dots.get(j))));
            }
        }

        Collections.sort(edges);

        int[] parrent = new int[dots.size()];

        for(int i=0; i<parrent.length; i++)
        {
            parrent[i] = i;
        }

        optimalScore = 0;
        int edgeAdded = 0;

        for(Edge e : edges)
        {
            int root1 = find(parrent, e.u);
            int root2 = find(parrent, e.v);

            if(root1 != root2)
            {
                optimalScore += e.weight;
                parrent[root1] = root2;
                edgeAdded++;

                if(edgeAdded == dots.size()-1)
                {
                    break;
                }
            }
        }
    }

    private int find(int[] parrent, int x)
    {
        if(parrent[x] != x){
            parrent[x] = find(parrent, parrent[x]);
        }

        return parrent[x];
    }

    public void createBoard(int numDots)
    {
        dots.clear();
        lines.clear();
        p1Lines.clear();
        p2Lines.clear();
        currentPlayer = 1;
        optimalScore = 0;
        p1Score = 0;
        p2Score = 0;

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numDots; i++)
        {
            int x = rand.nextInt(width-40)+20;
            int y = rand.nextInt(height-40)+20;
            dots.add(new Point(x, y));
        }

        repaint();
    }

    public void saveGame(File file)
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            GameState state = new GameState(dots, lines, p1Lines, p2Lines, currentPlayer, p1Score, p2Score);
            oos.writeObject(state);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(frame, "Error saving game: ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetGame()
    {
        createBoard(dots.size());
    }

    public void loadGame(File file)
    {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            GameState state = (GameState) ois.readObject();
            dots = state.dots;
            lines = state.lines;
            p1Lines = state.p1Lines;
            p2Lines = state.p2Lines;
            currentPlayer = state.currentPlayer;
            p1Score = state.p1Score;
            p2Score = state.p2Score;

            if(isGameComplete())
            {
                calculateOptimalScore();
            }

            repaint();
        }
        catch(IOException | ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(frame, "Error loading game: ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exportImage(File file)
    {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        paint(g2d);

        g2d.dispose();

        try{
            ImageIO.write(image, "png", file);
        }catch(IOException e){
            JOptionPane.showMessageDialog(frame, "Error exporting image: ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static class Line implements Serializable {
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

    private static class Edge implements Comparable<Edge> , Serializable{
        int u, v;
        double weight;

        Edge(int u, int v, double weight)
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    private static class GameState implements Serializable{
        List<Point> dots;
        List<Line> lines;
        List<Line> p1Lines;
        List<Line> p2Lines;
        int currentPlayer;
        double p1Score;
        double p2Score;

        public GameState(List<Point> dots, List<Line> lines, List<Line> p1Lines, List<Line> p2Lines, int currentPlayer, double p1Score, double p2Score) {
            this.dots = dots;
            this.lines = lines;
            this.p1Lines = p1Lines;
            this.p2Lines = p2Lines;
            this.currentPlayer = currentPlayer;
            this.p1Score = p1Score;
            this.p2Score = p2Score;
        }
    }
}
