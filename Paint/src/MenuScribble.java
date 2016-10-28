import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MenuScribble extends Frame {
	public MenuScribble(String s){
			 super(s);
			
			  ScrollPane pane = new ScrollPane() ; 
			 	pane.setSize(500, 500); 
			    add(pane,BorderLayout.CENTER) ;
			    pane.setBackground(Color.WHITE);
		      
			    
			 Scribble scr = new Scribble(this, 700, 700);  // прокрутка
			 pane.add(scr);
			 
			 MenuBar mb = new MenuBar() ; 
			    setMenuBar(mb); 
			    
			 Menu f = new Menu("Файл");
			 Menu v = new Menu("Вид"); 
			 	mb.add(f); 
			 	mb.add(v);
			 
			 MenuItem open = new MenuItem("Открыть...",	 new MenuShortcut(KeyEvent.VK_0));  // сtrl + 0
			 MenuItem save = new MenuItem("Сохранить",	 new MenuShortcut(KeyEvent.VK_S)); 
			 MenuItem saveAs = new MenuItem("Сохранить как...");
			 MenuItem exit = new MenuItem ("Выход",	 new MenuShortcut(KeyEvent.VK_Q)); 
			 
			 	f.add(open); 
			 	f.add(save); 
			 	f.add(saveAs);
			 	f.addSeparator();    // горизонтальна лінія
			 	f.add(exit);
			 
			 
			 open.addActionListener(new ActionListener() { 
				 public void actionPerformed(ActionEvent e){
					 FileDialog fd = new FileDialog(new Frame(), " Загрузить", FileDialog.LOAD);
					 fd.setVisible(true);
			 }
			 });
			 
			 
			 saveAs.addActionListener (new ActionListener () { 
				 public void actionPerformed(ActionEvent e){ 
					 FileDialog fd = new FileDialog(new Frame(), " Сохранить", FileDialog.SAVE); 
					 fd.setVisible(true) ;
			 }
				 });
			 
			 
			 exit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){ 
					System.exit(0);
				}
			 });
			 
			 
			 Menu c = new Menu("Цвет");
			 MenuItem clear = new MenuItem("Очистить", new MenuShortcut(KeyEvent.VK_D)); 
			 
			 v.add(c); 
			 v.add(clear);
			 
			 MenuItem red = new MenuItem("Красный"); 
			 MenuItem green = new MenuItem("Зеленый");		 
			 MenuItem blue = new MenuItem ("Синий"); 
			 MenuItem black = new MenuItem ( "Черный") ; 
			 
			 c.add (red);
			 c.add (green); 
			 c.add(blue);
			 c.add(black);
			 
			 red.addActionListener (scr) ;
			 green.addActionListener (scr) ; 
			 blue.addActionListener (scr) ;
			 black.addActionListener (scr) ; 
			 clear.addActionListener (scr) ;
			 
			 addWindowListener (new WinClose ()) ; 
			 pack() ;
			 setVisible (true) ;
	        }
	
			 class WinClose extends WindowAdapter{
			 public void windowClosing (WindowEvent e) {
			 System.exit(0) ;
			 }
			 }
			 
			 
			 
			 class Scribble extends Component implements ActionListener, MouseListener, MouseMotionListener{
				 protected int lastX, lastY, w, h;
				 protected Color currColor = Color .black; 
				 protected Frame f;
			 
			 
			 public Scribble (Frame frame, int width, int height) {
				 f = frame; w = width; h = height;
				 enableEvents(AWTEvent.MOUSE_EVENT_MASK |  AWTEvent.MOUSE_MOTION_EVENT_MASK) ; 
				 addMouseListener (this) ;        // адекваткість
				 addMouseMotionListener (this);  //  натиск кнопки миші
			 }
			 
		 public Dimension getPreferredSize() {
				 return new Dimension (w, h);
		 }
		 
		public void actionPerformed (ActionEvent event) {
			String s = event.getActionCommand(); 
			if (s.equals ("Очистить")) repaint () ;
			else 
				if (s. equals ("Красный")) currColor = Color.red; 
			else 
			    if (s.equals ("Зеленый")) currColor = Color.green; 
			else
				if (s.equals ("Синий")) currColor = Color.blue; 
		    else
				if (s.equals ("Черный")) currColor = Color.black;
	}

		public void mousePressed (MouseEvent e) {
			if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0) return ;
            lastX = e.getX();
            lastY = e.getY();
 		}
		
		
		public void mouseDragged (MouseEvent e) {
			if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0) return; 
			Graphics g = getGraphics() ;
			g.setColor(currColor);
			g.drawLine(lastX, lastY, e.getX(), e.getY()); 
			lastX = e.getX(); lastY = e.getY() ;
			}
			public void mouseReleased(MouseEvent e) {} 
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e){} 
			public void mouseExited (MouseEvent e){}
			public void mouseMoved (MouseEvent e){}
		
			}

			 
	public static void main(String[] args) {
	    new MenuScribble("Пейнт");
	    
	}

}			 
			 
			
		