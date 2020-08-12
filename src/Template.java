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
        System.out.print("Please input two integers (separated by space or newline) and press enter: \n");
        int a = io.i(), b = io.i();
        int c = a + b;
        System.out.print("The answer is " + c);
    }

    public void question2() throws IOException {
        /* */
    }

}