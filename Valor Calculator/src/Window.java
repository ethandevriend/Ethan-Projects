import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends JPanel implements ActionListener {

	
	private JFrame frame;
	private int WIDTH = 0;
	private int HEIGHT = 0;
	private String NAME = "";
	
	private String[] ACHIEVEMENT_LEVEL = {"None", "Keystone Explorer", "Keystone Conquerer", "Keystone Master" };
	
	private String[] ITEM = {"Helmet", "Necklace", "Shoulders", "Cape", "Chest", "Wrist", 
			"Gloves", "Belt", "Legs", "Boots", "Ring", "Trinket", "Two-Handed Weapon",
			"One Hand Agility/Strength Weapon", "One Hand Intellect Weapon", "Shield/Offhand"};
	
	private int[] UPGRADE_COST = { 475, 250, 400, 250, 475, 250, 400,  400,
			475, 400, 250, 400, 1000, 500, 750, 250};
	
	private int[] ITEM_RANKS = { 184, 187, 190, 194, 197, 200, 203, 207, 210, 213, 216, 220 };
	
	private int CURRENT_SLOT = 0;
	private int ACHIEVE_LEVEL = 0;
	
	
	JComboBox item = new JComboBox(ITEM);
	JComboBox achievement_level = new JComboBox(ACHIEVEMENT_LEVEL);
	JButton button = new JButton("Calculate Valor");
	
	
	public  Window(int x, int y, String title)
	{
		
		WIDTH = x;
		HEIGHT = y;
		NAME = title;
		
		createWindow();
	}

	
	private void createWindow() {
		
		frame = new JFrame(NAME);
		frame.setSize(HEIGHT, WIDTH);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JLabel achievement_label = new JLabel("Keystone Achievement Completed: ");
		achievement_level.addActionListener(this);
		
		JLabel item_label = new JLabel("                  Item slot to upgrade: ");
		item.addActionListener(this);
		
		button.setBounds(200, 200, 50, 50);
		button.addActionListener(this);
		
		frame.add(achievement_label);
		frame.add(achievement_level);
		frame.add(item_label);
		frame.add(item);	

		
		frame.add(button);
	
		
		frame.add(this);
		//frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == achievement_level)
		{
			JComboBox cb = (JComboBox)e.getSource();
	    	String chosen = (String)cb.getSelectedItem();
	    	cb.getSelectedIndex();
	    	ACHIEVE_LEVEL = cb.getSelectedIndex();
			//System.out.println(chosen + " : " + cb.getSelectedIndex());
		}
		
		if(e.getSource() == item)
		{
			JComboBox cb = (JComboBox)e.getSource();
	    	String chosen = (String)cb.getSelectedItem();
	    	cb.getSelectedIndex();
	    	CURRENT_SLOT = cb.getSelectedIndex();
			//System.out.println(chosen + " : " + cb.getSelectedIndex() + " cost " + UPGRADE_COST[cb.getSelectedIndex()]);
		}
		
		if(e.getSource() == button)
		{

			int amount = calculate();
			
			if(amount < 0)
				JOptionPane.showMessageDialog(frame, "Invalid item level", "Invalid item level", JOptionPane.ERROR_MESSAGE);
			if(amount == 0)
				JOptionPane.showMessageDialog(frame, "You are unable to upgrade this item.");
			if(amount >= 1)
				JOptionPane.showMessageDialog(frame, "You will need " + amount + " Valor badges for a maximum upgrade.");

			frame.setVisible(true);
		}
		
		
		
	}
	
	public int calculate()
	{
		int amount = -1;
		int max = 0;
		
		if(ACHIEVE_LEVEL == 0)
			max = 5;
		if(ACHIEVE_LEVEL == 1)
			max = 7;
		if(ACHIEVE_LEVEL == 2)
			max = 9;
		if(ACHIEVE_LEVEL == 3)
			max = 11;
		
		String s = (String)JOptionPane.showInputDialog(frame, "What is your gears current item level?","Item Level", JOptionPane.PLAIN_MESSAGE);

		
		if ((s != null) && (s.length() > 0))
		{
			for(int i = 0; i < ITEM_RANKS.length; i++)
			{
				if(Integer.parseInt(s) == ITEM_RANKS[i])
				{	
					if(max - i == 0)
						return 0;
					
					return amount = (max - i) * UPGRADE_COST[CURRENT_SLOT];
				}
			}	
		}
		

		return amount;
	}


}
