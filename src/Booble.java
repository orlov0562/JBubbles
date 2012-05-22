import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Booble implements Config {
	
	private int x;
	private int y;
	private int state;
	private Color color;
	private int drawStyle;
	
	Booble(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.state=1;
		this.color = color;
		this.drawStyle = BOOBLE_STYLE; 
	}
	
	public int getState(){
		return this.state;
	}

	public Color getColor(){
		return this.color;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public void setDrawStyle(int drawStyle){
		this.drawStyle = drawStyle;
	}

	public int getDrawStyle() {
		return this.drawStyle;
	}
	
	
	public void setState(int state){
			this.state=state;
	}
	
	public void draw(Graphics g) {
		drawCircleWithBorder(g, BOOBLE_BORDER, BOOBLE_BORDER_COLOR);
	}
	
	private void drawCircleWithBorder(Graphics g, int border, Color borderColor)
	{
		DrawPosition dp = new DrawPosition(x,y);
		if (DRAW_BOOBLE_BORDER)
		{
			g.setColor(borderColor);
			g.fillOval(dp.getX()-border, dp.getY()-border, dp.getWidth()+border*2, dp.getHeight()+border*2);
		}
		g.setColor(color);
		g.fillOval(dp.getX(), dp.getY(), dp.getWidth(), dp.getHeight());
		
		int clBlue=color.getBlue();
		int clRed=color.getRed();
		int clGreen=color.getGreen();

		for(int i=0; i<dp.getWidth()/2; i++)
		{
			g.setColor(new Color(clRed,clGreen, clBlue, 100));

			int fx= dp.getX()+i;
			int fy = dp.getY()+i;
			int fw = dp.getWidth()-i*2;
			int fh = dp.getHeight()-i*2;
			
			if ( drawStyle == 2 ) {
				fx= (int) ( dp.getX() + (i*1.5) );
				fy = (int) ( dp.getY() + i*2.5 );
				fw = (int) ( dp.getWidth() - i*2.5 );
				fh = dp.getHeight() - i*3;
			}
			
			g.fillOval(fx, fy, fw, fh);
			clBlue+=8; if (clBlue>255) clBlue=255;
			clRed+=8; if (clRed>255) clRed=255;
			clGreen+=8; if (clGreen>255) clGreen=255;
		}
		
	}

	class DrawPosition {
		private int x,y;
		DrawPosition(int x, int y) {this.x=x; this.y=y;}
		public int getX() { return FIELD_INDENT_X + getBoobleWidth() * x + BOOBLE_INDENT_X;}
		public int getY() { return FIELD_INDENT_Y + getBoobleHeight() * y + BOOBLE_INDENT_Y;}
		public int getWidth() { return getBoobleWidth() - BOOBLE_INDENT_X*2;}
		public int getHeight() { return getBoobleHeight() - BOOBLE_INDENT_Y*2;}
	}	
	
	public void showDestroyAnimation(Graphics g, Color background) {
		DrawPosition dp = new DrawPosition(x,y);
		int imax = (dp.getWidth()>dp.getHeight())?dp.getWidth():dp.getHeight();
		imax+=BOOBLE_BORDER+2;
		
		g.setColor(background);
		
		for (int i=10; i<=imax; i++)
		{
			int fw = i;
			int fh = i;
			
			int fx = dp.getX() + (dp.getWidth()/2) - fw/2;
			int fy = dp.getY() + (dp.getHeight()/2) - fh/2;
			
			if (i<imax)
			{
			
				int dx = (int) (Math.random()*10);
				int dy = (int) (Math.random()*10);

				int dw = (int) (Math.random()*10);
				int dh = (int) (Math.random()*10);

				if (dw<dx) dw=dx;
				if (dh<dy) dh=dy;
	
				fx+=dx;
				fy+=dy;
				fw-=dw;
				fh-=dh;
			}
			
			g.fillOval(fx, fy, fw, fh);
			
	        try {  
	        	Thread.sleep(BOOBLE_DESTROY_SPEED);  
	        } catch (Exception ex) {}
	             			
		}
		
	}
	
	public static int getBoobleWidth() {
		return FIELD_WIDTH / BOOBLE_COUNT_BY_X;
	}
	
	public static int getBoobleHeight() {
		return FIELD_HEIGHT / BOOBLE_COUNT_BY_Y;
	}
	
	public static Point getPositionByAbsolute(int ax, int ay)
	{
		int x = ( ax - FIELD_INDENT_X ) / getBoobleWidth();
		int y = ( ay - FIELD_INDENT_Y ) / getBoobleHeight();
		return new Point(x,y);
	}	
}
