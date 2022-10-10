package ru.vsu.cs.galimov.tasks;

import ru.vsu.cs.galimov.tasks.drawers.DDALineDrawer;
import ru.vsu.cs.galimov.tasks.drawers.GraphicsLineDrawer;
import ru.vsu.cs.galimov.tasks.drawers.GraphicsPixelDrawer;
import ru.vsu.cs.galimov.tasks.drawers.LineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    private int currentX, currentY;
    private ScreenConverter converter;
    private Line ox, oy, current;
    private Point lastP;

    public DrawPanel() {

        converter = new ScreenConverter(800,600, -2,2, 4, 4);
        ox = new Line(new RealPoint(-1,0), new RealPoint(1,0));
        oy = new Line(new RealPoint(0,-1), new RealPoint(0,1));
        current = new Line(new RealPoint(0,0), new RealPoint(0,0));

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(lastP != null){
                    Point curP = e.getPoint();
                    ScreenPoint delta = new ScreenPoint(curP.x - lastP.x,curP.y - lastP.y);
                    RealPoint deltaR = converter.s2r(delta);
                    converter.setX(deltaR.getX());
                    converter.setY(deltaR.getY());
                    lastP = curP;
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                /*currentX = e.getX();
                currentY = e.getY();*/
                ScreenPoint sp = new ScreenPoint(e.getX(), e.getY());
                current.setP2(converter.s2r(sp));
                repaint();
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastP = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastP = null;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        converter.setsHeight(getHeight());
        converter.setsWidth(getWidth());

        BufferedImage bi = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D biG = bi.createGraphics();
        biG.setColor(Color.WHITE);
        biG.fillRect(0,0,getWidth(),getHeight());

         LineDrawer ld = new GraphicsLineDrawer(biG);
        //LineDrawer ld = new DDALineDrawer(new GraphicsPixelDrawer(biG));

        biG.setColor(Color.BLACK);
        /*ld.drawLine(getWidth() / 2, getHeight() / 2, currentX, currentY);*/
        drawLine(ld,converter,ox);
        drawLine(ld,converter,oy);
        drawLine(ld,converter,current);

        g2d.drawImage(bi,0,0,null);
        biG.dispose();
    }

    private static void drawLine(LineDrawer ld,ScreenConverter sc, Line l){
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());
        ld.drawLine(p1.getX(),p1.getY(), p2.getX(), p2.getY());
    }
}
