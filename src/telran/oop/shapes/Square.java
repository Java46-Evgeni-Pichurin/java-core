package telran.oop.shapes;

public class Square extends Rectangle {
public Square(int width) {
	super(width, width);
}
@Override
public void setWidth(int width) {
	setHeightWidth(width);
}
@Override
public void setHeight(int height) {
	setHeightWidth(height);
}
private void setHeightWidth(int length) {
	super.setHeight(length);
	super.setWidth(length);
	
}

}
