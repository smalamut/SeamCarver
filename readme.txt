/**********************************************************************
 *  readme.txt template                                                   
 *  SeamCarver
**********************************************************************/

Name:    Sara Malamut
Login:   smalamut
Precept: P04A

Partner name:     Josh Moskovits
Partner login:    jm37
Partner precept:  P02


/**********************************************************************
 *  Describe concisely your algorithm to compute the horizontal and
 *  vertical seam. 
 **********************************************************************/
    For vertical seam: calculated the first row's energies, stored them
    in a 2d array called "dist": for each successive row, cycled through
    and for each entry, examined three entries: above and left, above and
    right, and directly above. We then checked which of those had the
    smallest distTo entry, added the currently examined entry's energy
    to it and made that sum this entry's distTo value. We also stored
    which entry had the smallest distTo value in a 2d array called "path"
    which we then used to trace back the proper seam.



/**********************************************************************
 *  Give a formula (using tilde notation) for the running time 
 *  (in seconds) required to reduce image size by one row and a formula
 *  for the running time required to reduce image size by one column. 
 *  Both should be functions of W and H. Removal should involve exactly
 *  one call to the appropriate find method and one call to the 
 *  appropriate remove method. The randomPicture() method in SCUtility 
 *  may be useful.
 * 
 *  Justify your answer with sufficient data using large enough 
 *  W and H values.
 *  
 *  Be sure to give the leading coefficient.
 **********************************************************************/

(keep W constant) W = 500

 H           Row removal time (seconds)     Column removal time (seconds)
--------------------------------------------------------------------------
1000         .335                          .332
2000         .563                          .518
4000         .985                         1.069
8000        2.984                         3.194


(keep H constant) = 500

 W           Row removal time (seconds)     Column removal time (seconds)
--------------------------------------------------------------------------
1000         .325                           .308
2000         .504                           .518
4000        1.008                          1.043
8000        3.074                          3.257


Running time to remove one row as a function of W and H:  ~(5 * 10^-7)W*H 



Running time to remove one column as a function of W and H:  ~(5 * 10^-7)W*H 





/**********************************************************************
 * If you did one of the extra credits, describe what you 
 * did here. 
 **********************************************************************/









/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/


/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/

We have followed the proper protocol.


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/