package util;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageManager {
	//이미지 크기 바꾸기 & 아이콘의 이미지화
	public ImageIcon getScaledIcon(String filename,int width,int height) {
		URL url=this.getClass().getClassLoader().getResource(filename);
		
		ImageIcon icon=new ImageIcon(url);
		//이미지의 크기를 줄이는 메서드는 Image 추상클래스에서 지원하므로, 현재 아이콘을 이미지로 변경해보자
		Image img=icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon=new ImageIcon(img);
		
		return icon;
		
	}
}