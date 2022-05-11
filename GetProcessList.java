
// COSC 439.003
// Grant Weedon
// PJ Emmert
// Mamadou Ndiong

// update package name to appropriate  package name
package cosc439term;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GetProcessList {

    private String GetRunningTasks() {
        Process proc;
        Runtime runTime;
        
        String process = null;
        
        //Attempts to read the processes running on the local system
        try { 
            System.out.println("Processes Reading is started...");
            runTime = Runtime.getRuntime();
            proc = runTime.exec("tasklist");
 
            //We want to create an Inputstream to read processes
            InputStream inputStream = proc.getInputStream();
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
            String proc = GetRunningTasks();
            
            //Create Streams for write processes
            //Given the filepath which you need.Its store the file at where your java file.

            OutputStreamWriter outputStreamWriter = 
                    new OutputStreamWriter(new FileOutputStream("ProcessList.txt"));
    
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            //Tokenize the output for write the processes
            StringTokenizer st = new StringTokenizer(proc, "&");
    
            while (st.hasMoreTokens()) {
                System.out.println(st.nextToken());     // this will print, in the terminal, the processes and task running on the machine
                bufferedWriter.write(st.nextToken());  //Write the data in file
                bufferedWriter.newLine();                 //Allocate new line for next line
            }

            //Close the outputStreams
            bufferedWriter.close();
            outputStreamWriter.close();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static void OpenTasks(String task) {
        Runtime app = Runtime.getRuntime();
        try {
          Process process = app.exec(task);
          process.destroy();
          //app.exec(task);

        } catch (Exception Ex) {
            System.out.println("Error: " + Ex.toString());
        }
    }
    
    // this takes the input of the name of the app the user wants to close and does exactly that
    public static void TaskKill(String program) throws IOException{
        try {
            
            //Runtime.getRuntime().exec("taskkill /F /PID " + pid);
            System.out.println("Task killed");
            Runtime rt = Runtime.getRuntime();
            rt.exec("taskkill /F /IM " + program+".exe");
            
        } catch(IOException e){
            System.out.println("Task Kill failure");
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        GetProcessList gpl = new GetProcessList();
        gpl.showProcessData();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the program you want to open: ");
        String program = input.nextLine();
        OpenTasks(program);
        System.out.println("Enter the program you want to close: ");
        String program2 = input.nextLine();
        TaskKill(program2);
    }
}
