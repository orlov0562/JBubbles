import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldListener implements MouseListener, Config{
	Field field;
	
	FieldListener(Field field)
	{
		this.field = field;
	}
	
	class DestroyRunner implements Runnable
	{
		Field field;
		int x;
		int y;
		DestroyRunner(Field field, int x, int y)
		{
			this.field = field;
			this.x = x;
			this.y = y;
		}
	    public void run() {
	    	field.checkDestroyBooble(x,y);
	    }
	}
	
	private void destroyBubble(int x, int y)
	{
		Thread r = new Thread(new DestroyRunner(field,x,y));
		r.start();
		try {
			r.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		field.recalculatePositions();
		
		field.checkLevelEnd();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (!field.isGameOver())
		{
			Point coord = Booble.getPositionByAbsolute(e.getX(), e.getY());
			destroyBubble(coord.x, coord.y);
		} else if (e.getClickCount()>1) field.restartGame();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
