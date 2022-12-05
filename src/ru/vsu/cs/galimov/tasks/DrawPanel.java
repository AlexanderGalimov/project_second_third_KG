package ru.vsu.cs.galimov.tasks;

import ru.vsu.cs.galimov.tasks.drawers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {
    private int currentX, currentY;
    private final ScreenConverter converter;
    private Line current = null;
    private Point lastP;
    private final java.util.List<Line> lines = new ArrayList<>();
    private static final List<CandleStick> candleStickList = new ArrayList<>();
    private static final double timeLength = 1;
    private double SIZE = 15;
    private boolean isInit = true;
    private final Random random = new Random();
    GridDrawing drawing;


    /*private static Line find(java.util.List<Line> all, ScreenConverter sc, ScreenPoint p, int eps){
        Line answer = null;
        for (Line l: all) {
            ScreenPoint p1 = sc.r2s(l.getP1());
            ScreenPoint p2 = sc.r2s(l.getP2());
            if((p1.getX() - eps < p.getX() && p.getX() < p2.getX()) || (p1.getX() + eps > p.getX() && p.getX() > p2.getX())){
                double ax = p1.getX() - p2.getX();
                double ay = p1.getY() - p2.getY();
                double px = p1.getX();
                double py = p1.getY();
                double qx = p.getX();
                double qy = p.getY();
                double y = (qx-px + py * ax/ay + qy*ay/ax)ж
                double x = (y-py)*ax/ay + px;
                double distance = (qx-x) * (qx-x) + (qy-y);
                if(){

                }
            }
        }
        return answer;
    }*/

    public DrawPanel() {

        converter = new ScreenConverter(800,600, -SIZE, SIZE, 2 * SIZE, 2 * SIZE);
        drawing = new GridDrawing(converter);

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(lastP != null){
                    Point curP = e.getPoint();
                    ScreenPoint delta = new ScreenPoint(-curP.x + lastP.x,-curP.y + lastP.y);
                    RealPoint deltaR = converter.s2r(delta);
                    converter.setX(deltaR.getX());
                    converter.setY(deltaR.getY());
                    lastP = curP;
                    converter.moveCorner(deltaR);
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                /*ScreenPoint sp = new ScreenPoint(e.getX(), e.getY());
                current.setP2(converter.s2r(sp));
                repaint();*/
            }
        });

        this.addMouseWheelListener(e -> {
            int count = e.getWheelRotation();
            double base = count < 0 ? 0.99 : 1.01;
            double coef = 1;
            for (int i = Math.abs(count); i > 0 ; i--) {
                coef *= base;
            }
            converter.changeScale(coef);
            repaint();
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
                if(SwingUtilities.isLeftMouseButton(e)){

                    ScreenPoint p = new ScreenPoint(e.getX(), e.getY());
                    current = new Line(converter.s2r(p), converter.s2r(p));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastP = null;
                }
                if(SwingUtilities.isLeftMouseButton(e)){
                    lines.add(current);
                    current = null;
                    repaint();
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

        LineDrawer ld = new DDALineDrawer(new GraphicsPixelDrawer(biG));
        //LineDrawer ld = new BresenhamLineDrawer(new GraphicsPixelDrawer(biG));
        //LineDrawer ld = new WULineDrawer(new GraphicsPixelDrawer(biG));

        biG.setColor(Color.BLACK);
        /*drawLine(ld,converter,ox);
        drawLine(ld,converter,oy);*/
        //drawValueOnOy(ld, converter);
        //drawValueOnOx(ld, converter);

        /*for (Line line : lines) {
            drawLine(ld, converter, line);
        }
        if(current != null){
            drawLine(ld,converter, current);
        }*/
        drawing.draw(ld,biG);

        FillRect fillRect = new FillRect(new GraphicsPixelDrawer(biG));
        CandleStick currStick;
        if(isInit){
            for (int i = 0; i < 10; i++) {
                double enterValue = random.nextInt(10) + 1;
                double deltaOut = random.nextInt(3) + 0.5;
                double deltaMax = random.nextInt(3) + 0.5;
                double deltaMin = random.nextInt(3) + 0.5;
                while (enterValue - deltaMin < 0){
                    deltaMin  = random.nextInt(3) + 0.5;
                }
                currStick = new CandleStick(new RealPoint(i * 2, enterValue), new RealPoint(i * 2, enterValue + deltaOut), new RealPoint(i * 2 + timeLength / 2,enterValue + deltaOut + deltaMax), new RealPoint(i * 2 + timeLength / 2,enterValue - deltaMin ));
                candleStickList.add(currStick);
            }
            isInit = false;
        }

        for (int i = 0; i < candleStickList.size() - 1; i++) {
            if(i == 0){
                drawCandleStick(fillRect, ld, converter, candleStickList.get(i), Color.GREEN);
            }if(candleStickList.get(i).getEnterValue().getY() > candleStickList.get(i + 1).getEnterValue().getY()){
                drawCandleStick(fillRect, ld, converter, candleStickList.get(i + 1), Color.RED);
            }

            else{
                drawCandleStick(fillRect, ld, converter, candleStickList.get(i + 1), Color.GREEN);
            }
        }

        g2d.drawImage(bi,0,0,null);
        biG.dispose();
    }

    private void drawCandleStick(FillRect fillRect,LineDrawer drawer, ScreenConverter converter, CandleStick stick, Color color){
        ScreenPoint sp1 = converter.r2s(stick.getEnterValue());
        ScreenPoint sp2 = converter.r2s(stick.getOutValue());
        ScreenPoint sp3 = converter.r2s(stick.getMaxValue());
        ScreenPoint sp4 = converter.r2s(stick.getMinValue());
        drawer.drawLine(sp1.getX(),sp1.getY(), sp2.getX(), sp2.getY());
        drawer.drawLine(sp1.getX(), sp1.getY(), 2 * (sp3.getX() - sp1.getX()) + sp1.getX(), sp1.getY());
        drawer.drawLine(sp2.getX(), sp2.getY(), 2 * (sp3.getX() - sp1.getX()) + sp2.getX(), sp2.getY());
        drawer.drawLine(2 * (sp3.getX() - sp1.getX()) + sp1.getX(), sp1.getY(), 2 * (sp3.getX() - sp1.getX()) + sp2.getX(), sp2.getY());
        drawer.drawLine(sp3.getX(), sp2.getY(), sp3.getX(), sp3.getY());
        drawer.drawLine(sp4.getX(), sp1.getY(), sp4.getX(), sp4.getY());
        fillRect.drawRect(sp2.getX(), sp2.getY(), 2 * (sp3.getX() - sp1.getX()), sp1.getY() - sp2.getY(), color);
    }
}
