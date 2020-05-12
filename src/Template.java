import java.io.*;

public class Template {

    public Template() throws IOException{
        addTwo(); // Each question is instantiated here
    }

    /**
     * This method is used to add two integers, and is used to
     * demonstrate input and output.
     * @return void
     */
    public void addTwo() throws IOException {
        System.out.print("Please input two integers and press enter: ");
        int a = io.i();
        int b = io.i();
        int c = a + b;
        io.w("The answer is " + c);
    }

    public void question2() throws IOException {
        /* */
    }

}