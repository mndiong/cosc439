
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
            
            //Call the method For Read the process      
            String proc = GetRunningTasks();
            
            //Create Streams for write processes
            //Given the filepath which you need.Its store the file at where your java file.

            //Tokenize the output for write the processes
            StringTokenizer st = new StringTokenizer(proc, "&");
    
            while (st.hasMoreTokens()) {
                System.out.println(st.nextToken());     // this will print, in the terminal, the processes and task running on the machine
            }
    }
    
    public static void OpenTasks(String task) {
        Runtime app = Runtime.getRuntime();
        try {
          //Process process = app.exec(task);
          //process.destroy();
          app.exec(task);

        } catch (Exception Ex) {
            System.out.println("Error: " + Ex.toString());
        }
    }
    
    // this takes the input of the name of the app the user wants to close and does exactly that
    public static void TaskKill(String program) throws IOException{
        try {
            
            //Runtime.getRuntime().exec("taskkill /F /PID " + pid);
            Runtime rt = Runtime.getRuntime();
            rt.exec("taskkill /F /IM " + program+".exe");
            System.out.println("Task killed");
            
        } catch(IOException e){
            System.out.println("Task Kill failure");
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        int in = 0;
        int in2 = 0;
        String program;
        boolean loop = true;
        boolean loop2 = true;
        Scanner input = new Scanner(System.in);
        while(loop == true) {
            System.out.println("Do you want to view all of the processes running on your computer? Enter 1 to view, 2 to exit.");
            in = input.nextInt();
            if(in == 1) {
                GetProcessList gpl = new GetProcessList();
                gpl.showProcessData();
                loop = false;
            }
            else if(in == 2) {
                System.exit(0);
            }
            else {
                System.out.println("Invalid input. Try again.");
            }
        }
        while(loop2 == true) {
            System.out.println("Enter 1 to open a program. Enter 2 to close a program. Enter 3 to view the process list again. Enter 4 to exit.");
            in2 = input.nextInt();
            if(in2 == 1) {
                System.out.println("Enter the name of the program you would like to open.");
                program = input.next();
                OpenTasks(program);
            }
            else if(in2 == 2) {
                System.out.println("Enter the name of the program you would like to terminate.");
                program = input.next();
                TaskKill(program);
            }
            else if(in2 == 3) {
                GetProcessList gpl = new GetProcessList();
                gpl.showProcessData();
            }
            else if(in2 == 4) {
                System.exit(0);
            }
            else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }
}
