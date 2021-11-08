
package example

import scala.io.Source
import java.io.File
import java.io.PrintWriter
import java.io.FileOutputStream

object project1{
  def main(args: Array[String]){


    var usernameGlobal = ""
    var passwordGlobal = ""
    var workingMap = scala.collection.mutable.Map(""->"")
    var infoFile = ""
    var userFile = ""
    var adminDirectory = false




    val userFunc = new userLogClass
    val apiFunc = new apiManipulation



    //Hive and HDFS Functions**************
    val hdfsFunc = new hdfsCommands
    val hiveFunc = new hiveCommands




    def logIn(){ 
      println();println()

      //If an admin logs in, logs out, and then a user attmepts to log in without closing the program  
      adminDirectory = false

      //Admin or User
      val usertype = scala.io.StdIn.readLine("Admin or User login?: ").trim.toLowerCase()
      usertype match {
        case "exit" => //Exit program
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
        var fileLocation = "/home/maria_dev/userInfo.txt"
        //var fileLocation = "C:/Users/theod/AIORevature/Project1/userInfo.txt"

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

        var fileLocation = "/home/maria_dev/adminInfo.txt" 
        //var fileLocation = "C:/Users/theod/AIORevature/Project1/adminInfo.txt" 

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

                userFunc.globalSet(usernameGlobal,passwordGlobal,workingMap,infoFile,adminDirectory)
   
                //Exit to directory
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
 





    def directory(){
      println()

      //Admin Directory
      if (userFunc.adminDirectory){
        val a: String = scala.io.StdIn.readLine("Enter Command: ").toLowerCase()
        a match{
          case "exit" => //userFunc.logIn()
          case "makerequest()" => {
            apiFunc.firstGateRecurse()
            queryRecurse() 
            def queryRecurse(){
              var query = scala.io.StdIn.readLine("Would you like to query this data?: ")
              query match{
                case "exit" => directory()
                case "yes"|"y" => {
                  hdfsFunc.copyFromLocal()
                  hiveFunc.createTable()
                  hiveFunc.query()
                }
                case "no"|"n"=>
                case _ => {
                  println("I'm sorry, that's not an option")
                  queryRecurse()
                }//End of 'case_'
              }//End of 'query match'
            }//End of queryRecurse()
            directory()
          }//End of 'case "makerequest()"'
          case "updateinfo()" => {
            userFunc.updateInfo()
            directory()
          }
          case "adduser()" => {
            userFunc.addUser()
            directory()
          }
          case "logout" => logIn()
          case "--help" => {
            println("updateInfo()")
            println("makerequest()")
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
            println("Try --help")
            println()
            directory()
            
          }//End of "case _"
        }//End of match
      }//End of "if (adminDirectory)"
    
      //User Directory
      else {
        val a: String = scala.io.StdIn.readLine("Enter Command: ").toLowerCase()
        a match{
          case "exit" => //userFunc.logIn()
          case "makerequest()" => {
            apiFunc.firstGateRecurse()
            queryRecurse() 
            def queryRecurse(){
              var query = scala.io.StdIn.readLine("Would you like to query this data?: ")
              query match{
                case "exit" => 
                case "yes"|"y" => {
                  hdfsFunc.copyFromLocal()
                  hiveFunc.createTable()
                  hiveFunc.query()
                }
                case "no"|"n"=>
                case _ => {
                  println("I'm sorry, that's not an option")
                  queryRecurse()
                }//End of 'case_'
              }//End of 'query match'
            }//End of queryRecurse()
            directory()
          }//End of 'case "makerequest()"'
          case "updateinfo()" => {
            userFunc.updateInfo()
            directory()
          }
          case "query()" => {
            hiveFunc.query()
            directory()
          }
          case "logout" => logIn()     
          case "--help" => {
            println("makerequest()")
            println("updateInfo()")
            println("logOut")          
            println()
            println()
            directory()           
          }//End of "--help"
          case _ => {
            println("I'm sorry that's not an option")
            println("Try --help")
            println()
            directory()       
          }//End of "case _"
        }//End of match  
      }//End of "else"
    }//End of directory()


    logIn()
    

  }//End of main()
}//End of project1
//spark-submit --packages net.liftweb:lift-json_2.11:2.6 ./apiproject_2.11-0.1.0-SNAPSHOT.jar
