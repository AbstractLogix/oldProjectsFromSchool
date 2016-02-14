package musiccollectionsorter;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author omiranda
 */
public class UserInput extends JFrame
{
    
    public UserInput()
    {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setTitle("Music Collection Manager");
        
        JPanel thePanel = new JPanel();
        JLabel label1 = new JLabel(" something random ");
        
        label1.setText("next text");
        label1.setToolTipText("hey a tooltip asdfasf ");
        
        thePanel.add(label1);
        
        JButton button1 = new JButton("Artist Name");
        JButton button2 = new JButton("Album Name");
        
        thePanel.add(button1);
        thePanel.add(button2);
        
        
        JTextField textField1 = new JTextField("type here", 15);
        thePanel.add(textField1);
        
        
        
        this.add(thePanel);
        
        this.setVisible(true);
        textField1.requestFocus();
        
    }
    
    //listeners
}
