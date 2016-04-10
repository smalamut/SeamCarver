/*************************************************************************
 *  Compilation:  javac PrintSeams.java
 *  Execution:    java PrintSeams input.png
 *  Dependencies: SeamCarver.java Picture.java StdOut.java
 *
 *  Read image from file specified as command-line argument. Print energy
 *  of each image, as well as both seams, and the total energy of each seam.
 *
 *************************************************************************/

public class PrintSeams {

    private static void printHorizontalSeam(SeamCarver sc) {        
        double totalSeamEnergy = 0;

        int[] horizontalSeam = sc.findHorizontalSeam();
        for (int j = 0; j < sc.height(); j++) {
            for (int i = 0; i < sc.width(); i++) {
                char lMarker = ' ';
                char rMarker = ' ';
                if (j == horizontalSeam[i]) {
                    lMarker = '[';
                    rMarker = ']';
                    totalSeamEnergy += sc.energy(i, j);
                }

                StdOut.printf("%c%6.0f%c ", lMarker, sc.energy(i, j), rMarker);
            }
            StdOut.println();
        }                
        
        StdOut.printf("\nTotal energy: %.0f\n\n", totalSeamEnergy);
    }


    private static void printVerticalSeam(SeamCarver sc) {
        double totalSeamEnergy = 0;

        int[] verticalSeam = sc.findVerticalSeam();
        for (int j = 0; j < sc.height(); j++) {
            for (int i = 0; i < sc.width(); i++) {
                char lMarker = ' ';
                char rMarker = ' ';
                if (i == verticalSeam[j]) {
                    lMarker = '[';
                    rMarker = ']';
                    totalSeamEnergy += sc.energy(i, j);
                }

                StdOut.printf("%c%6.0f%c ", lMarker, sc.energy(i, j), rMarker);
            }

            StdOut.println();
        }                
        
        StdOut.printf("\nTotal energy: %.0f\n\n", totalSeamEnergy);
    }

    public static void main(String[] args)
    {
        Picture picture = new Picture(args[0]);
        StdOut.printf("%d-by-%d image\n", picture.width(), picture.height());
        StdOut.println();

        //picture.show();        
        SeamCarver sc = new SeamCarver(picture);
        
        StdOut.printf("Displaying horizontal seam calculated.\n");
        printHorizontalSeam(sc);

        //remove horizontal
        sc.removeHorizontalSeam(sc.findHorizontalSeam());
        
        
        
        
        
        StdOut.printf("Displaying vertical seam calculated.\n");
        printVerticalSeam(sc); 


    }

}
