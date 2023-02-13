/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lanzaayuda;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import javax.help.HelpBroker;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.help.HelpSet;
import javax.swing.JOptionPane;

public class LanzaAyuda {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        
        menuBar = new JMenuBar();
        
        menu = new JMenu("Ayuda");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Contenido de Ayuda");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        HelpSet hs = obtenFicHelpset();
        HelpBroker  hb = hs.createHelpBroker();
        hb.enableHelpOnButton(menuItem, "inicio", hs);
        hb.enableHelpKey(menuItem, "inicio", hs);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("About");
        menu.add(menuItem);
        
        return menuBar;
    }
    
    public Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Aplicación Swing con Ayuda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LanzaAyuda lanzaAyuda = new LanzaAyuda();
        frame.setJMenuBar(lanzaAyuda.createMenuBar());
        frame.setContentPane(lanzaAyuda.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
    
    public HelpSet obtenFicHelpset() {
        try {
            ClassLoader cl = LanzaAyuda.class.getClassLoader();
            File file = new File(LanzaAyuda.class.getResource("help/helpset.hs").getFile());
            URL url = file.toURI().toURL();
            HelpSet hs = new HelpSet(null, url);
            return hs;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Fichero HelpSet no encontrado");
            return null;
            
        }
        
    }
    
}
