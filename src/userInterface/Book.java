package userInterface;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Book {
	private String Name;
	private ImageIcon Cover;
	private String ISBN;
	public static final int smallIcon = 150;
	public static final int bigIcon = 310;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public ImageIcon getCover() {
		return Cover;
	}
	public void setCover(ImageIcon cover, int imageHeight) {
		Image coverImage = cover.getImage();
		Image scaledImage = coverImage.getScaledInstance((int) (imageHeight*0.62), imageHeight, Image.SCALE_SMOOTH);
		Cover = new ImageIcon(scaledImage);
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	
}
