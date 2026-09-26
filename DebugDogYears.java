//DogYears.java Twyford
//Read 1.2 in Runestone - then debug this code
  //HOWEVER - explain the why, post your working code and they WHY this doesn't work.
public class DebugDogYears
{
    public static void main(String[] args)
    {
        //create variables
        /*private*/ int humanyears;  //declare integer variable humanyears 
        /*private*/ int dogyears;    //declare integer variable dogyears 
        
        humanyears = 3;
        dogyears = humanyears * 7;
        System.out.println("Your dog is actually " + dogyears + " years old.");

        // Now come up with your own silly poem!
        
        /*Silly Poem: (May have forgotten to make it silly)
         * 
         * Years of person and canine,
         * 
         * together
         * however
         * 
         * always waiting for us to catch up
         * always staying and attentive
         * happiness never waning
         * tail never stopping
         * 
         * full of 
         * energy
         * full of
         * love
         * 
         * together
         * for-
         * ever
         * 
         * */
    } //close main method
} //close class

/*Explanations about Changes:
 * 
 * Why work:
 * Without private or public before the data type of variable, 
 * Java treats the variables as normal, so the code works
 * 
 * Why not work:
 * Java doesn't like it when main method has public or private
 * because everything in main method already restricted to only the main method,
 * so access modifiers (public and private) serve no purpose
 * 
 * */
