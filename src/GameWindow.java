import java.awt.Dimension;
import java.awt.Font;
//import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameWindow extends JFrame implements Config{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Field field;
	GameWindow()
	{
		setTitle("Boobles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		field = new Field();
		
		JButton [] buttons = getButtonsList();
		
		add(field);
		
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setBounds( FIELD_WIDTH+FIELD_INDENT_X*2, 
								  FIELD_INDENT_Y+BUTTONS_HEIGHT*i+BUTTONS_INDENT_Y*i, 
								  BUTTONS_WIDTH, BUTTONS_HEIGHT);
			buttons[i].setMargin(new java.awt.Insets(1, 2, 1, 2));
			
			add(buttons[i]);
		}
		
		int labelIndent = BUTTONS_HEIGHT*buttons.length+BUTTONS_INDENT_Y*buttons.length;
		
		JLabel label = new JLabel("Жизней: "+DEFAULT_LIVES_COUNT);
		label.setFont(new Font("Arial", Font.PLAIN, LABELS_FONT_SIZE));
		label.setBounds(FIELD_WIDTH+FIELD_INDENT_X*2,  FIELD_INDENT_Y+labelIndent,  LABELS_WIDTH, LABELS_HEIGHT);
		add(label);
		field.setLivesOutLabel(label);
		
		JLabel label2 = new JLabel("Уровень: "+DEFAULT_LEVEL_NUMBER);
		label2.setFont(new Font("Arial", Font.PLAIN, LABELS_FONT_SIZE));
		label2.setBounds(FIELD_WIDTH+FIELD_INDENT_X*2,  FIELD_INDENT_Y+labelIndent+LABELS_HEIGHT+LABELS_INDENT_Y,  LABELS_WIDTH, LABELS_HEIGHT);
		add(label2);
		field.setLevelOutLabel(label2);
		
		
		setSizeAndLocation();
		setResizable(false);
		setVisible(true);
	}
	
	private JButton[] getButtonsList()
	{
		JButton[] buttons  = new JButton[5];
		
		buttons[0] = new JButton("Новая игра");
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				field.setGameOver(true);
				field.restartGame();
			}
		});

		buttons[1] = new JButton("Сделать "+(field.getCheckCrossCombination()?"посложнее":"полегче"));
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				field.setCheckCrossCombination(!field.getCheckCrossCombination());
				((JButton)e.getSource()).setText("Сделать "+(field.getCheckCrossCombination()?"посложнее":"полегче"));
				field.setGameOver(true);
				field.restartGame();
			}
		});

		buttons[2] = new JButton((field.isSoundMute()?"Включить":"Выключить")+" звук");
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				field.setSoundMute(!field.isSoundMute());
				((JButton)e.getSource()).setText((field.isSoundMute()?"Включить":"Выключить")+" звук");
			}
		});
		
		buttons[3] = new JButton("Изменить вид шариков");
		buttons[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				field.changeBoobleStyle();
			}
		});

		buttons[4] = new JButton((field.getDrawFieldGrid()?"Выключить":"Включить")+" сетку");
		buttons[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				field.setDrawFieldGrid(!field.getDrawFieldGrid());
				((JButton)e.getSource()).setText((field.getDrawFieldGrid()?"Выключить":"Включить")+" сетку");
			}
		});
		
		
		return buttons;
	}
	
	private void setSizeAndLocation()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		// размеры экрана
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		// размеры окна
		int frameWidth = FIELD_WIDTH+250;
		int frameHeight = FIELD_HEIGHT+100;
		
		// не забываем считать размеры околохолстовых областей, таких как заголовок, border-ы и т.д. 
//		Insets insets = getInsets();
		setSize(frameWidth,frameHeight);

		// позиция окна
		setLocation(screenWidth/2-frameWidth/2, screenHeight/2-frameHeight/2);		
	}
}
