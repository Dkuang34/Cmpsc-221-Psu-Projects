/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{
    
    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    private final JPanel top = new JPanel();
    private final JPanel bottom = new JPanel();
    private final JPanel toppanel = new JPanel();
    // create the widgets for the firstLine Panel.
    private Color c1 = Color.LIGHT_GRAY;
    private Color c2 = Color.LIGHT_GRAY;        
    private final JLabel line1;
    private final JComboBox<String> shapeCB;
    private final String[] shapes1 = {"Rectangle","Oval","Line"};
    private final JButton color1;
    private final JButton color2;
    private final JButton undo;
    private final JButton clear;
    //create the widgets for the secondLine Panel.
    private final JLabel line2;
    private final JCheckBox filled;
    private final JCheckBox usegradient;
    private final JCheckBox dashed;
    private final JLabel lw;
    private final JSpinner linewidth;
    private final JLabel dl;
    private final JSpinner dashlength;
    // Variables for drawPanel.
    private final JPanel drawpanel;
    // add status label
    private final JLabel status;
    private Point mouseCoords = new Point();   
    private ArrayList<MyShapes> shapes = new ArrayList<>();
    private Point endpoint = new Point();
    private Point startpoint = new Point();
    private MyShapes lastshape = null;
    
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()        
    {
        super("Java 2D Drawings");
        setLayout(new BorderLayout());
//        // add widgets to panels
     
        // firstLine widgets
        top.setBackground(Color.CYAN);
        line1 = new JLabel("Shape: ");
        top.add(line1);
        shapeCB = new JComboBox(shapes1);
        shapeCB.setMaximumRowCount(3);
        top.add(shapeCB);
        color1 = new JButton("1st Color");
        top.add(color1);
        color2 = new JButton("2nd Color");
        top.add(color2);
        undo = new JButton("Undo");
        top.add(undo);
        clear = new JButton("Clear");
        top.add(clear);
        //add button handling 
        
        // secondLine widgets
        bottom.setBackground(Color.CYAN);
        line2 = new JLabel("Options: ");
        bottom.add(line2);
        filled = new JCheckBox("Filled");
        bottom.add(filled);
        usegradient = new JCheckBox("Use Gradient");
        bottom.add(usegradient);
        dashed = new JCheckBox("Dashed");
        bottom.add(dashed);
        lw = new JLabel("Line Width:");
        bottom.add(lw);
        linewidth = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
        bottom.add(linewidth);
        dl = new JLabel("Dash Length:");
        bottom.add(dl);
        dashlength = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
        bottom.add(dashlength);
        
        drawpanel = new DrawPanel();
        drawpanel.setBackground(Color.WHITE);
        status = new JLabel("(" + (int)mouseCoords.getX() + "," + (int)mouseCoords.getY() + ")");
                
        
        // add top panel of two panels
        toppanel.setLayout(new BorderLayout());
        toppanel.add(top,BorderLayout.NORTH);
        toppanel.add(bottom,BorderLayout.SOUTH);
        
        // add topPanel to North, drawPanel to Center, and statusLabel to South
        add(toppanel,BorderLayout.NORTH);
        add(drawpanel,BorderLayout.CENTER);
        add(status,BorderLayout.SOUTH);
        //add listeners and event handlers
        
        //Color 
        color1.addActionListener(
            new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){  
                c1 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Choose a color", c1);
            }            
            });      
        color2.addActionListener(
            new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){  
                c2 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Choose a color", c2);
            }
        });

        //undo
        undo.addActionListener(    
            new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){  
                if (!shapes.isEmpty()){
                    shapes.remove(shapes.size()-1);
                    drawpanel.repaint();
                }
            }
            });
        //clear
        clear.addActionListener(
            new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){  
                shapes.clear();
                }
            });
    }
        

    // Create event handlers, if needed

    // Create a private inner class for the DrawPanel.
        private class DrawPanel extends JPanel{
            public DrawPanel()
            {
                MouseHandler handler = new MouseHandler();
                this.addMouseMotionListener(handler);
                this.addMouseListener(handler);
            }
            
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                if (!shapes.isEmpty()) {
                    shapes.get(shapes.size() - 1).draw(g2d);
                }
                //loop through and draw each shape in the shapes arraylist
                for(MyShapes shape:shapes){
                    shape.draw(g2d);
                }
            }  
        }

        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            @Override
            public void mousePressed(MouseEvent event)
            {
                startpoint = event.getPoint();
                endpoint = startpoint;   
                Paint paint = Color.WHITE;
                Stroke stroke = new BasicStroke();
                if (usegradient.isSelected()){
                     paint = new GradientPaint(0, 0, c1, 50, 50, c2, true);            
                }
                else{
                     paint = c1;
                }
  
                if (dashed.isSelected()){   
                    float[] dl = {(Integer)dashlength.getValue()};    
                     stroke = new BasicStroke((Integer)linewidth.getValue(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dl, 0);
                }
                else{
                     stroke = new BasicStroke((Integer)linewidth.getValue(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
                }
                if (filled.isSelected()){
                    if (shapeCB.getSelectedItem().equals("Line")){
                        lastshape = new MyLine(startpoint,endpoint,paint,stroke);
                        shapes.add(lastshape);
                        drawpanel.repaint();
                    }
                    if (shapeCB.getSelectedItem().equals("Oval")){
                        lastshape = new MyOval(startpoint,endpoint,paint,stroke,true);
                        shapes.add(lastshape);
                        drawpanel.repaint();
                    }
                    if (shapeCB.getSelectedItem().equals("Rectangle")){
                        lastshape = new MyRectangle(startpoint,endpoint,paint,stroke,true);
                        shapes.add(lastshape);
                        drawpanel.repaint();
                    }
                }
                else{
                    if (shapeCB.getSelectedItem().equals("Line")){
                        lastshape = new MyLine(startpoint,endpoint,paint,stroke);
                        shapes.add(lastshape);
                        drawpanel.repaint();
                    }
                    if (shapeCB.getSelectedItem().equals("Oval")){
                        lastshape = new MyOval(startpoint,endpoint,paint,stroke,false);
                        shapes.add(lastshape);
                        drawpanel.repaint();
                    }
                    if (shapeCB.getSelectedItem().equals("Rectangle")){
                        lastshape = new MyRectangle(startpoint,endpoint,paint,stroke,false);
                        shapes.add(lastshape);
                        drawpanel.repaint();

                    drawpanel.repaint();
                }
                }  
               
               
            }
            @Override
            public void mouseReleased(MouseEvent event){  
                endpoint = event.getPoint();
                lastshape.setEndPoint(endpoint);
                drawpanel.repaint();

            }
            @Override
            public void mouseDragged(MouseEvent event)
            {
                endpoint = event.getPoint();
                lastshape.setEndPoint(endpoint);
                drawpanel.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mouseCoords = e.getPoint();
                status.setText("(" + (int)mouseCoords.getX() + "," + (int)mouseCoords.getY() + ")");
                drawpanel.repaint();
            }
        }
}

