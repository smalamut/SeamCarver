/*************************************************************************
 *  Compilation:  javac PrintEnergy.java
 *  Execution:    java PrintEnergy input.png
 *  Dependencies: SeamCarver.java Picture.java StdOut.java
 *                
 *
 *  Read image from file specified as command line argument.
 *  Print energy of each pixel as calculated by SeamCarver object. 
 * 
 *************************************************************************/

public class PrintDist {

    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        StdOut.printf("%d-by-%d image\n", picture.width(), picture.height());
        
        SeamCarver sc = new SeamCarver(picture);
        
        StdOut.printf("Printing energy calculated for each pixel.\n");        

        for (int j = 0; j < sc.height(); j++) {
            for (int i = 0; i < sc.width(); i++)
                StdOut.printf("%9.0f ", sc.dist(i, j));
            StdOut.println();
        }
    }

}
