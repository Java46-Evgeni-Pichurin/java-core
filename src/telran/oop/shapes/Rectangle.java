package telran.oop.shapes;

public class Rectangle {
private int width;
private int height;
public Rectangle( int width, int height) {
	setWidth(width);
	setHeight(height);
}
/****************getters**************/
public int getWidth() {
	return width;
}
public int getHeight() {
	return height;
}
public void setWidth(int width) {
	if (width < 0) {
		width = -width;
	}
	this.width = width;
}
public void setHeight(int height) {
	if (height < 0) {
		height = -height;
	}
	this.height = height;
}
public void draw() {
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			System.out.print("*" + " ");
		}
		System.out.println();
	}
}
}
