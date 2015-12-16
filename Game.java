import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class Game extends JFrame
{
	GamePanel gamePanel;
	public Game()
	{
		gamePanel = new GamePanel(4);//轨道数，目前只支持4条
		gamePanel.setFre(50);//时钟频率
		gamePanel.setFallTime(4000);//方块下落时间
		gamePanel.setTimeWidth(100);//按中的间隔
		gamePanel.setTimePerfectWidth(25);//高级按中的间隔
		gamePanel.setFocusable(true);
		add(gamePanel);
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("Start");
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new OpenActionListener());
		menu1.add(open);
		JMenuItem play = new JMenuItem("Play");
		play.addActionListener(new PlayActionListener());
		menu1.add(play);
		menubar.add(menu1);
		Container content = getContentPane();
		content.add(menubar, BorderLayout.NORTH);
	}

	public static void main(String[] args)
	{
		Game frame = new Game();
		
		frame.setTitle("AnimationDemo");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	class OpenActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(null)
				== JFileChooser.APPROVE_OPTION)
			{
				java.io.File file = fileChooser.getSelectedFile();
				Scanner input = null;
				try
				{
					input = new Scanner(file);
				} catch (FileNotFoundException e1)
				{
					System.out.println("Can't open file");
				}
				int num=0, ni;
				gamePanel.resetEnd();
				gamePanel.stop();
				while (input.hasNext())
				{
					ni = input.nextInt();
					if (ni==-1)
					{
						num++; continue;
					}
					gamePanel.addNode(num, ni);
				}
				input.close();
			}
			else
				System.out.println("No file selected");
			
		}
	}
	
	class PlayActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gamePanel.play();
		}
	}
}


