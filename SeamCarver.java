/* Name: Sara Malamut
 * NetID: smalamut
 * Precept: P04A
 * 
 * Partner Name: Josh Moskovits
 * Partner Login: jm37
 * Partner Precept: P02
 * 
 * Compilation: javac SeamCarver.java
 * Execution: java SeamCarver
 * Dependencies: Picture.java, Color.java, IndexOutOfBoundsException,
 *               IllegalArgumentException, NullPointerException
 * 
 * Description: This program implements the mutable data type SeamCarver 
 */


import java.awt.Color;


public class SeamCarver {
      
    private double[][] energy;
    
    private int w; //original width
    private int h; //original height
    private Picture pic;
        
    // create a seam carver object based on the given picture    
    public SeamCarver(Picture picture) { 
       
        pic = new Picture(picture); //defensive copy 
        w = picture.width(); //width
        h = picture.height(); //height
        
        energy = new double[w][h]; //energy array
        for (int i = w; i < 2*w; i++) {
            for (int j = h; j < 2*h; j++) {
                energy[i - w][j - h] = calculate(i, j);
               
            }
        }
    } 

    public static void main(String[] args)
    {

        SeamCarver s = new SeamCarver()
        pic = new Picture(newPic);


    }
    
    //calculates energies
    private int calculate(int i, int j) {
        
        Color left = pic.get((i - 1) % w, j % h);
        Color right = pic.get((i + 1) % w, j % h);
        Color up = pic.get(i % w, (j - 1) % h);
        Color down = pic.get(i % w, (j + 1) % h);
        
        int deltax = (left.getRed() - right.getRed()) 
            * (left.getRed() - right.getRed());
        deltax += (left.getGreen() - right.getGreen()) 
            * (left.getGreen() - right.getGreen());
        deltax += (left.getBlue() - right.getBlue()) 
            * (left.getBlue() - right.getBlue());
        
        int deltay = (down.getRed() - up.getRed()) 
            * (down.getRed() - up.getRed());
        deltay += (down.getGreen() - up.getGreen()) 
            * (down.getGreen() - up.getGreen());
        deltay += (down.getBlue() - up.getBlue()) 
            * (down.getBlue() - up.getBlue());
        
        return deltax + deltay;
    }
    
    
     // current picture
     public Picture picture() {
         return new Picture(pic);   
     }
     
     // width of current picture
     public int width() { 
         return pic.width();
     }
     
     // height of current picture
     public int height() { 
         return pic.height();
     }
     
     // energy of pixel at column x and row y
     public double energy(int x, int y) {   
         if (x < 0 || x > (w-1) || y < 0 || y > (h-1))
             throw new java.lang.IndexOutOfBoundsException(
                                    "x or y is not in range");  
         //if ( w == 1 || 
         return energy[x][y];
     }
      
     
     // sequence of indices for horizontal seam
     public   int[] findHorizontalSeam() { 
         
         int[][] path = new int[w][h]; //keep track of path
         double[][] dist = new double[w][h]; //distances
         int[] seam = new int[w];
         
         for (int i = 0; i < h; i++) {
             dist[0][i] = energy[0][i];
         }
         
         // corner case
         if (w == 1) {
             double min = Double.MAX_VALUE;
             int place = 0;
             for (int i = 0; i < h; i++) {
                 if (energy[0][i] < min) {
                     min = energy[0][i];
                     place = i;
                 }
             }
             int[] retrn = new int[1];
             retrn[0] = place;
             return retrn;
         }
         
         double minDist = Double.MAX_VALUE;
         int x = 0;
         
         for (int i = 1; i < w; i++) { //each row
             for (int j = 0; j < h; j++) { //each element in row
                 for (int k = -1; k < 2; k++) { //compare each option
                  if ((j + k) >= 0 && (j + k) < h) { 
                     if (dist[(i - 1)][(j + k)] < minDist) {
                         minDist = dist[(i -1)][(j + k)];
                         dist[i][j] = minDist + energy[i][j]; 
                         x = (j + k);
                     }
                  }
                     
                 }
                  minDist = Double.MAX_VALUE;
                     
                 path[i][j] = x; //x cord of best option
             }       
         }
          
        
         double minVal = Double.MAX_VALUE;
         int y = 0;
         
         //find smallest row in last column
         for (int i = 0; i < h; i++) {
             if (dist[w-1][i] < minVal) {
                 minVal = dist[w-1][i];
                 y = i; // correct column
             }
         }
         
         seam[w-1] = y; //last element in path = 2
         int p = path[w - 1][y]; //= 3
         
         for (int j = w-1; j > 0; j--) { //each row     
             seam[j-1] = p;  // = 3
             p = path[j - 1][p]; // = 4     
         }
         
         return seam;
     }
     
     // sequence of indices for vertical seam
     public int[] findVerticalSeam() {  
         int[][] path = new int[w][h]; //keep track of path
         double[][] dist = new double[w][h]; //distances
         int[] seam = new int[h];
         
         for (int i = 0; i < w; i++) {
             dist[i][0] = energy[i][0];
         }
         
         // corner case
         if (h == 1) {
             double min = Double.MAX_VALUE;
             int place = 0;
             for (int i = 0; i < w; i++) {
                 if (energy[i][0] < min) {
                     min = energy[i][0];
                     place = i;
                 }
             }
             int[] retrn = new int[1];
             retrn[0] = place;
             return retrn;
         }
         
         double minDist = Double.MAX_VALUE;
         int x = 0;
         
         for (int j = 1; j < h; j++) { //each row
             for (int i = 0; i < w; i++) { //each element in row
                 for (int k = -1; k < 2; k++) { //compare each option
                  if ((i + k) >= 0 && (i + k) < w) { 
                     if (dist[(i + k)][(j-1)] < minDist) {
                         minDist = dist[(i + k)][(j-1)];
                         dist[i][j] = minDist + energy[i][j]; 
                         x = (i + k);
                     }
                  }
                     
                 }
                  minDist = Double.MAX_VALUE;
                     
                 path[i][j] = x; //x cord of best option
             }       
         }
         
               
         double minVal = Double.MAX_VALUE;
         int y = 0;
         
         //find smallest column in last row
         for (int i = 0; i < w; i++) {
             if (dist[i][h-1] < minVal) {
                 minVal = dist[i][h-1];
                 y = i; //column
             }
         }
         
         seam[h-1] = y; //last element in path = 2
         int p = path[y][h-1]; //= 3
         
         for (int j = h-1; j > 0; j--) { //each row     
             seam[j-1] = p;  // = 3
             p = path[p][j-1]; // = 4     
         }
         
         return seam;   
     }
     //check for validity of array
     private void checkHSeam(int[] seam) {
         
         if (seam[0] < 0 || seam[0] >= h) 
             throw new java.lang.IllegalArgumentException(
                              "seam[] has invalid entries");
             
        
         for (int i = 1; i < seam.length; i++) {
             if (seam[i] < 0 || seam[i] >= h || Math.abs(seam[i] - seam[i - 1]) > 1)
                     throw new java.lang.IllegalArgumentException(
                      "seam[] has invalid entries");
         }
     }
     // remove horizontal seam from current picture
     public void removeHorizontalSeam(int[] seam) { 
         
         if (seam == null) throw new java.lang.NullPointerException(
                                                   "argument null");
         if (seam.length != w) throw new java.lang.IllegalArgumentException(
                                                 "seam[] is the wrong length");
          if (h == 1) throw new java.lang.IllegalArgumentException(
                                                "height cannot equal 1");
         checkHSeam(seam);
         
         Picture newPic = new Picture(w, h - 1); //without seam
         //replacing old pic with new
         for (int i = 0; i < w; i++) {
             for (int j = 0; j < h; j++) {
                 
                 if (j != seam[i]) {
                     if (j > seam[i]) { //after seam
                         newPic.set(i, j-1, pic.get(i, j));
                     }
                     else           //before seam
                         newPic.set(i, j, pic.get(i, j));
                 } 
             }
             
         }
         h--; //decrease height
         pic = new Picture(newPic);
         //recalculating energy
         energy = new double[w][h]; //energy array
         for (int i = w; i < 2*w; i++) {
             for (int j = h; j < 2*h; j++) {
                 energy[i - w][j - h] = calculate(i, j); 
             }
         }

     }
     //check for validity of array
     private void checkVSeam(int[] seam) {
         
         if (seam[0] < 0 || seam[0] >= w) 
             throw new java.lang.IllegalArgumentException(
                      "seam[] has invalid entries");
         
         
         for (int i = 1; i < seam.length; i++) {
             if (seam[i] < 0 || seam[i] >= w || Math.abs(seam[i] - seam[i - 1]) > 1)
                 throw new java.lang.IllegalArgumentException(
               "seam[] has invalid entries");
         }
     }
     
     
     // remove vertical seam from current picture
     public void removeVerticalSeam(int[] seam) {
          if (seam == null) throw new java.lang.NullPointerException(
                                                   "argument null");
          if (seam.length != h) throw new java.lang.IllegalArgumentException(
                                                 "seam[] is the wrong length");
          if (w == 1) throw new java.lang.IllegalArgumentException(
                                                "width cannot equal 1");
          checkVSeam(seam);
         
         Picture newPic = new Picture(w - 1, h); //without seam
         //replacing old pic with new
         for (int j = 0; j < h; j++) {
             for (int i = 0; i < w; i++) {
                 
                 if (i != seam[j]) {
                     if (i > seam[j]) { //after seam
                         newPic.set(i-1, j, pic.get(i, j));
                     }
                     else           //before seam
                         newPic.set(i, j, pic.get(i, j));
                 }
             }
         }
         w--; //decrease width
         pic = new Picture(newPic);
         
         //recalculating energy
         energy = new double[w][h]; //energy array
         for (int i = w; i < 2*w; i++) {
             for (int j = h; j < 2*h; j++) {
                 energy[i - w][j - h] = calculate(i, j);
                 
             }
         }
         
     }
    
}