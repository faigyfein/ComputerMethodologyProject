package vacationPlanner;
import java.util.ArrayList;
import java.util.Collections;

public class Calculator {
    // Output:
    // Friend 1 owes $10, pay Friend 2 $10
    // Friend 3 owes $5, pay Friend 2 $5

    //current output:
    //Friend 1 owes $30:
        //Pay Friend 2 $40
        //Pay Friend 4 $20
    //Friend 3 owes $50:
        //pay friend 4 $10

    public static String calculate(ArrayList<Friend> friends) {
		// Loop through ArrayList
       final double pie=calculatePie(friends);//call calculatePie() to calculate total spent
       final double slice=Math.floor((pie/friends.size()) * 100.0) / 100.0;//create slice to hold amount each friend "owes"
		ArrayList<Friend> underpaid=new ArrayList<Friend>();//create ArrayList to hold friends who overpayed
        ArrayList<Friend> overpaid=new ArrayList<Friend>();//create ArrayList to hold friends who underpayed
        ArrayList<Payment> payments=new ArrayList<Payment>();//create ArrayList of payments due
        splitFriends(friends, underpaid, overpaid, slice);//call split friends to figure out which friends belogn where
        double owes;//create owes
        double paid;//creates payed
        int friendBeingPaid=0;//create and initialize friendBeingPayed to 0
        double deserves=(overpaid.get(friendBeingPaid).getTotalSpent())-slice;//create and initialize deserves to the amount the friend being payed deserved
        for(int friend=0;friend<underpaid.size();friend++){//loop through the friends
            paid=0;//set paid to 0
            owes=slice-underpaid.get(friend).getTotalSpent();//set owes to the amount friend owes
            while(paid<owes){//check that owes is less than paid-aka friend in underpaid still owes money
                    if(owes<=deserves){//if owes is less than or equal to deserves-aka friend in underpaid owes less than friend in overpaid still deserves
                        payments.add(new Payment(underpaid.get(friend), overpaid.get(friendBeingPaid), owes));//create new Payment with friend being payed and owes as amount and add it to friend's payments field
                        deserves-=owes;//subtract amount paid(owes) from deserves
                        paid+=owes;//add owes to paid(affectively breaking out of the while loop)
                    }
                    
                    else{//aka friend in underpaid owes more than friend in overpaid still deserves
                        payments.add(new Payment(underpaid.get(friend),overpaid.get(friendBeingPaid), deserves));//create new Payment with friend being payed and deserves as amount and add it to friend's payments field
                        owes-=deserves;//subtract amount friend who overpayid deserves from owes
                        deserves=0;//set deserves to 0
                        friendBeingPaid++;//increment friend being payed
                        deserves=(overpaid.get(friendBeingPaid).getTotalSpent())-slice;//set deserves to what next friend in overpaid deserves
                    }
            }
        }
        return printPayments(payments);//call printPayments to build string of who owes who what and then returns it
	}
	public static double calculatePie(ArrayList<Friend> friends){
        double pie=0;
        for(int friend=0;friend<friends.size();friend++){
            pie+=friends.get(friend).getTotalSpent();
        }
        return pie;
    }
    public static void splitFriends(ArrayList<Friend> friends, ArrayList<Friend> underpaid, ArrayList<Friend> overpaid, double slice){
        for(int friend=0;friend<friends.size();friend++){//loop through the friends
            if(friends.get(friend).getTotalSpent()<slice){//check if friend spent less than what he owes
                underpaid.add(friends.get(friend));//add friend to underpaid
            }
            else if(friends.get(friend).getTotalSpent()>slice) {//check if friend spent more than what he owes
                overpaid.add(friends.get(friend));//add friend to overpaid
            }
        }
        Collections.reverse(underpaid);//sort underpaid so that the it goes from those who owe the most
        Collections.sort(overpaid);//sort overpaid so that it goes from those who are owed the most
    }
    public static String printPayments(ArrayList<Payment> payments){
        StringBuilder str=new StringBuilder("Payments Calculated:");//create StringBuilder object with heading
        for(int payment=0;payment<payments.size();payment++){//loop through payments ArrayList
                str.append("\n\t"+(payment + 1)+") "+payments.get(payment));//append next line with payment number, and relevant info
        }
        return str.toString();//returns str
    }
	
}
