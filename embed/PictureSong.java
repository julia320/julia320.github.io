package PictureSong;
import java.awt.*;
import jm.util.*;
import jm.music.data.*;
import jm.JMC;

public class PictureSong implements JMC {
	static ImageTool imTool = new ImageTool();
	
	public static void main (String[] args) 
	{
		Image bird = imTool.readImageFile("C:\\Users\\Julia\\Pictures\\bird.JPG");
		int[][][] pix = imTool.imageToPixels(bird);
		int height = 856;
		int width = 1280;
		
		//make parts so the rows can play at the same time
		Part drum = new Part("Drum",DRUM,0);
		Part bass = new Part("Bass",BASS,1);
		Part piano = new Part("Piano",PIANO,2);
		Part bells = new Part("Bells",BELLS,3);
		Part guitar = new Part("Guitar",GUITAR,4);
		
		//put the phrases from different rows into the parts
		drum.addPhrase(firstRow(pix,height,width));
		bass.addPhrase(firstRow(pix,height,width));
		piano.addPhrase(middleRow(pix,height,width));
		bells.addPhrase(lastRow(pix,height,width));
		guitar.addPhrase(lastRow(pix,height,width));
		
		//create a score to play all of the parts
		Score song = new Score();
		song.addPart(drum);
		song.addPart(bass);
		song.addPart(piano);
		song.addPart(bells);
		song.addPart(guitar);
		Play.midi(song);
	}
	
	public static Phrase firstRow (int[][][] pix, int h, int w)
	{
		//use avg for duration, RGB for pitch, loudness 100, pan left to right
		Phrase song = new Phrase(0.0);
		int x = 1;
		for (int y=0; y<w; y+=10) //every 10th pixel to make it shorter
		{
			int transp = pix[x][y][0];
			int red = pix[x][y][1];
			int green = pix[x][y][2];
			int blue = pix[x][y][3];
			int avg = (red+green+blue)/3; //brightness of the pixel
			double pan = x/pix[x].length;
			int pitch = findPitch(red);
			song.addNote(findNote(pitch,pan,avg));
		}
		return song;
	}
	
	public static Phrase middleRow (int[][][] pix, int h, int w)
	{
		Phrase song = new Phrase(0.0);
		int x = 300;
		for (int y=0; y<w; y+=10) //make it shorter
		{
			int transp = pix[x][y][0];
			int red = pix[x][y][1];
			int green = pix[x][y][2];
			int blue = pix[x][y][3];
			int avg = (red+green+blue)/3;
			double pan = x/pix[x].length;
			int pitch = findPitch(red);
			song.addNote(findNote(pitch,pan,avg));
		}
		return song;
	}
	
	public static Phrase lastRow (int[][][] pix, int h, int w)
	{
		Phrase song = new Phrase(0.0);
		int x = 855;
		for (int y=0; y<w; y+=10) //makes it shorter
		{
			int transp = pix[x][y][0];
			int red = pix[x][y][1];
			int green = pix[x][y][2];
			int blue = pix[x][y][3];
			int avg = (red+green+blue)/3;
			double pan = x/pix[x].length;;
			int pitch = findPitch(red);
			song.addNote(findNote(pitch,pan,avg));
		}
		return song;
	}
	
	public static int findPitch (int x)
	{
		//10 notes, 255/10 = 25
		int pitch = 0;
		if (0<=x && x<=25)
		{
			pitch = 60;
		}
		if (26<=x && x<=50)
		{
			pitch = 62;
		}
		if (51<=x && x<=75)
		{
			pitch = 64;
		}
		if (76<=x && x<=100)
		{
			pitch = 67;
		}
		if (101<=x && x<=126)
		{
			pitch = 69;
		}
		if (127<=x && x<=151)
		{
			pitch = 72;
		}
		if (152<=x && x<=177)
		{
			pitch = 74;
		}
		if (178<=x && x<=202)
		{
			pitch = 76;
		}
		if (203<=x && x<=228)
		{
			pitch = 79;
		}
		if (229<=x && x<=255)
		{
			pitch = 81;
		}
		return pitch;
	}
	
	public static Note findNote (int pitch, double pan, int avg)
	{
		//4 durations - WN,HN,QN,EN; WN is too long, only use the others
		//avg is brightness - avg of RGB values
		//note constructor - Note n1 = new Note(pitch, duration, dynamics, panning)
		Note n1 = new Note();
		if (0<=avg && avg<=85)
		{
			n1 = new Note(pitch, HN, 100, pan);
		}
		if (86<=avg && avg<=171)
		{
			n1 = new Note(pitch, QN, 100, pan);
		}
		if (172<=avg && avg<=255)
		{
			n1 = new Note(pitch, EN, 100, pan);
		}
		//the brighter the pixel, the shorter the note
		return n1;
	}
}