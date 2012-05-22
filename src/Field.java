import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Field extends JPanel implements Config{

	private static final long serialVersionUID = 1L;
	private Booble boobles[][] = new Booble[BOOBLE_COUNT_BY_X][BOOBLE_COUNT_BY_Y];
	private boolean checkCrossCombination;
	private boolean soundMute = false;
	private boolean drawFieldGrid;
	private int lives;
	private JLabel livesOut=null;
	private int gameLevel;
	private JLabel levelOut=null;	
	private boolean gameOver;
	private int boobleColorsCount;
	
	Field() {

		addMouseListener(new FieldListener(this));
		setBounds(0,0,FIELD_WIDTH+FIELD_INDENT_X+1, FIELD_HEIGHT+FIELD_INDENT_Y+1);
		setDoubleBuffered(true);

		checkCrossCombination = CHECK_CROSS_COMBINATION;
		soundMute = !SOUND_ON;
		drawFieldGrid = DRAW_FIELD_LINES;
		lives = DEFAULT_LIVES_COUNT;
		gameLevel = DEFAULT_LEVEL_NUMBER;
		gameOver = false;
		boobleColorsCount = BOOBLE_COLORS_COUNT;
		fillBoobles();		
	}

	public void setLevelOutLabel(JLabel label)
	{
		levelOut = label;
	}
	
	public void setGameLevelOut(int level)
	{
		gameLevel = level;
		levelOut.setText("Уровень: "+Integer.toString(gameLevel));
	}

	public int getGameLevel()
	{
		return gameLevel;
	}	
	
	
	public void setLivesOutLabel(JLabel label)
	{
		livesOut = label;
	}
	
	public void setLivesOut(int lives)
	{
		this.lives = lives;
		livesOut.setText("Жизней: "+Integer.toString(lives));
	}

	public int getLives()
	{
		return lives;
	}	
	
	public boolean isSoundMute() {
		return soundMute;
	}

	public void setSoundMute(boolean b) {
		soundMute = b;
	}

	public void setDrawFieldGrid(boolean b)
	{
		drawFieldGrid = b;
		repaint();
	}

	public boolean getDrawFieldGrid()
	{
		return drawFieldGrid;
	}	
	
	public void setCheckCrossCombination(boolean b)
	{
		checkCrossCombination = b;
	}

	public boolean getCheckCrossCombination()
	{
		return checkCrossCombination;
	}
	
	
	private void fillBoobles()
	{
		for(int y=0; y<Config.BOOBLE_COUNT_BY_Y; y++)
		{
			for(int x=0; x<Config.BOOBLE_COUNT_BY_X; x++)
			{
				boobles[x][y] = new Booble(x,y,Color.black);
			}
		}
		changeBooblesColor();
	}
	
	private void changeBooblesColor() {
		for(int y=0; y<Config.BOOBLE_COUNT_BY_Y; y++)
		{
			for(int x=0; x<Config.BOOBLE_COUNT_BY_X; x++)
			{
				Color c = new Color(0x00,0x77,0x00);
				if(boobleColorsCount>1 && ((int)(Math.random()*10)%2)==0) c = Color.blue;
				if(boobleColorsCount>2 && ((int)(Math.random()*10)%3)==0) c = new Color(0x66,0x00,0x66);
				if(boobleColorsCount>3 && ((int)(Math.random()*10)%4)==0) c = Color.gray;
				if(boobleColorsCount>4 && ((int)(Math.random()*10)%5)==0) c = new Color(0x99,0x00,0x00);
				if(boobleColorsCount>5 && ((int)(Math.random()*10)%6)==0) c = new Color(0x99,0x00,0xFF);
				if(boobleColorsCount>6 && ((int)(Math.random()*10)%7)==0) c = new Color(0xFF,0x99,0x00);
				if(boobleColorsCount>7 && ((int)(Math.random()*10)%8)==0) c = new Color(0x00,0x00,0x80);
				if(boobleColorsCount>8 && ((int)(Math.random()*10)%9)==0) c = new Color(0x00,0x80,0x80);
				if(boobleColorsCount>9 && ((int)(Math.random()*10)%10)==0) c = Color.black;
								
				boobles[x][y].setColor(c);
			}
		}
		//boobles[0][0].setColor(Color.orange);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (getDrawFieldGrid()) drawField(g);
		drawBoobles(g);
		if (isGameOver()) gameOverPaint(g);
	}
	
	public void restartGame(){
		if(gameOver) {
			setGameLevelOut(DEFAULT_LEVEL_NUMBER);
			setLivesOut(DEFAULT_LIVES_COUNT);
			boobleColorsCount = BOOBLE_COLORS_COUNT;
		}
		
		setGameLevelOut(getGameLevel());
		setLivesOut(getLives());		
		
		gameOver = false;
		
		for(int y=0; y<Config.BOOBLE_COUNT_BY_Y; y++)
		{
			for(int x=0; x<Config.BOOBLE_COUNT_BY_X; x++)
			{
				boobles[x][y].setState(1);
			}
		}
		changeBooblesColor();
		repaint();
	}
	
	private void drawField(Graphics g)
	{
		g.setColor(DRAW_FIELD_LINES_COLOR);
		g.drawRect(FIELD_INDENT_X, FIELD_INDENT_Y, getPlayGroundWidth(), getPlayGroundHeight());
		for(int x=1; x<Config.BOOBLE_COUNT_BY_X; x++)
		{
			g.drawLine( FIELD_INDENT_X + x*Booble.getBoobleWidth(), FIELD_INDENT_Y, 
						FIELD_INDENT_X + x*Booble.getBoobleWidth(), FIELD_INDENT_Y+getPlayGroundHeight());
		}

		for(int y=1; y<Config.BOOBLE_COUNT_BY_Y; y++)
		{
			g.drawLine( FIELD_INDENT_X, FIELD_INDENT_Y + y*Booble.getBoobleHeight(),  
						FIELD_INDENT_X+getPlayGroundWidth(), FIELD_INDENT_Y + y*Booble.getBoobleHeight());
		}

	}
	
	private void drawBoobles(Graphics g)
	{
		for(Booble[] boobleRow: boobles)
		{
			for(Booble booble: boobleRow)
			{
				if(booble.getState()==1) booble.draw(g);
			}
		}
	}
	
	private int getPlayGroundWidth() {
		return Booble.getBoobleWidth() * BOOBLE_COUNT_BY_X;
	}

	private int getPlayGroundHeight() {
		return Booble.getBoobleHeight() * BOOBLE_COUNT_BY_Y;
	}

	public void checkDestroyBooble(int x, int y)
	{
		if (x>=0 && y>=0 && x<BOOBLE_COUNT_BY_X && y<BOOBLE_COUNT_BY_Y)
		{
			if (isSingleBooble(x,y))
			{
				if (getLives()>0)
				{
					setLivesOut(getLives()-1);
				}
				else
				{
					gameOver();
				}
			}
		} 
		
		destroyBooble(x, y);
	}
	
	public void destroyBooble(int x, int y)
	{
		if (x>=0 && y>=0 && x<BOOBLE_COUNT_BY_X && y<BOOBLE_COUNT_BY_Y)
		{
			Booble b = boobles[x][y];
			if( b.getState() == 1 )
			{
				b.setState(2);
				if (!isSoundMute()) (new Thread(new Audio())).start();
				b.showDestroyAnimation(getGraphics(), getBackground());
			}
			
			int threadCount;
			
			threadCount = getCheckCrossCombination() ? 8 : 4;
			
			Thread[] th = new Thread[threadCount];
			
			th[0] = new Thread(new NeighborBooble(x+1,y,b.getColor(), this));
			th[1] = new Thread(new NeighborBooble(x-1,y,b.getColor(), this));
			th[2] = new Thread(new NeighborBooble(x,y-1,b.getColor(), this));
			th[3] = new Thread(new NeighborBooble(x,y+1,b.getColor(), this));

			if (getCheckCrossCombination())
			{
				th[4] = new Thread(new NeighborBooble(x+1,y+1,b.getColor(), this));
				th[5] = new Thread(new NeighborBooble(x-1,y-1,b.getColor(), this));
				th[6] = new Thread(new NeighborBooble(x+1,y-1,b.getColor(), this));
				th[7] = new Thread(new NeighborBooble(x-1,y+1,b.getColor(), this));
			}
			
			
			for (int i=0; i<threadCount; i++) th[i].start();
			try
			{
				for (int i=0; i<threadCount; i++) th[i].join();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void gameOver() {
		gameOver = true;
		livesOut.setText("Игра окончена");
		repaint();
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean b) {
		gameOver = b;
	}
	
	
	public void gameOverPaint(Graphics g) {
		int x = 170;
		int y = 280;

		// смещение второго слова для центровки
		int ix = -148;
		int iy = 100;
		
		Font oldFont = g.getFont();

		String game = "Игра";
		String over = "Окончена";
		
		Font f = new Font("Arial Bold", Font.PLAIN, 120);
		int indent = 3;
		Color c = Color.black;
		
		textOut(g, x-indent, y, c, f, game);		  textOut(g, x+ix-indent, y+iy, c, f, over);
		textOut(g, x+indent, y, c, f, game);		  textOut(g, x+ix+indent, y+iy, c, f, over);
		textOut(g, x, y-indent, c, f, game);		  textOut(g, x+ix, y+iy-indent, c, f, over);
		textOut(g, x, y+indent, c, f, game);		  textOut(g, x+ix, y+iy+indent, c, f, over);		
		textOut(g, x+indent, y-indent, c, f, game); textOut(g, x+ix+indent, y+iy-indent, c, f, over);
		textOut(g, x-indent, y-indent, c, f, game); textOut(g, x+ix-indent, y+iy-indent, c, f, over);
		
		textOut(g, x+indent, y+indent, c, f, game); textOut(g, x+ix+indent, y+iy+indent, c, f, over);
		textOut(g, x-indent, y+indent, c, f, game); textOut(g, x+ix-indent, y+iy+indent, c, f, over);		
				
		
		c = new Color(0xFF,0x99,0x00);
		f = new Font("Arial Bold", Font.PLAIN, 120);		
		textOut(g, x, y, c, f, game);	  textOut(g, x+ix, y+iy, c, f, over);
	
		g.setFont(oldFont);
	}
	
	public void textOut(Graphics g, int x, int y, Color color, Font font, String s) {
		g.setFont(font); 
		g.setColor(color);
		g.drawString(s, x, y);
	}
	
	class NeighborBooble implements Runnable{
		int x;
		int y;
		Color color;
		Field field;
		NeighborBooble(int x, int y, Color color, Field field)
		{
			this.x=x;
			this.y=y;
			this.color=color;
			this.field = field;
		}
		public void run() {
			if (x>=0 && y>=0 && x<BOOBLE_COUNT_BY_X && y<BOOBLE_COUNT_BY_Y)
			{
				Booble b = boobles[x][y];
				if (b.getState()==1 && color.equals(b.getColor())) field.destroyBooble(x, y);
			}
		}
	}
	
	public void recalculatePositions()
	{
		boolean restartNeed=false;
		for(int y=0; y<Config.BOOBLE_COUNT_BY_Y-1; y++)
		{
			for(int x=0; x<Config.BOOBLE_COUNT_BY_X; x++)
			{
				if (boobles[x][y].getState()==1 && boobles[x][y+1].getState()==2)
				{
					boobles[x][y+1].setColor(boobles[x][y].getColor());
					boobles[x][y+1].setState(1);
					boobles[x][y].setState(2);
					restartNeed=true;
				}
				
			}
		}
		if (restartNeed) recalculatePositions();
		
		for(int x=0; x<Config.BOOBLE_COUNT_BY_X-1; x++)
		{
			boolean columnEmpty=true;
			for(int y=0; y<Config.BOOBLE_COUNT_BY_Y; y++)	{
				if (boobles[x][y].getState()==1)
				{
					columnEmpty=false;
					break;
				}
			}
			if (columnEmpty) 
			{
				for(int xn=x; xn<Config.BOOBLE_COUNT_BY_X-1;xn++) {
					for(int y=0; y<Config.BOOBLE_COUNT_BY_Y; y++) {
						if (boobles[xn+1][y].getState()==1) restartNeed=true;
						boobles[xn][y].setColor(boobles[xn+1][y].getColor());
						boobles[xn][y].setState(boobles[xn+1][y].getState());
						boobles[xn+1][y].setState(2);
					}
				}
			}
		}
		
		if (restartNeed) recalculatePositions();
		repaint();		
	}
	
	public void changeBoobleStyle()
	{
		int style = boobles[0][0].getDrawStyle();
		if (style==1) style = 2; else style=1;
				
		for(int y=0; y<Config.BOOBLE_COUNT_BY_Y; y++)
			for(int x=0; x<Config.BOOBLE_COUNT_BY_X; x++)
				boobles[x][y].setDrawStyle(style);
				
		repaint();
	}
	
	public boolean isSingleBooble(int x, int y)
	{
		boolean ret = false;
		
		if (x>=0 && y>=0 && x<BOOBLE_COUNT_BY_X && y<BOOBLE_COUNT_BY_Y)
		{
			Booble b = boobles[x][y];
			if (b.getState()==1)
			{
					ret = true;
					Booble nb; // сосед
		
					if (x>0) {
						nb = boobles[x-1][y];
						if ( (nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
					}
					
					if(ret && x<(BOOBLE_COUNT_BY_X-1))
					{
						nb = boobles[x+1][y];
						if ((nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
					}
		
					if (ret && y>0) {
						nb = boobles[x][y-1];
						if ((nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
					}
		
					if (ret && y<(BOOBLE_COUNT_BY_Y-1))
					{
						nb = boobles[x][y+1];
						if ((nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
					}
					
					if (getCheckCrossCombination())
					{
						
						
						if (ret && x>0 && y>0) {
							nb = boobles[x-1][y-1];
							if ( (nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
						}
		
						if (ret && x>0 && y<(BOOBLE_COUNT_BY_Y-1)) {
							nb = boobles[x-1][y+1];
							if ( (nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
						}
		
						if (ret && y<(BOOBLE_COUNT_BY_Y-1) && x<(BOOBLE_COUNT_BY_X-1))
						{
							nb = boobles[x+1][y+1];
							if ((nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
						}
		
						if (ret && y>0 && x<(BOOBLE_COUNT_BY_X-1)) {
							nb = boobles[x+1][y-1];
							if ( (nb.getState()==1) && nb.getColor().equals(b.getColor())) ret = false;
						}
						
						
					}
			}
			
		}
		return ret;
	}
	
	public void checkLevelEnd()
	{
		boolean levelEnd = true;
		for(int y=0; y<Config.BOOBLE_COUNT_BY_Y; y++) {
			for(int x=0; x<Config.BOOBLE_COUNT_BY_X; x++)
			{
				if (boobles[x][y].getState()==1) {
					levelEnd=false;
					break;
				}
			}
			if (!levelEnd) break;
		}
		
		if (levelEnd) {
			if  (LAST_SINGLE_BOOBLE_IS_NEXT_LEVEL) ;

			nextLevel();
		}
		
	}
	
	public void nextLevel() {
		setGameOver(false);
		setGameLevelOut(getGameLevel()+1);
		setLivesOut(getLives()+getGameLevel());
//		System.out.println(getGameLevel()+1);		
		boobleColorsCount++;
		restartGame();
	}
	
}
