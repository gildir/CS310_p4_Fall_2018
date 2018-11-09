## Tasks
[back](README.md)

There are **4** tasks in this assignment. It is recommended that you implement these tasks in the given order. 

### Task 0: Read the given code (0%)

Read and familiarize yourself with the code. This will save you a lot of time later. An overview of the provided code in Detector.java is given below, but you need to read the code base yourself.

```java

//Convenient helper class representing a pair of things
private static class Pair<A,B> {...}

//A pixel is a set of locations a (aka. x, distance from the left) and b (aka. y, distance from the top)
private static class Pixel extends Pair<Integer, Integer> {...}

//Convert a pixel in an image to its ID
private static int getId(BufferedImage image, Pixel p);
    
//Convex ID for an image back to a pixel
private static Pixel getPixel(BufferedImage image, int id);

//Converts a location in an image into an id
private static int getId(BufferedImage image, int x, int y);
    
//Returns the color of a given pixel in a given
private static Color getColor(BufferedImage image, Pixel p);

//Pass 0 -> k-1 as i to get the color for the blobs 0 -> k-1
private Color getSeqColor(int i, int max);

```

There is also a class called BlobDetection.java which reads in all the command line arguments, sets up an instance of Detector, gives help if you forget the commands line args and what they do, etc.

### Task 1: Implement Disjoint Sets (the union-find data structure) (25%)

- Implement Set<T> in Set.java **(10%)**
- Implement DisjointSets<T> in DisjointSets.java **(15%)**

_Hints_
- Each Set represents a [blob](README.md#blob) of contiguous pixels
- Initially every [pixel](README.md#pixel) is its own blob
- You can use/modify the DisjointSets code from your textbook

```java
//in Set.java
//all methods, including iterator methods, must be O(1)
public class Set<T>{...} //must provide add, addAll, clear, size, and iterator
```

```java
//in DisjointSets.java
public class DisjointSets<T> {

    //You must use these variables for credit...
    //we will be "breaking in" to peak at them for testing
    //DO NOT change their names, types, etc.
    private int[] s; //the disjoint set array
    private ArrayList<Set<T>> sets; //actual data
    
    //Constructor
    public DisjointSets( ArrayList<T> data  );

    //Compute the union of two sets using rank union by size
    //returns the new root of the unioned set
    //if two sets are equal, root1 is the new root
    //Must be O(1) time (do not perform find()
    //throw IllegalArgumentException() if non-roots provided
    public int union( int root1, int root2 );

    //Find and return the root
    //Must implement path compression
    public int find( int x );
    
    //Get all the data in the same set
    //Must be O(1) time
    public Set<T> get( int root );
}
```

### Task 2: Implement Helper Methods (20%)

1. Get Distance for Color (5%)

Implement getDifference() function in Detector.java

```java
public static int getDifference(Color c1, Color c2);
```

The difference between two colors (for this assignment) is:

```
((diff_red^2) + (diff_green^2) + (diff_blue^2))
```

But scaled to be an integer between 0 and 100 where 0 is an exact match and 100 is the difference between black and white. You should floor any decimals rather than round.

For example, the difference between red and black is:

```java
//difference between white and black:
difference in red = 255
difference in green = 255
difference in blue = 255

//difference between red and black:
difference in red = 255
difference in green = 0 //black and red have the same green values
difference in blue = 0 //black and red have the same blue values

//scaled difference between red and black is: 33
```

2. Thresholding an Image (10%)

Implement thresh() function in Detector.java

```java
public static void thresh(BufferedImage image, Color c, int okDist);
```

Given a image, a color, and an okDist, recolor the image into black (the color we want) or white (not the color we want). okDist indicates the distance from c that is still considered the color. For example, if okDist is 0, the color must be an exact match, or, if okDist is 100, then the image would be recolored entirely black.

3. Get Neightbor Sets (5%)

Given an image, a disjoint set, and a pixel (defined by its id), return a pair which contains (a) the _root_ id of the blob above and (b) the _root_ id of the blob to the left.

```java
public static Pair<Integer,Integer> getNeightborSets(BufferedImage image, DisjointSets<Pixel> ds, int pixelId);
```

### Task 3: Implement the Detector (45%)

Implement the following method

```java
public void detect()
```

**High-level idea**

1. Threshold the image (this.img).
2. Initialize ```ds``` to be a new ```DisjointSets<Pixel>``` and populate it with all pixels
3. Walk through the image as if you were reading from a book (left to right, top to bottom).
  - If a pixel is part of the blob above it, union it's set it with the blob above it.
  - If a pixel is part of the blob to the left of it, union it's set with the blob to the left of it.

### Task 4: Output (10%)

Recolor all pixels in the k largest blobs (if less than k blobs found, then the total number of blobs).
      
```java
public void outputResults(String outputFileName, String outputECFileName, int k);
```

Where the largest blob should be i=0, the second largest blob would be i=1, and max would be k.

- For the console output, you will output two lines:
  1. the number of blobs colored (a value <= k) and the number of blobs detected total
  2. a list of the k biggest blobs ordered from large to small (pixel count), identified by their root
- For the image file:
  1. all blobs will be black, unless they are one of the k-biggest (after thresholding, all blobs will be black).
  2. the k-biggest blobs will be various colors chosen using the provided helper method:
  ```java private Color getSeqColor(int i, int max); ```
  3. all non-blob areas will be white (after thresholding, all non-blobs will be white).
- See [examples page](EXAMPLES.md); your program must reproduce _exactly_ the same output.

### (Optional) Task 5: Output Original Image with a "Bounding Box" (5%)

Add code to the end of outputResults() which takes the original image and outputs a "bounding box" around the largest blob, saving the result to a file named outputECFileName (a parameter to outputResults()). The bounding box must be (a) around the largest blob such that it touches up against the blob but does not overlap any pixels in the blob, (b) black, and (c) 2 pixels wide.


Code for outputting an image is already in outputResults() and you can get more information on how to draw on an image here:
- [Working with Images](https://docs.oracle.com/javase/tutorial/2d/images/index.html) -- esp. Creating and Drawing to an Image
- [Graphics2D Methods](https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html) -- esp. setStroke() and setColor()
- [Working with Geometry](https://docs.oracle.com/javase/tutorial/2d/geometry/index.html) -- esp. drawing rectangles

All of java.awt, java.awt.image, and java.awt.geom have been imported for you. A helper method to reload the original image has been provided to you:

```java
public void reloadImage();
```

Example output for the extra credit is shown on the [Examples Page](EXAMPLES.md#extra-credit-examples)
