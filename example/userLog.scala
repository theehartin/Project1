package example

import scala.io.Source
import java.io.File
import java.io.PrintWriter
import java.io.FileOutputStream

//val d = new Directory

//object userLogClass {
class userLogClass {
    //def main(args: Array[String]){

    var usernameGlobal = ""
    var passwordGlobal = ""
    var workingMap = scala.collection.mutable.Map(""->"")
    var infoFile = ""
    var adminDirectory = false

    //Set global variables (p = passed)
    def globalSet(username: String, password: String, pworkingMap: scala.collection.mutable.Map[String,String], pinfoFile: String, padminDirectory: Boolean){
     usernameGlobal = username
      passwordGlobal = password
      workingMap = pworkingMap
      infoFile = pinfoFile
      adminDirectory =  padminDirectory

      //println(usernameGlobal + passwordGlobal + workingMap + infoFile + adminDirectory)   
    }
          
    
/* 
   // 
def logIn(){ 
      println();println()

      //If an admin logs in, logs out, and then a user attmepts to log in without closing the program  
      adminDirectory = false

      //Admin or User
      val usertype = scala.io.StdIn.readLine("Admin or User login?: ").trim.toLowerCase()
      usertype match {
        case "exit" => 
        case "admin"|"administrator" => adminLogin()
        case "user" => userLogin()
        case _ => {
          println()
          println("I'm sorry that's not an option")
          logIn()
        }
      }

      def userLogin(){
        var usernameConfirm = false

        //File location needed as string for later funcitons
        var fileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"//"/home/maria_dev/userInfo.txt"

        //Create Map from file
        var userFile = Source.fromFile(fileLocation)
        var intermediateUserFile =
          for {
            line <-  userFile.getLines
            split = line.split(",").map(_.trim).toList
            name = split(0)
            password = split(1)
          } yield (name -> password)//End of "intermediateUserFile" initialization
        var userInfo = intermediateUserFile.toMap

        //Assigning map to a mutable collection
        workingMap = collection.mutable.Map(userInfo.toSeq: _*)

        //Checking to provided name against records
        val usernameCheck = scala.io.StdIn.readLine("Please enter username: ").trim.toLowerCase
        usernameCheck match {
          case "exit" => logIn()
          case _ => {
            for ((k,v) <- userInfo) {
              if (usernameCheck == k ){
                println("Success!")
                usernameConfirm = true

                //Confirm password
                passwordConfirm(k ,v, fileLocation)           
              }// End of username match "if (usernameCheck == k )"       
            }//End of "for ((k,v) <- userInfo)"

            if (usernameConfirm == false){
              println()
              println("I'm sorry, that's not a recognized username")
              userLogin()
            }//End of "if (valid == 0)"
          }//End of 'case _'
        }//End of 'usernameCheck'     
      }//End of userLogIn


      def adminLogin(){
        //See userLogin for documentation. 
        //Differnce: this path activates an Admin Directory
        
        println()
        var usernameConfirm = false
        var fileLocation = "C:/Users/theod/AIORevature/Project1/adminInfo.txt"//"/home/maria_dev/adminInfo.txt"
        var adminFile = Source.fromFile(fileLocation)
        var intermediateAdminFile =
        for {
            line <-  adminFile.getLines
            split = line.split(",").map(_.trim).toList
            name = split(0)
            password = split(1)
        } yield (name -> password)
        var adminInfo = intermediateAdminFile.toMap
        workingMap = collection.mutable.Map(adminInfo.toSeq: _*)
        val adminnameCheck = scala.io.StdIn.readLine("Please enter Admin username: ").trim.toLowerCase()
        adminnameCheck match {
          case "exit" => logIn()
          case _ => {
            for ((k,v) <- adminInfo) {
              if (adminnameCheck == k ){
                println("Success!")
                usernameConfirm = true
                adminDirectory = true
                passwordConfirm(k ,v, fileLocation)            
              }// End of username match "(adminnameCheck == k )"
            }//End of "for ((k,v) <- adminInfo)"
            if (usernameConfirm == false){
              println("I'm sorry, that's not a recognized Admin name")
              adminLogin()
            }//End of "if (valid == 0)"
          }//End of 'case _ =>'
        }//End of 'adminnameCheck match' 
      }//End of adminLogIn


      def passwordConfirm(k: String, v: String, fileLocation: String ) {
        passwordConfirmRecurse()
        
        def passwordConfirmRecurse(){
          println()
          val passwordCheck = scala.io.StdIn.readLine("Please enter password: ")
          passwordCheck match {
            case "exit" => logIn()
            case _ => {
              if (passwordCheck == v ){
                println("Access granted!")
                usernameGlobal = k
                passwordGlobal = v
                infoFile = fileLocation
                
                //Exit to directory
              }//End of "if (passwordGlobal == v )"
              else{
                println()
                println("I'm sorry, that password is incorrect.")
                passwordConfirmRecurse()
              }//end of 'else'
            }//End of 'case _'
          }//End of 'passwordCheck match'
        }//End of password ConfirmRecurse()
      }//End of passwordConfirm() 
    }//End of Login
 
 */
    def addUser(){
      var newUsername = ""
      var newPassword = ""

      println()
      nameAddRecurse()
      def nameAddRecurse(){
        val tempUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val tempUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()

        ifRecurse()
        def ifRecurse(){
          if (tempUsername != tempUsernameConfirm) {
            println()     
                val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
                check match {
                  case "exit" => //Exit to Directory
                  case "yes"|"y" => nameAddRecurse()
                  case "no"|"n" => //Exit to Directory
                  case _ => {
                    println("I'm sorry, that's not an option")
                    ifRecurse()
                  }//End of 'case_'
                }//End of 'check match'
              }//End of '(newUsername != newUsernameConfirm)'
          else{
            
            newUsername = tempUsername
          }//End of 'else'
        }//End of 'if (tempUsername != tempUsernameConfirm)''
      }//End of ifRecurse()


      println()
      passAddRecurse()
      def passAddRecurse(){
        println("Passwords are case-sensitive")
        val tempPassword = scala.io.StdIn.readLine("Please enter Password: ").trim
        val tempPasswordConfirm = scala.io.StdIn.readLine("Please re-enter Password: ").trim
        
        ifRecurse()
        def ifRecurse(){
          if (tempPassword != tempPasswordConfirm) {
            println()     
              val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
              check match {
                case "exit" => //Exit to Directory
                case "yes"|"y" => passAddRecurse()
                case "no"|"n" => //Exit to Directory
                case _ => {
                  println("I'm sorry, that's not an option")
                  ifRecurse()
                }//End of 'case_'
              }//End of 'check match'
            }//End of 'if (newUsername != newUsernameConfirm)'
          else{
            newPassword = tempPassword            
          }
        }//End of 'if (tempPassword != tempPasswordConfirm)'
      }//End of ifRecurse()

        var userfileLocation = "/home/maria_dev/userInfo.txt"

        //Creates a temporary fileWriter object so that a new username and password can
        //be appended to the userInfo.txt file
        val infoDoc = new PrintWriter(new FileOutputStream(new File(userfileLocation),true))
        infoDoc.write("\n" + newUsername + "," + newPassword)
        infoDoc.close()
        println()
        println("New user added")
        println(s"Username: $newUsername")
        println(s"Password: $newPassword")
        println()
       //Exit to Directory
    }//End of addUser()


    def updateInfo() {

      //Check Password
      val passwordCheck = scala.io.StdIn.readLine("Please enter password to confirm identity: ")
      if (passwordCheck == passwordGlobal){
        println()
        println("Password Confirmed")
        println()
        optionRecurse()      
      }//End of if (passwordCheck == passwordGlobal)
      else{
        elseRecurse()
        def elseRecurse(){  
          println()   
          val check = scala.io.StdIn.readLine("I'm sorry, that password is incorrect. Would you like to try again?: ").trim.toLowerCase()
          check match {
            case "exit" => //Exit to Directory
            case "yes"|"y" => updateInfo()
            case "no"|"n" => //Exit to Directory
            case _ => {
              println("I'm sorry, that's not an option")
              elseRecurse()
            }//End of 'case_'
          }//End of 'check match'
        }//End of elseRecurse()       
      }//End of 'else'



      def optionRecurse(){
        println() 
        val option = scala.io.StdIn.readLine("Would you like to update your Password or Username?: ").trim().toLowerCase()
        option match {
          case "exit" => //Exit to Directory
          case "password" => passChange()
          case "username" => nameChange()
          case _ => {
            println("I'm sorry, that's not an option")
            optionRecurse()
          }//End of 'case_'
        }//End of "option match"
      }//End of optionRecurse()


      def passChange(){
        println()
        println("Passwords are case-sensitive")
        val newPassword = scala.io.StdIn.readLine("Please enter new Password: ").trim
        val newPasswordConfirm = scala.io.StdIn.readLine("Please re-enter new Password: ").trim
        if (newPassword == newPasswordConfirm){
          
          //Update Password in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += usernameGlobal -> newPassword

          //Update Password in program
          passwordGlobal = newPassword
          println()
          println("Password has been Successfuly changed")

          //Update local file
          fileUpdate()

        }//End of "if (newPassword == newPasswordConfirm)""

        else{  
          elseRecurse()
          def elseRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to Directory
              case "yes"|"y" => passChange()
              case "no"|"n" => //Exit to Directory
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of passChange()



      def nameChange(){
        println()
        val newUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val newUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()
        if (newUsername == newUsernameConfirm){
          
          //Update Username in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += newUsername -> passwordGlobal

          //Update Username in Program
          usernameGlobal = newUsername
          println()
          println("Username has been Successfuly changed")

          //Update local file        
          fileUpdate()
          

        }//End of "if (newUsername ==newUsernameConfirm)"
        else{  
          elseRecurse()
          def elseRecurse(){ 
            println()     
            val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to Directory
              case "yes"|"y" => nameChange()
              case "no"|"n" => //Exit to Directory
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of nameChange()



      def fileUpdate(){

        //'infoFile' is either admin txt file or user text file, depending on the log in credentials
        val fileTemp  = new PrintWriter(new File(infoFile))
        for ((i,j) <- workingMap){
          fileTemp.write(i+","+j+ "\n")
          
        }//End of "for ((i,j) <- userMap)"
        fileTemp.close()
        
        //Exit to Directory
      }//End of fileUpdate()     
    }// End of updateInfo()




































/* 
  def logIn(){ 
    //If an admin logs in, logs out, and then a user attmepts to log in without closing the program  
    adminDirectory = false

    //Admin or User
    val usertype = scala.io.StdIn.readLine("Admin or User login?: ").trim.toLowerCase()
    usertype match {
      case "exit" => 
      case "admin"|"administrator" => adminLogin()
      case "user" => userLogin()
      case _ => {
        println()
        println("I'm sorry that's not an option")
        logIn()
      }
    }

    def userLogin(){
      var usernameConfirm = false

      //File location needed as string for later funcitons
      var fileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"

      //Create Map from file
      var userFile = Source.fromFile(fileLocation)
      var intermediateUserFile =
        for {
          line <-  userFile.getLines
          split = line.split(",").map(_.trim).toList
          name = split(0)
          password = split(1)
        } yield (name -> password)//End of "intermediateUserFile" initialization
      var userInfo = intermediateUserFile.toMap

      //Convert map to a mutable collection
      var userMap = collection.mutable.Map(userInfo.toSeq: _*)

      //Checking to provided name against records
      val usernameCheck = scala.io.StdIn.readLine("Please enter username: ").trim.toLowerCase
      usernameCheck match {
        case "exit" => userLogin() 
        case _ => {
          for ((k,v) <- userInfo) {
            if (usernameCheck == k ){
              println("Success!")
              usernameConfirm = true

              //Confirm password
              passwordConfirm(k ,v, fileLocation)           
            }// End of username match "if (usernameCheck == k )"       
          }//End of "for ((k,v) <- userInfo)"

          if (usernameConfirm == false){
            println()
            println("I'm sorry, that's not a recognized username")
            userLogin()
          }//End of "if (valid == 0)"
        }//End of 'case _'
      }//End of 'usernameCheck'     
    }//End of userLogIn


    def adminLogin(){
      //See userLogin for documentation. 
      //Differnce: this path activates an Admin Directory
      
      println()
      var usernameConfirm = false
      var fileLocation = "C:/Users/theod/AIORevature/Project1/adminInfo.txt"
      var adminFile = Source.fromFile(fileLocation)
      var intermediateAdminFile =
      for {
          line <-  adminFile.getLines
          split = line.split(",").map(_.trim).toList
          name = split(0)
          password = split(1)
      } yield (name -> password)
      var adminInfo = intermediateAdminFile.toMap
      var adminMap = collection.mutable.Map(adminInfo.toSeq: _*)
      val adminnameCheck = scala.io.StdIn.readLine("Please enter Admin username: ").trim.toLowerCase()
      adminnameCheck match {
        case "exit" => userLogin()
        case _ => {
          for ((k,v) <- adminInfo) {
            if (adminnameCheck == k ){
              println("Success!")
              usernameConfirm = true
              adminDirectory = true
              passwordConfirm(k ,v, fileLocation)            
            }// End of username match "(adminnameCheck == k )"
          }//End of "for ((k,v) <- adminInfo)"
          if (usernameConfirm == false){
            println("I'm sorry, that's not a recognized Admin name")
            adminLogin()
          }//End of "if (valid == 0)"
        }//End of 'case _ =>'
      }//End of 'adminnameCheck match' 
    }//End of adminLogIn


     def passwordConfirm(k: String, v: String, fileLocation: String ) {
      passwordConfirmRecurse()
      
      def passwordConfirmRecurse(){
        println()
        val passwordCheck = scala.io.StdIn.readLine("Please enter password: ")
        passwordCheck match {
          case "exit" => logIn()
          case _ => {
            if (passwordCheck == v ){
              println("Access granted!")
              usernameGlobal = k
              passwordGlobal = v
              infoFile = fileLocation
              //Exit to directory
            }//End of "if (passwordGlobal == v )"
            else{
              println()
              println("I'm sorry, that password is incorrect.")
              passwordConfirmRecurse()
            }//end of 'else'
          }//End of 'case _'
        }//End of 'passwordCheck match'
      }//End of password ConfirmRecurse()
    }//End of passwordConfirm() 
  }//End of Login
 

















    def addUser(){
      var newUsername = ""
      var newPassword = ""

      println()
      nameAddRecurse()
      def nameAddRecurse(){
        val tempUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val tempUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()

        ifRecurse()
        def ifRecurse(){
          if (tempUsername != tempUsernameConfirm) {
            println()     
                val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
                check match {
                  case "exit" => //Exit to directory
                  case "yes"|"y" => nameAddRecurse()
                  case "no"|"n" => //Exit to directory
                  case _ => {
                    println("I'm sorry, that's not an option")
                    ifRecurse()
                  }//End of 'case_'
                }//End of 'check match'
              }//End of '(newUsername != newUsernameConfirm)'
          else{
            
            newUsername = tempUsername
            println(newUsername)
          }//End of 'else'
        }//End of 'if (tempUsername != tempUsernameConfirm)''
      }//End of ifRecurse()


      println()
      passAddRecurse()
      def passAddRecurse(){
        println("Passwords are case-sensitive")
        val tempPassword = scala.io.StdIn.readLine("Please enter Password: ").trim
        val tempPasswordConfirm = scala.io.StdIn.readLine("Please re-enter Password: ").trim
        
        ifRecurse()
        def ifRecurse(){
          if (tempPassword != tempPasswordConfirm) {
            println()     
              val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
              check match {
                case "exit" => //Exit to directory
                case "yes"|"y" => passAddRecurse()
                case "no"|"n" => //Exit to directory
                case _ => {
                  println("I'm sorry, that's not an option")
                  ifRecurse()
                }//End of 'case_'
              }//End of 'check match'
            }//End of 'if (newUsername != newUsernameConfirm)'
          else{
            newPassword = tempPassword
            println(newPassword)
          }
        }//End of 'if (tempPassword != tempPasswordConfirm)'
      }//End of ifRecurse()

        var userfileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"

        //Creates a temporary fileWriter object so that a new username and password can
        //be appended to the userInfo.txt file
        val infoDoc = new PrintWriter(new FileOutputStream(new File(userfileLocation),true))
        infoDoc.write("\n"+ newUsername + "," + newPassword)
        infoDoc.close()
    }//End of addUser()
























    def updateInfo() {

      //Check Password
      val passwordCheck = scala.io.StdIn.readLine("Please enter password to confirm identity: ")
      if (passwordCheck == passwordGlobal){
        println()
        println("Password Confirmed")
        println()
        optionRecurse()      
      }//End of if (passwordCheck == passwordGlobal)
      else{
        elseRecurse()
        def elseRecurse(){  
          println()   
          val check = scala.io.StdIn.readLine("I'm sorry, that password is incorrect. Would you like to try again?: ").trim.toLowerCase()
          check match {
            case "exit" => //Exit to directory
            case "yes"|"y" => updateInfo()
            case "no"|"n" => //Exit to directory
            case _ => {
              println("I'm sorry, that's not an option")
              elseRecurse()
            }//End of 'case_'
          }//End of 'check match'
        }//End of elseRecurse()       
      }//End of 'else'



      def optionRecurse(){
        println() 
        val option = scala.io.StdIn.readLine("Would you like to update your Password or Username?: ").trim().toLowerCase()
        option match {
          case "exit" => //Exit to directory
          case "password" => passChange()
          case "username" => nameChange()
          case _ => {
            println("I'm sorry, that's not an option")
            optionRecurse()
          }//End of 'case_'
        }//End of "option match"
      }//End of optionRecurse()


      def passChange(){
        println()
        println("Passwords are case-sensitive")
        val newPassword = scala.io.StdIn.readLine("Please enter new Password: ").trim
        val newPasswordConfirm = scala.io.StdIn.readLine("Please re-enter new Password: ").trim
        if (newPassword == newPasswordConfirm){
          
          //Update Password in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += usernameGlobal -> newPassword

          //Update Password in program
          passwordGlobal = newPassword
          println()
          println("Password has been Successfuly changed")

          //Update local file
          fileUpdate()

        }//End of "if (newPassword == newPasswordConfirm)""

        else{  
          elseRecurse()
          def elseRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => passChange()
              case "no"|"n" => //Exit to directory
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of passChange()



      def nameChange(){
        println()
        val newUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val newUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()
        if (newUsername ==newUsernameConfirm){
          
          //Update Username in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += newUsername -> passwordGlobal

          //Update Username in Program
          usernameGlobal = newUsername
          println()
          println("Username has been Successfuly changed")

          //Update local file
          fileUpdate()

        }//End of "if (newUsername ==newUsernameConfirm)"
        else{  
          elseRecurse()
          def elseRecurse(){ 
            println()     
            val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => nameChange()
              case "no"|"n" => //Exit to directory
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of nameChange()



      def fileUpdate(){

        //'infoFile' is either admin txt file or user text file, depending on the log in credentials
        val fileTemp  = new PrintWriter(new File(infoFile))
        for ((i,j) <- workingMap){
          fileTemp.write(i+","+j+ "\n")
        }//End of "for ((i,j) <- userMap)"

        fileTemp.close()
        //Exit to directory
      }//End of fileUpdate()     
    }// End of updateInfo()

    
  
  
  
   */
  
  
  
  































































































  /*   
    val d = new Directory

   // 


  def logIn(){ 
    //If an admin logs in, logs out, and then a user attmepts to log in without closing the program  
    adminDirectory = false

    //Admin or User
    val usertype = scala.io.StdIn.readLine("Admin or User login?: ").trim.toLowerCase()
    usertype match {
      case "exit" => 
      case "admin"|"administrator" => adminLogin()
      case "user" => userLogin()
      case _ => {
        println()
        println("I'm sorry that's not an option")
        logIn()
      }
    }

    def userLogin(){
      var usernameConfirm = false

      //File location needed as string for later funcitons
      var fileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"

      //Create Map from file
      var userFile = Source.fromFile(fileLocation)
      var intermediateUserFile =
        for {
          line <-  userFile.getLines
          split = line.split(",").map(_.trim).toList
          name = split(0)
          password = split(1)
        } yield (name -> password)//End of "intermediateUserFile" initialization
      var userInfo = intermediateUserFile.toMap

      //Convert map to a mutable collection
      var userMap = collection.mutable.Map(userInfo.toSeq: _*)

      //Checking to provided name against records
      val usernameCheck = scala.io.StdIn.readLine("Please enter username: ").trim.toLowerCase
      usernameCheck match {
        case "exit" => userLogin() 
        case _ => {
          for ((k,v) <- userInfo) {
            if (usernameCheck == k ){
              println("Success!")
              usernameConfirm = true

              //Confirm password
              passwordConfirm(k ,v, fileLocation)           
            }// End of username match "if (usernameCheck == k )"       
          }//End of "for ((k,v) <- userInfo)"

          if (usernameConfirm == false){
            println()
            println("I'm sorry, that's not a recognized username")
            userLogin()
          }//End of "if (valid == 0)"
        }//End of 'case _'
      }//End of 'usernameCheck'     
    }//End of userLogIn


    def adminLogin(){
      //See userLogin for documentation. 
      //Differnce: this path activates an Admin Directory
      
      println()
      var usernameConfirm = false
      var fileLocation = "C:/Users/theod/AIORevature/Project1/adminInfo.txt"
      var adminFile = Source.fromFile(fileLocation)
      var intermediateAdminFile =
      for {
          line <-  adminFile.getLines
          split = line.split(",").map(_.trim).toList
          name = split(0)
          password = split(1)
      } yield (name -> password)
      var adminInfo = intermediateAdminFile.toMap
      var adminMap = collection.mutable.Map(adminInfo.toSeq: _*)
      val adminnameCheck = scala.io.StdIn.readLine("Please enter Admin username: ").trim.toLowerCase()
      adminnameCheck match {
        case "exit" => userLogin()
        case _ => {
          for ((k,v) <- adminInfo) {
            if (adminnameCheck == k ){
              println("Success!")
              usernameConfirm = true
              adminDirectory = true
              passwordConfirm(k ,v, fileLocation)            
            }// End of username match "(adminnameCheck == k )"
          }//End of "for ((k,v) <- adminInfo)"
          if (usernameConfirm == false){
            println("I'm sorry, that's not a recognized Admin name")
            adminLogin()
          }//End of "if (valid == 0)"
        }//End of 'case _ =>'
      }//End of 'adminnameCheck match' 
    }//End of adminLogIn


     def passwordConfirm(k: String, v: String, fileLocation: String ) {
      passwordConfirmRecurse()
      
      def passwordConfirmRecurse(){
        println()
        val passwordCheck = scala.io.StdIn.readLine("Please enter password: ")
        passwordCheck match {
          case "exit" => logIn()
          case _ => {
            if (passwordCheck == v ){
              println("Access granted!")
              usernameGlobal = k
              passwordGlobal = v
              infoFile = fileLocation
              d.directory()
            }//End of "if (passwordGlobal == v )"
            else{
              println()
              println("I'm sorry, that password is incorrect.")
              passwordConfirmRecurse()
            }//end of 'else'
          }//End of 'case _'
        }//End of 'passwordCheck match'
      }//End of password ConfirmRecurse()
    }//End of passwordConfirm() 
  }//End of Login
 

















    def addUser(){
      var newUsername = ""
      var newPassword = ""

      println()
      nameAddRecurse()
      def nameAddRecurse(){
        val tempUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val tempUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()

        ifRecurse()
        def ifRecurse(){
          if (tempUsername != tempUsernameConfirm) {
            println()     
                val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
                check match {
                  case "exit" => d.directory()
                  case "yes"|"y" => nameAddRecurse()
                  case "no"|"n" => d.directory()
                  case _ => {
                    println("I'm sorry, that's not an option")
                    ifRecurse()
                  }//End of 'case_'
                }//End of 'check match'
              }//End of '(newUsername != newUsernameConfirm)'
          else{
            
            newUsername = tempUsername
            println(newUsername)
          }//End of 'else'
        }//End of 'if (tempUsername != tempUsernameConfirm)''
      }//End of ifRecurse()


      println()
      passAddRecurse()
      def passAddRecurse(){
        println("Passwords are case-sensitive")
        val tempPassword = scala.io.StdIn.readLine("Please enter Password: ").trim
        val tempPasswordConfirm = scala.io.StdIn.readLine("Please re-enter Password: ").trim
        
        ifRecurse()
        def ifRecurse(){
          if (tempPassword != tempPasswordConfirm) {
            println()     
              val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
              check match {
                case "exit" => d.directory()
                case "yes"|"y" => passAddRecurse()
                case "no"|"n" => d.directory()
                case _ => {
                  println("I'm sorry, that's not an option")
                  ifRecurse()
                }//End of 'case_'
              }//End of 'check match'
            }//End of 'if (newUsername != newUsernameConfirm)'
          else{
            newPassword = tempPassword
            println(newPassword)
          }
        }//End of 'if (tempPassword != tempPasswordConfirm)'
      }//End of ifRecurse()

        var userfileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"

        //Creates a temporary fileWriter object so that a new username and password can
        //be appended to the userInfo.txt file
        val infoDoc = new PrintWriter(new FileOutputStream(new File(userfileLocation),true))
        infoDoc.write("\n"+ newUsername + "," + newPassword)
        infoDoc.close()
    }//End of addUser()
























    def updateInfo() {

      //Check Password
      val passwordCheck = scala.io.StdIn.readLine("Please enter password to confirm identity: ")
      if (passwordCheck == passwordGlobal){
        println()
        println("Password Confirmed")
        println()
        optionRecurse()      
      }//End of if (passwordCheck == passwordGlobal)
      else{
        elseRecurse()
        def elseRecurse(){  
          println()   
          val check = scala.io.StdIn.readLine("I'm sorry, that password is incorrect. Would you like to try again?: ").trim.toLowerCase()
          check match {
            case "exit" => d.directory()
            case "yes"|"y" => updateInfo()
            case "no"|"n" => d.directory()
            case _ => {
              println("I'm sorry, that's not an option")
              elseRecurse()
            }//End of 'case_'
          }//End of 'check match'
        }//End of elseRecurse()       
      }//End of 'else'



      def optionRecurse(){
        println() 
        val option = scala.io.StdIn.readLine("Would you like to update your Password or Username?: ").trim().toLowerCase()
        option match {
          case "exit" => d.directory()
          case "password" => passChange()
          case "username" => nameChange()
          case _ => {
            println("I'm sorry, that's not an option")
            optionRecurse()
          }//End of 'case_'
        }//End of "option match"
      }//End of optionRecurse()


      def passChange(){
        println()
        println("Passwords are case-sensitive")
        val newPassword = scala.io.StdIn.readLine("Please enter new Password: ").trim
        val newPasswordConfirm = scala.io.StdIn.readLine("Please re-enter new Password: ").trim
        if (newPassword == newPasswordConfirm){
          
          //Update Password in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += usernameGlobal -> newPassword

          //Update Password in program
          passwordGlobal = newPassword
          println()
          println("Password has been Successfuly changed")

          //Update local file
          fileUpdate()

        }//End of "if (newPassword == newPasswordConfirm)""

        else{  
          elseRecurse()
          def elseRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => d.directory()
              case "yes"|"y" => passChange()
              case "no"|"n" => d.directory()
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of passChange()



      def nameChange(){
        println()
        val newUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val newUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()
        if (newUsername ==newUsernameConfirm){
          
          //Update Username in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += newUsername -> passwordGlobal

          //Update Username in Program
          usernameGlobal = newUsername
          println()
          println("Username has been Successfuly changed")

          //Update local file
          fileUpdate()

        }//End of "if (newUsername ==newUsernameConfirm)"
        else{  
          elseRecurse()
          def elseRecurse(){ 
            println()     
            val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => d.directory()
              case "yes"|"y" => nameChange()
              case "no"|"n" => d.directory()
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of nameChange()



      def fileUpdate(){

        //'infoFile' is either admin txt file or user text file, depending on the log in credentials
        val fileTemp  = new PrintWriter(new File(infoFile))
        for ((i,j) <- workingMap){
          fileTemp.write(i+","+j+ "\n")
        }//End of "for ((i,j) <- userMap)"

        fileTemp.close()
        d.directory()
      }//End of fileUpdate()     
    }// End of updateInfo()

  
  
   */
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  


/* 
  def logIn(){ 
    //If an admin logs in, logs out, and then a user attmepts to log in without closing the program  
    adminDirectory = false

    //Admin or User
    val usertype = scala.io.StdIn.readLine("Admin or User login?: ").trim.toLowerCase()
    usertype match {
      case "exit" => 
      case "admin"|"administrator" => adminLogin()
      case "user" => userLogin()
      case _ => {
        println()
        println("I'm sorry that's not an option")
        logIn()
      }
    }

    def userLogin(){
      var usernameConfirm = false

      //File location needed as string for later funcitons
      var fileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"

      //Create Map from file
      var userFile = Source.fromFile(fileLocation)
      var intermediateUserFile =
        for {
          line <-  userFile.getLines
          split = line.split(",").map(_.trim).toList
          name = split(0)
          password = split(1)
        } yield (name -> password)//End of "intermediateUserFile" initialization
      var userInfo = intermediateUserFile.toMap

      //Convert map to a mutable collection
      var userMap = collection.mutable.Map(userInfo.toSeq: _*)

      //Checking to provided name against records
      val usernameCheck = scala.io.StdIn.readLine("Please enter username: ").trim.toLowerCase
      usernameCheck match {
        case "exit" => userLogin() 
        case _ => {
          for ((k,v) <- userInfo) {
            if (usernameCheck == k ){
              println("Success!")
              usernameConfirm = true

              //Confirm password
              passwordConfirm(k ,v, fileLocation)           
            }// End of username match "if (usernameCheck == k )"       
          }//End of "for ((k,v) <- userInfo)"

          if (usernameConfirm == false){
            println()
            println("I'm sorry, that's not a recognized username")
            userLogin()
          }//End of "if (valid == 0)"
        }//End of 'case _'
      }//End of 'usernameCheck'     
    }//End of userLogIn


    def adminLogin(){
      //See userLogin for documentation. 
      //Differnce: this path activates an Admin Directory
      
      println()
      var usernameConfirm = false
      var fileLocation = "C:/Users/theod/AIORevature/Project1/adminInfo.txt"
      var adminFile = Source.fromFile(fileLocation)
      var intermediateAdminFile =
      for {
          line <-  adminFile.getLines
          split = line.split(",").map(_.trim).toList
          name = split(0)
          password = split(1)
      } yield (name -> password)
      var adminInfo = intermediateAdminFile.toMap
      var adminMap = collection.mutable.Map(adminInfo.toSeq: _*)
      val adminnameCheck = scala.io.StdIn.readLine("Please enter Admin username: ").trim.toLowerCase()
      adminnameCheck match {
        case "exit" => userLogin()
        case _ => {
          for ((k,v) <- adminInfo) {
            if (adminnameCheck == k ){
              println("Success!")
              usernameConfirm = true
              adminDirectory = true
              passwordConfirm(k ,v, fileLocation)            
            }// End of username match "(adminnameCheck == k )"
          }//End of "for ((k,v) <- adminInfo)"
          if (usernameConfirm == false){
            println("I'm sorry, that's not a recognized Admin name")
            adminLogin()
          }//End of "if (valid == 0)"
        }//End of 'case _ =>'
      }//End of 'adminnameCheck match' 
    }//End of adminLogIn


     def passwordConfirm(k: String, v: String, fileLocation: String ) {
      passwordConfirmRecurse()
      
      def passwordConfirmRecurse(){
        println()
        val passwordCheck = scala.io.StdIn.readLine("Please enter password: ")
        passwordCheck match {
          case "exit" => logIn()
          case _ => {
            if (passwordCheck == v ){
              println("Access granted!")
              usernameGlobal = k
              passwordGlobal = v
              infoFile = fileLocation
              directory()
            }//End of "if (passwordGlobal == v )"
            else{
              println()
              println("I'm sorry, that password is incorrect.")
              passwordConfirmRecurse()
            }//end of 'else'
          }//End of 'case _'
        }//End of 'passwordCheck match'
      }//End of password ConfirmRecurse()
    }//End of passwordConfirm() 
  }//End of Login
 

















    def addUser(){
      var newUsername = ""
      var newPassword = ""

      println()
      nameAddRecurse()
      def nameAddRecurse(){
        val tempUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val tempUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()

        ifRecurse()
        def ifRecurse(){
          if (tempUsername != tempUsernameConfirm) {
            println()     
                val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
                check match {
                  case "exit" => directory()
                  case "yes"|"y" => nameAddRecurse()
                  case "no"|"n" => directory()
                  case _ => {
                    println("I'm sorry, that's not an option")
                    ifRecurse()
                  }//End of 'case_'
                }//End of 'check match'
              }//End of '(newUsername != newUsernameConfirm)'
          else{
            
            newUsername = tempUsername
            println(newUsername)
          }//End of 'else'
        }//End of 'if (tempUsername != tempUsernameConfirm)''
      }//End of ifRecurse()


      println()
      passAddRecurse()
      def passAddRecurse(){
        println("Passwords are case-sensitive")
        val tempPassword = scala.io.StdIn.readLine("Please enter Password: ").trim
        val tempPasswordConfirm = scala.io.StdIn.readLine("Please re-enter Password: ").trim
        
        ifRecurse()
        def ifRecurse(){
          if (tempPassword != tempPasswordConfirm) {
            println()     
              val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
              check match {
                case "exit" => directory()
                case "yes"|"y" => passAddRecurse()
                case "no"|"n" => directory()
                case _ => {
                  println("I'm sorry, that's not an option")
                  ifRecurse()
                }//End of 'case_'
              }//End of 'check match'
            }//End of 'if (newUsername != newUsernameConfirm)'
          else{
            newPassword = tempPassword
            println(newPassword)
          }
        }//End of 'if (tempPassword != tempPasswordConfirm)'
      }//End of ifRecurse()

        var userfileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"

        //Creates a temporary fileWriter object so that a new username and password can
        //be appended to the userInfo.txt file
        val infoDoc = new PrintWriter(new FileOutputStream(new File(userfileLocation),true))
        infoDoc.write("\n"+ newUsername + "," + newPassword)
        infoDoc.close()
    }//End of addUser()
























    def updateInfo() {

      //Check Password
      val passwordCheck = scala.io.StdIn.readLine("Please enter password to confirm identity: ")
      if (passwordCheck == passwordGlobal){
        println()
        println("Password Confirmed")
        println()
        optionRecurse()      
      }//End of if (passwordCheck == passwordGlobal)
      else{
        elseRecurse()
        def elseRecurse(){  
          println()   
          val check = scala.io.StdIn.readLine("I'm sorry, that password is incorrect. Would you like to try again?: ").trim.toLowerCase()
          check match {
            case "exit" => directory()
            case "yes"|"y" => updateInfo()
            case "no"|"n" => directory()
            case _ => {
              println("I'm sorry, that's not an option")
              elseRecurse()
            }//End of 'case_'
          }//End of 'check match'
        }//End of elseRecurse()       
      }//End of 'else'



      def optionRecurse(){
        println() 
        val option = scala.io.StdIn.readLine("Would you like to update your Password or Username?: ").trim().toLowerCase()
        option match {
          case "exit" => directory()
          case "password" => passChange()
          case "username" => nameChange()
          case _ => {
            println("I'm sorry, that's not an option")
            optionRecurse()
          }//End of 'case_'
        }//End of "option match"
      }//End of optionRecurse()


      def passChange(){
        println()
        println("Passwords are case-sensitive")
        val newPassword = scala.io.StdIn.readLine("Please enter new Password: ").trim
        val newPasswordConfirm = scala.io.StdIn.readLine("Please re-enter new Password: ").trim
        if (newPassword == newPasswordConfirm){
          
          //Update Password in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += usernameGlobal -> newPassword

          //Update Password in program
          passwordGlobal = newPassword
          println()
          println("Password has been Successfuly changed")

          //Update local file
          fileUpdate()

        }//End of "if (newPassword == newPasswordConfirm)""

        else{  
          elseRecurse()
          def elseRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, the Passwords do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => directory()
              case "yes"|"y" => passChange()
              case "no"|"n" => directory()
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of passChange()



      def nameChange(){
        println()
        val newUsername = scala.io.StdIn.readLine("Please enter new Username: ").trim.toLowerCase()
        val newUsernameConfirm = scala.io.StdIn.readLine("Please re-enter new Username: ").trim.toLowerCase()
        if (newUsername ==newUsernameConfirm){
          
          //Update Username in Map
          workingMap -= (usernameGlobal,passwordGlobal)
          workingMap += newUsername -> passwordGlobal

          //Update Username in Program
          usernameGlobal = newUsername
          println()
          println("Username has been Successfuly changed")

          //Update local file
          fileUpdate()

        }//End of "if (newUsername ==newUsernameConfirm)"
        else{  
          elseRecurse()
          def elseRecurse(){ 
            println()     
            val check = scala.io.StdIn.readLine("I'm sorry, the Usernames do not match. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => directory()
              case "yes"|"y" => nameChange()
              case "no"|"n" => directory()
              case _ => {
                println("I'm sorry, that's not an option")
                elseRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of elseRecurse() 
        }//End of 'else'
      }//End of nameChange()



      def fileUpdate(){

        //'infoFile' is either admin txt file or user text file, depending on the log in credentials
        val fileTemp  = new PrintWriter(new File(infoFile))
        for ((i,j) <- workingMap){
          fileTemp.write(i+","+j+ "\n")
        }//End of "for ((i,j) <- userMap)"

        fileTemp.close()
        directory()
      }//End of fileUpdate()     
    }// End of updateInfo()
 */
/* 
    def directory(){
      println()

      //Admin Directory
      if (adminDirectory){
        val a: String = scala.io.StdIn.readLine("Enter Command: ").toLowerCase()
        a match{
          case "exit" =>
          case "updateinfo()" => updateInfo()
          case "adduser()" => addUser()
          case "logout" => logIn()
          case "--help" => {
            println("updateInfo()")         
            println("logOut")
            println()
            println("Admin Commands:")
            println("addUser()")
            println()
            println()
            directory()
            
          }//End of "--help"
          case _ => {
            println("I'm sorry that's not an option")
            println()
            directory()
            
          }//End of "case _"
        }//End of match
      }//End of "if (adminDirectory)"
    
      //User Directory
      else {
        val a: String = scala.io.StdIn.readLine("Enter Command: ").toLowerCase()
        a match{
          case "exit" =>
          case "updateinfo()" => updateInfo()
          case "logout" => logIn()       
          case "--help" => {
            println("updateInfo()")
            println("logOut")
            println()
            println()
            directory()
            
          }//End of "--help"
          case _ => {
            println("I'm sorry that's not an option")
            println()
            directory()
            
          }//End of "case _"
        }//End of match  
      }//End of "else"
    }//End of directory()


    */

  //logIn()


  
  //}//End of main()
}//End of object