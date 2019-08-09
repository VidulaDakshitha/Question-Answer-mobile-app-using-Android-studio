package com.example.madforumapp;

import java.util.ArrayList;
import java.util.List;

public class DBManipulator {
    public  static List<Question> mQuestionsList = new ArrayList<Question>();

    public static void InitializeQuestions(){
        mQuestionsList.add(new Question(0, "Does C# UDP socket's ReceiveBufferSize property applies to size of a datagram or size of the message queue?","Anton Costa", "I have a C# UDP socket hosted on azure cloud that receives UDP datagrams using UdpClient.receive. There could be thousands of clients sending messages to the socket concurrently and each client sends datagrams with a size of around 1Kb.\n" +
                "\n" +
                "I am confused to whether the ReceiveBufferSize applies to the size of each datagram or if it sets the size of the receive queue underneath? By receive queue, I mean the queue that is used by the .NET runtime to enqueue datagrams from different clients that hasn't been read by UdpClient.receive yet.\n" +
                "\n" +
                "I read in some posts that the ReceiveBufferSize only applies to the size of individual datagrams, if that's the case, how would we set the size of the receive queue underneath?\n" +
                "\n" +
                "I have set the ReceiveBufferSize to 65Kb. If the ReceiveBufferSize applies to the queue size under the hood, and each client sends a 1Kb datagram at the same time, does this mean the socket can only handle data from 65 clients? If there are more clients sending at the same time the buffer overflows and datagrams are lost?" ));

        mQuestionsList.add(new Question(1, "ERROR the set accessor is inaccessible - Deducting user's score as currency","Luke William", "I have a score manager which tracks down your amount of points. And With these points I want the user to be able to purchase upgrades for his character.\n" +
                "\n" +
                "But every time I try to access the user's current score I keep getting the same error message.\n" +
                "\n" +
                "If you look at the Upgrademenu Document underneath you can see that I'm trying to do the following \" // ScoreManager.Score -= upgradeCost; \" The // is there because when I activate it I get the error message:" ));

        mQuestionsList.add(new Question(2, "does {} wrapped around my variables in php reduce sql-injection risk?","Lakshan Perera", "I am writting a peice of code for a simple login page using php and mysql so trying to pentest it myself, i used curly braces to wrap around my variables $password and $username in my query and it totally blocked my attempts to bypass it.\n" +
                "i know i can use mysqli_real_escape_string and prepared statements and im not asking how to secure my code in here , i want to check it this way and know how does a hacker penetrate this exact code. i tried passing ' or 1=1 -- - just like when there wasn't a curly brace around variable but but it didnt work also tried }' or 1=1 -- - but couldnt bypass it .so the question is that curly braces inhance the security? and if not what is the payload to inject" ));
        mQuestionsList.add(new Question(3, "Does C# UDP socket's ReceiveBufferSize property applies to size of a datagram or size of the message queue?","Anton Costa", "I have a C# UDP socket hosted on azure cloud that receives UDP datagrams using UdpClient.receive. There could be thousands of clients sending messages to the socket concurrently and each client sends datagrams with a size of around 1Kb.\n" +
                "\n" +
                "I am confused to whether the ReceiveBufferSize applies to the size of each datagram or if it sets the size of the receive queue underneath? By receive queue, I mean the queue that is used by the .NET runtime to enqueue datagrams from different clients that hasn't been read by UdpClient.receive yet.\n" +
                "\n" +
                "I read in some posts that the ReceiveBufferSize only applies to the size of individual datagrams, if that's the case, how would we set the size of the receive queue underneath?\n" +
                "\n" +
                "I have set the ReceiveBufferSize to 65Kb. If the ReceiveBufferSize applies to the queue size under the hood, and each client sends a 1Kb datagram at the same time, does this mean the socket can only handle data from 65 clients? If there are more clients sending at the same time the buffer overflows and datagrams are lost?" ));

        mQuestionsList.add(new Question(4, "ERROR the set accessor is inaccessible - Deducting user's score as currency","Luke William", "I have a score manager which tracks down your amount of points. And With these points I want the user to be able to purchase upgrades for his character.\n" +
                "\n" +
                "But every time I try to access the user's current score I keep getting the same error message.\n" +
                "\n" +
                "If you look at the Upgrademenu Document underneath you can see that I'm trying to do the following \" // ScoreManager.Score -= upgradeCost; \" The // is there because when I activate it I get the error message:" ));

        mQuestionsList.add(new Question(5, "does {} wrapped around my variables in php reduce sql-injection risk?","Lakshan Perera", "I am writting a peice of code for a simple login page using php and mysql so trying to pentest it myself, i used curly braces to wrap around my variables $password and $username in my query and it totally blocked my attempts to bypass it.\n" +
                "i know i can use mysqli_real_escape_string and prepared statements and im not asking how to secure my code in here , i want to check it this way and know how does a hacker penetrate this exact code. i tried passing ' or 1=1 -- - just like when there wasn't a curly brace around variable but but it didnt work also tried }' or 1=1 -- - but couldnt bypass it .so the question is that curly braces inhance the security? and if not what is the payload to inject" ));

    }
}
