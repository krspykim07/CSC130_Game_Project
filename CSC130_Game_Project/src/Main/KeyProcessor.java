/* This will handle the "Hot Key" system. */

package Main;

import Data.boundingBox;
import Graphics.Sprites;
import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';					// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	private static stopWatchX timer = new stopWatchX(1);
	private static int i = 1,j = 2;
	private static boolean lastPress, goldKey = false;
	
	// Static Method(s)
	public static void processKey(char key){
		
		if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
			
		case '$':
			//Main.spritesTest.remove("2");
			Main.trigger = "";
			Main.dialogueBox.setTag("blank");
			
			if(lastPress) {
				Main.GoldenKey.setCoords(-128, -128);
				lastPress = false;
			}
			
			if(Main.checkCollision(Main.IUBound, Main.grasssearch1)) {
				Main.trigger = "Nothing here.";
				Main.dialogueBox.setTag("dialogueBox");
			}
			
			if(Main.checkCollision(Main.IUBound, Main.grasssearch2)) {
				if(!goldKey) {
					Main.trigger = "You found the key!";
					Main.dialogueBox.setTag("dialogueBox");
					Main.GoldenKey.setCoords(Main.IUSprite.getCoords().getX()+24, Main.IUSprite.getCoords().getY()-60);
					Main.IUSprite.setTag("IUFront01");
					goldKey = true;
					lastPress = true;
				}
			}
			
			if(Main.checkCollision(Main.IUBound, Main.gateInteract)) {
				if(!goldKey) {
					Main.trigger = "The gate is closed. You need to look for the key.";
					Main.dialogueBox.setTag("dialogueBox");
				}
				else {
					Main.trigger = "You use the key!";
					Main.dialogueBox.setTag("dialogueBox");
					Main.gateWall.setTag("blank");
					Main.gateBound.setBound(0,0,0,0);
				}
		
			}
			
			if(Main.checkCollision(Main.IUBound, Main.doorInteract)) {
				Main.trigger = "You successfully got home.";
				Main.dialogueBox.setTag("dialogueBox");
			}
			break;
			
		case 'w':
			Main.trigger = "";
			Main.dialogueBox.setTag("blank");
			if(lastPress) {
				Main.GoldenKey.setCoords(-128, -128);
				lastPress = false;
			}
			if (timer.isTimeUp()){
				Main.prevSprite.setCoords(Main.IUSprite.getCoords().getX(), Main.IUSprite.getCoords().getY());
				Main.IUSprite.getCoords().adjustY(-20);
				Main.IUSprite.setTag("IUBack0"+i);
				i++;
				if (i > 4)
					i = 1;
				timer.resetWatch();
			}
			break;
		case 'a':
			Main.trigger = "";
			Main.dialogueBox.setTag("blank");
			if(lastPress) {
				Main.GoldenKey.setCoords(-128, -128);
				lastPress = false;
			}
			if (timer.isTimeUp()){
				Main.prevSprite.setCoords(Main.IUSprite.getCoords().getX(), Main.IUSprite.getCoords().getY());
				Main.IUSprite.getCoords().adjustX(-20);
				Main.IUSprite.setTag("IULeft0"+i);
				i++;
				if (i > 4)
					i = 1;
				timer.resetWatch();
			}
			break;
		case 's':
			Main.trigger = "";
			Main.dialogueBox.setTag("blank");
			if(lastPress) {
				Main.GoldenKey.setCoords(-128, -128);
				lastPress = false;
			}
			if (timer.isTimeUp()){
				Main.prevSprite.setCoords(Main.IUSprite.getCoords().getX(), Main.IUSprite.getCoords().getY());
				Main.IUSprite.getCoords().adjustY(20);
				Main.IUSprite.setTag("IUFront0"+i);
				i++;
				if (i > 4)
					i = 1;
				timer.resetWatch();
			}
			break;
		case 'd':
			Main.trigger = "";
			Main.dialogueBox.setTag("blank");
			if(lastPress) {
				Main.GoldenKey.setCoords(-128, -128);
				lastPress = false;
			}
			if (timer.isTimeUp()){
				Main.prevSprite.setCoords(Main.IUSprite.getCoords().getX(), Main.IUSprite.getCoords().getY());
				Main.IUSprite.getCoords().adjustX(20);
				Main.IUSprite.setTag("IURight0"+i);
				i++;
				if (i > 4)
					i = 1;
				timer.resetWatch();
			}
			break;	
		}
		
	}
}