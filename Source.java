import javax.swing.JFrame;
import java.io.File;

public class Source {

	public static void main(String[] args) {
		
		Gui_design x= new Gui_design();

		File file = new File("highScore.txt");
		try {
			boolean value = file.createNewFile();
			if (value) {
				System.out.println("The new file is created.");
			}
			else {
				System.out.println("The file already exists.");
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
}



