
// COSC 439.003
// Grant Weedon
// PJ Emmert
// Mamadou Ndiong

package com.sample.javaapplication;
import java.io.*;
import java.util.StringTokenizer;

public class GetProcessList {
    
    private String GetProcessListData() {
        
        Process p;
        Runtime runTime;
        String process = null;
        //Attempts to read the processes running on the local system
        try { 
            System.out.println("Processes Reading is started...");
            runTime = Runtime.getRuntime();
            p = runTime.exec("tasklist");
 
            //We want to create an Inputstream to read processes
            InputStream inputStream = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
 
            //Reads each process running and saves it to a string so it can be printed later on
            String line = bufferedReader.readLine();
            process = "&";
            while (line != null) {
                line = bufferedReader.readLine();
                process += line + "&";
            }
 
            //Close the Streams
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            //Once no more processes are found, it will print that all of the processes have been read
            System.out.println("Processes are read.");
            
        } catch (IOException e) {
            System.out.println("Exception arise during the read Processes");
            e.printStackTrace();
        }
        return process;
    }
 
    private void showProcessData() {
        try {

    //Call the method For Read the process        
    String proc = GetProcessListData();
 
    //Create Streams for write processes
    //Given the filepath which you need.Its store the file at where your java file.
    
    OutputStreamWriter outputStreamWriter =
            new OutputStreamWriter(new FileOutputStream("ProcessList.txt"));
    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
 
    //Tokenize the output for write the processes
    StringTokenizer st = new StringTokenizer(proc, "&");
 
    while (st.hasMoreTokens()) {
        bufferedWriter.write(st.nextToken());  //Write the data in file
        bufferedWriter.newLine();               //Allocate new line for next line
    }
 
    //Close the outputStreams
    bufferedWriter.close();
    outputStreamWriter.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        GetProcessList gpl = new GetProcessList();
        gpl.showProcessData();
    }
}