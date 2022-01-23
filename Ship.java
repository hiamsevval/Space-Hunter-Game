import javax.swing.JPanel;

public abstract class Ship extends JPanel {
	
	protected int healthPoint;
	protected int maxHealthPoint;
	
	protected abstract void hit();
}
