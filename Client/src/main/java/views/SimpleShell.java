package views;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.ServerController;
import controllers.TransactionController;
import models.Id;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException, InterruptedException {
        IdController idController = new IdController();

        YouAreEll urll = new YouAreEll(new TransactionController(
                new MessageController(), new IdController()));

        String command;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            command = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = command.split(" ");
            List<String> commandsList = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (command.equals(""))
                continue;
            if (command.equals("exit") || command.equals("quit")) {
                System.out.println("bye!");
//                idController.getIds();
//                String r = ServerController.getServerInstance().getURL("http://zipcode.rocks:8085/ids");
//                System.out.println(r);

                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                commandsList.add(commands[i]);

            }
            System.out.println(commandsList); //***check to see if commandsList was added correctly***
            history.addAll(commandsList);
            //display history of shell with index
            int index = 1;
            if (commandsList.get(commandsList.size() - 1).equals("history")) {
                for (String s : history)
                    System.out.println((index++) + " " + s);
                continue;
            }

            // Specific Commands.

            // ids
//            if (commandsList.contains("ids")) {
            if(commandsList.get(0).equals("ids")) {
                if (commandsList.size() ==1) {
                    for (Id id : idController.getIds()) {
                        System.out.println("#"+index++);
                        System.out.println("user id -> "+id.getUid());
                        System.out.println("name -> "+id.getName());
                        System.out.println("github -> "+id.getGithub()+"\n");
                    }
                }
                if (commandsList.size() ==3) {
                    Id myId = new Id(commandsList.get(1), commandsList.get(2));
                    idController.postId(myId);
                    System.out.println("Your ID is now posted :)");
                }
                continue;
            }

            // messages
            if (commandsList.contains("messages")) {
//                String results = webber.get_messages();
//                SimpleShell.prettyPrint(results);
                continue;
            }
            // you need to add a bunch more.

            //!! command returns the last command in history
            if (commandsList.get(commandsList.size() - 1).equals("!!")) {
                pb.command(history.get(history.size() - 2));

            }//!<integer value i> command
            else if (commandsList.get(commandsList.size() - 1).charAt(0) == '!') {
                int b = Character.getNumericValue(commandsList.get(commandsList.size() - 1).charAt(1));
                if (b <= history.size())//check if integer entered isn't bigger than history size
                    pb.command(history.get(b));
            } else {
                pb.command(commandsList);
            }

            // // wait, wait, what curiousness is this?
            // Process process = pb.start();

            // //obtain the input stream
            // InputStream is = process.getInputStream();
            // InputStreamReader isr = new InputStreamReader(is);
            // BufferedReader br = new BufferedReader(isr);

            // //read output of the process
            // String line;
            // while ((line = br.readLine()) != null)
            //     System.out.println(line);
            // br.close();


            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}