import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.AbstractCollection;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

import javax.swing.JPanel;

public class Detector extends JPanel {
	//
	// Task 2.1: get the difference between two colors (5%)
	// Determines the distance between two colors
	// as a value between 0 and 100.
	//
	public static int getDifference(Color c1, Color c2) {
		//TODO: Your code here...
		//Hint: Read the color class documentation
		//for useful methods and static variables:
		//https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
		return -1; //replace this
	}
	
	//
	// Task 2.2: Threshold the pixels and recolor them (10%)
	// 
	// Color the pixels white (if the pixel is not color we want)
	// or black (if it's the color we want). okDist indicates the
	// acceptable "distance" between the pixel and the color c
	// (inclusive).
	//
	public static void thresh(BufferedImage image, Color c, int okDist) {
		int width = image.getWidth();
		int height = image.getHeight();
		//TODO: Your code here...
		//Hint: You would use image.setRGB(x,y,new_color.getRGB())
		//to change the color of a pixel (x,y) to a new color new_color
	}
	
	//
	// Task 3: Get the neighboring sets (5%)
	//
	// Given an image, a disjoint set, and a pixel (defined by its id),
	// return a pair which contains (a) the blob above and
	// (b) the blob to the left (each represented by their _root_ ids)
	//
	// If there is no above/left neighbor, then the appropriate
	// part of the pair should be null
	public static Pair<Integer,Integer> getNeighborSets(BufferedImage image, DisjointSets<Pixel> ds, int pixelId) {
		//TODO: Your code here...
		return null; //remove and replace this line
	}

	//
	// Task 4. Implement the detect algorithm (45%)
	//
	// You must use instance variables img, blobColor, okDist, ds, etc.
	//
	public void detect() {
		//TODO: Your code here...
		
		//threshold the image
		
		//make your DS data structure
		
		//walk through the image and perform
		//finds and unions where appropriate

		//Hint: the algorithm is not fast and you are processing many pixels 
		//	  (e.g., 10,000 pixel for a small 100 by 100 image)
		//	  you may want to output a "." every 100 unions so you
		//	  get some progress updates.
		//If processing seems to be taking a rediculous amount of time,
		//you probably did either rank-union or path compression incorrectly.
		
		//After this, the instance variable this.ds should contain your color blobs
		//(and non-color areas) for this.img
	}

	//
	// Task 5: Output results (10%)
	// Recolor all in the k largest blobs and save output
	//
	// You must use instance variables img, blobColor, okDist, ds, etc.
	//
	public void outputResults(String outputFileName, String outputECFileName, int k) {
		if(k<1) {
			throw new IllegalArgumentException(new String("! Error: k should be greater than 0, current k="+k));
		}
		
		//Todo: Your code here (remove this line)
		
		//get all the roots from the DS
		//Hint: You can use TreeSet<> here if you want, it's a set supported by a red-black tree
		
		//using the roots, collect all sets of pixels and sort them by size
		//Note: you may use Collections.sort() here if you want
		
		//recolor the k-largest blobs from black to a color from getSeqColor()
		//Hint: Use image.setRGB(x,y,c.getRGB()) to change the color of a pixel (x,y) to the given color "c"
		
		//and output all blobs to console
		
		//save output image -- provided
		try {
			File ouptut = new File(outputFileName);
			ImageIO.write(this.img, "png", ouptut);
			System.err.println("- Saved result to "+outputFileName);
		}
		catch (Exception e) {
			System.err.println("! Error: Failed to save image to "+outputFileName);
		}
		
		/*
		//if you're doing the EC and your output image is still this.img,
		//you can uncomment this to save this.img to the specified outputECFileName
		try {
			File ouptut = new File(outputECFileName);
			ImageIO.write(this.img, "png", ouptut);
			System.err.println("- Saved result to "+outputECFileName);
		}
		catch (Exception e) {
			System.err.println("! Error: Failed to save image to "+outputECFileName);
		}
		*/
	}
	
	//main method just for your testing
	//edit as much as you want
	public static void main(String[] args) {
		/*
		
		//Some stuff to get you started...
		
		File imageFile = new File("../input/04_Circles.png");
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(imageFile);
		}
		catch(IOException e) {
			System.err.println("! Error: Failed to read "+imageFile+", error msg: "+e);
			return;
		}
		
		Pixel p = getPixel(img, 110); //100x100 pixel image, pixel id 110
		System.out.println(p.a); //x = 10
		System.out.println(p.b); //y = 1
		System.out.println(getId(img, p)); //gets the id back (110)
		System.out.println(getId(img, p.a, p.b)); //gets the id back (110)
		
		*/
	}

	//-----------------------------------------------------------------------
	//
	// Todo: Read and provide comments, but do not change the following code
	//
	//-----------------------------------------------------------------------

	//Data
	public BufferedImage img;        //this is the 2D array of RGB pixels
	private Color blobColor;         //the color of the blob we are detecting
	private String imgFileName;      //input image file name
	private DisjointSets<Pixel> ds;  //the disjoint set
	private int okDist;              //the distance between blobColor and the pixel which "still counts" as the color

	// constructor, read image from file
	public Detector(String imgfile, Color blobColor, int okDist) {
		this.imgFileName=imgfile;
		this.blobColor = blobColor;
		this.okDist = okDist;
		
		reloadImage();
	}

	// constructor, read image from file
	public void reloadImage() {
		File imageFile = new File(this.imgFileName);
		
		try {
			this.img = ImageIO.read(imageFile);
		}
		catch(IOException e) {
			System.err.println("! Error: Failed to read "+this.imgFileName+", error msg: "+e);
			return;
		}
	}

	// JPanel function
	public void paint(Graphics g) {
		g.drawImage(this.img, 0, 0,this);
	}

	//private classes below

	//Convenient helper class representing a pair of things
	private static class Pair<A,B> {
		A a;
		B b;
		public Pair(A a, B b) {
			this.a=a;
			this.b=b;
		}
	}

	//A pixel is a set of locations a (aka. x, distance from the left) and b (aka. y, distance from the top)
	private static class Pixel extends Pair<Integer, Integer> {
		public Pixel(int x, int y) {
			super(x,y);
		}
	}

	//Convert a pixel in an image to its ID
	private static int getId(BufferedImage image, Pixel p) {
		return getId(image, p.a, p.b);
	}

	//Convex ID for an image back to a pixel
	private static Pixel getPixel(BufferedImage image, int id) {
		int y = id/image.getWidth();
		int x = id-(image.getWidth()*y);

		if(y<0 || y>=image.getHeight() || x<0 || x>=image.getWidth())
			throw new ArrayIndexOutOfBoundsException();

		return new Pixel(x,y);
	}

	//Converts a location in an image into an id
	private static int getId(BufferedImage image, int x, int y) {
		return (image.getWidth()*y)+x;
	}

	//Returns the color of a given pixel in a given image
	private static Color getColor(BufferedImage image, Pixel p) {
		return new Color(image.getRGB(p.a, p.b));
	}
	
	//Pass 0 -> k-1 as i to get the color for the blobs 0 -> k-1
	private Color getSeqColor(int i, int max) {
		if(i < 0) i = 0;
		if(i >= max) i = max-1;
		
		int r = (int)(((max-i+1)/(double)(max+1)) * blobColor.getRed());
		int g = (int)(((max-i+1)/(double)(max+1)) * blobColor.getGreen());
		int b = (int)(((max-i+1)/(double)(max+1)) * blobColor.getBlue());
		
		if(r == 0 && g == 0 && b == 0) {
			r = g = b = 10;
		}
		else if(r == 255 && g == 255 && b == 255) {
			r = g = b = 245;
		}
		
		return new Color(r, g, b);
	}
}
