package Data;

public class boundingBox {
	private spriteInfo sprite;
	private int x1, x2, y1, y2;
	
	public boundingBox(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public boundingBox(spriteInfo sprite){	
		this.sprite = sprite;
		x1 = sprite.getCoords().getX();
		x2 = sprite.getCoords().getX() + 128;
		y1 = sprite.getCoords().getY();
		y2 = sprite.getCoords().getY() + 128;
	}
	
	public boundingBox(spriteInfo sprite, int adjustX1, int adjustX2, int adjustY1, int adjustY2){	
		this.sprite = sprite;
		x1 = sprite.getCoords().getX() + adjustX1;
		x2 = sprite.getCoords().getX() + adjustX2;
		y1 = sprite.getCoords().getY() + adjustY1;
		y2 = sprite.getCoords().getY() + adjustY2;
	}

	public int getX1(){
		return x1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getY2(){
		return y2;
	}
	
	/* Setters */
	public void setX1(int x1){
		this.x1 = x1;
	}
	
	public void setX2(int x2){
		this.x2 = x2;
	}
	
	public void setY1(int y1){
		this.y1 = y1;
	}
	
	public void setY2(int y2){
		this.y2 = y2;
	}
	
	public void setBound(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public String toString(){
		return "x1 = " + x1 + " x2 = " + x2 + " y1 = " + y1 + " y2 = " + y2;
	}

}