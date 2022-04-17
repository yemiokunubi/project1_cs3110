import java.util.Scanner;
import java.util.*;
import java.lang.Math;
public class Main{


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);  
    System.out.println("enter value: ");
    String num1 = sc.nextLine();
    
    // String num1 = "123.3e1";      //the string we are using
   int length = num1.length();    //length of inputed string
   int count = 0;
   while(count < 1){        //CHANGE THIS TO WHEN INPUT IS Q  
    
    // int arraylength = length;
    String[] grammar = new String[length];
     for(int i = 0; i < length; i++){
      //  System.out.println(num1.split("")[i]);
      String current = num1.split("")[i];
      if(current.equals(".")){
        grammar[i] = ".";
      }
      if(isNum(current) == true){
        grammar[i] = "D";       //Digit
      }
      if(current.equals("e")){
        grammar[i] = "e";
      }
      if(current.equals("f") || current.equals("F")){
        grammar[i] = "f";
      }
      if(current.equals("-") || current.equals("+")){
        grammar[i] = "s";   //sign
      }
       
     }
     System.out.println("grammar is " + Arrays.toString(grammar));

     String status = "pass";

    //  System.out.println("dont come here");
     if(grammar[0] == "D" ){    //1st state is a digit
      for(int i = 1; i < length; i++){
        if(grammar[i] == "e" && i != (length-1) ){     // if it is e and it is NOT LAST
          if( grammar[i+1] == "." || grammar[i-1] == "_" || grammar[i+1] == "_" || grammar[i+1] == "f"){
            System.out.println("fail");
            status = "fail";
            count = length;

          }

        }
        else if(grammar[i] == "e"){     //if its e and its the last
          System.out.println("fail");
          status = "fail";
          count = length;
        }
        else if(grammar[i] == "f" && i != length - 1){    //if its f and not last
          System.out.println("fail");
          status = "fail";
          count = length;
        }
        else if(grammar[i] == "f" && i == length - 1){
          if(grammar[i-1] == "_"){
            System.out.println("fail");
            status = "fail";
            count = length;
          }
        }
        else if(grammar[i] == "_" && i == (length - 1)){
          System.out.println("fail");
          status = "fail";
          count = length;
        }
        else if(grammar[i] == "_" && i != (length - 1) ){    //if an underscore and not last 
          if(isNum(grammar[i-1]) == false || grammar[i+1] != "D"){
            System.out.println("fail");
            status = "fail";
            count = length;
          }
        }
        else if(grammar[i] == "." && i != (length - 1)){  //checking if period and not last cuz if period is last we accept it (123.)
          if(grammar[i+1] == "." || grammar[i+1] != "D" || grammar[i-1] != "D"){
            System.out.println("cuz fail");
            status = "fail";
            count = length;
          }
        }
      }


     }
     else if(grammar[0] == "." && length > 1){     //1st state is a .
      for(int i = 1; i < length; i++){
        if(grammar[i] == "e" && i != (length-1) ){     // if it is e and it is NOT LAST
          if( grammar[i+1] == "." || grammar[i-1] == "_" || grammar[i+1] == "_" || grammar[i+1] == "f"){
            System.out.println("fail");
            status = "fail";
            count = length;

          }

        }
        else if(grammar[i] == "e"){     //if its e and its the last
          System.out.println("fail");
          status = "fail";
          count = length;
        }
        else if(grammar[i] == "f" && i != length - 1){    //if its f and not last
          System.out.println("fail");
          status = "fail";
          count = length;
        }
        else if(grammar[i] == "f" && i == length - 1){
          if(grammar[i-1] == "_"){
            System.out.println("fail");
            status = "fail";
            count = length;
          }
        }
        else if(grammar[i] == "_" && i == (length - 1)){
          System.out.println("fail");
          status = "fail";
          count = length;
        }
        else if(grammar[i] == "_" && i != (length - 1) ){    //if an underscore and not last 
          if(isNum(grammar[i-1]) == false || grammar[i+1] != "D"){
            System.out.println("fail");
            status = "fail";
            count = length;
          }
        }
        else if(grammar[i] == "." && i != (length - 1)){  //checking if period and not last cuz if period is last we accept it (123.)
          if(grammar[i-1] == "." || grammar[i+1] == "." || grammar[i+1] != "D" || grammar[i-1] != "D"){
            System.out.println("cuz fail");
            status = "fail";
            count = length;
          }
        }
      } 
    }
    else{
      System.out.println("fail");
      status = "fail";
    }

    System.out.println("status is: " + status);


     if(status == "pass"){
      convert(num1,length);

      System.out.println();

     }

 
    

    count++;
   }

  }

 

  public static boolean isNum(String num){

    if( num.equals("0") || num.equals("1") || num.equals("2")|| num.equals("3")|| num.equals("4")|| num.equals("5")|| num.equals("6")|| num.equals("7")|| num.equals("8")|| num.equals("9")){
      return true;
    }


    return false;

  }

  public static void convert(String num, int length){
   double whole = 0;
   double frac = 0;
   double exp=0;
   double combined = 0;
   double powLength = 1;
   String[] inputted = num.split("");
   boolean isE = false;
   boolean isDot =false;
   boolean negative = false;

   for(int i = 0; i < length; i++){
     if(isNum(inputted[i]) == true && (isE == false) && (isDot == false) ){
       whole = whole * 10 + Integer.parseInt(inputted[i]);
     }
     else if( (inputted[i]).equals(".") ){
       isDot = true;
       if(i == length - 1){
         System.out.println(whole +.0);
       }
     }
     else if(isNum(inputted[i]) == true && (isDot == true) && (isE == false)){
       frac = frac * 10 + Integer.parseInt(inputted[i]);
       powLength = powLength * 10;
       if( (inputted[i+1]).equals("e") || (inputted[i+1]).equals("f") ){
         frac = frac/(powLength);
         combined = whole + frac;
       }
     }
     else if( (inputted[i]).equals("e")){
      isE = true;
      if( (inputted[i+1]).equals("-") ){
        negative = true;
      }
     }
     else if( isNum(inputted[i]) == true && (isE == true) ){
       
      exp = exp * 10 + Integer.parseInt(inputted[i]);
      if( i == length -1){
        if(negative){
          exp = exp *-1;
        }
        double powerE = Math.pow(10, exp);
        combined = combined * powerE;

      }
      else if(isNum(inputted[i+1]) == false){
        if(negative){
          exp = exp *-1;
        }
        double powerE = Math.pow(10, exp);
        combined = combined * powerE;
      }

     }
     if( (inputted[i]).equals("f") && (isDot == false)){
      System.out.println(combined);
     }
     if( (inputted[i]).equals("f") && (isDot == true) && (isE == false)){
      System.out.println(combined);
     }
     if( (inputted[i]).equals("f") && (isE == true)){
      System.out.println(combined);
     }

   }
   System.out.println("floating point:" + combined);




  }

}
