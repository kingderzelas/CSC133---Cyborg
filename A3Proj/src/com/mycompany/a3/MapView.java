package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	
	private GameWorldProxy gwp;
	
	private TextArea textMap;
	
	public MapView(GameWorldProxy gwp) {
		this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		this.gwp = gwp;
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255,0,0)));
		
		this.textMap = new TextArea();
		
		this.add(this.textMap);
	}
	
	public void update (Observable o, Object arg) {
		this.gwp = (GameWorldProxy)o;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator iterator = gwp.getGameObjectCollection().getIterator();
		
		while (iterator.hasNext())
		{
			GameObject curObject = iterator.getNext();
			if (curObject instanceof IDrawable)
			{
				((IDrawable) curObject).draw(g, pCmpRelPrnt);
			}
		}
	}
}