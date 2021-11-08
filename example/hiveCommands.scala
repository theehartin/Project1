package example

class hiveCommands{

  import java.sql.SQLException;
  import java.sql.Connection;
  import java.sql.ResultSet;
  import java.sql.Statement;
  import java.sql.DriverManager;

  var db = "project1"
  var driverName = "org.apache.hive.jdbc.HiveDriver" //This is the driver we're going to use
  val conStr = s"jdbc:hive2://sandbox-hdp.hortonworks.com:10000/$db";//'default' is the database we're using
  var con: java.sql.Connection = null;
  con = DriverManager.getConnection(conStr, "", ""); //"Connect to the driver"
  Class.forName(driverName); //"Use this driver"
  val stmt = con.createStatement();



  

    val countryMap = Map(
    "ar" -> "Argentina",
     "au" -> "Australia",
     "at" -> "Austria",
     "be" -> "Belgium",
     "br" -> "Brazil",
     "bg" -> "Bulgaria",
     "ca" -> "Canada",
     "cn" -> "China",
     "co" -> "Colombia",
     "cz" -> "Czech Republic",
     "eg" -> "Egypt",
     "fr" -> "France",
     "de" -> "Germany",
     "gr" -> "Greece",
     "hk" -> "Hong Kong",
     "hu" -> "Hungary",
     "in" -> "India",
     "id" -> "Indonesia",
     "ie" -> "Ireland",
     "il" -> "Isreal",
     "it" -> "Italy",
     "jp" -> "Japan",
     "lv" -> "Latvia",
     "lt" -> "Lithuania",
     "my" -> "Malaysia",
     "mx" -> "Mexico",
     "ma" -> "Morocco",
     "nl" -> "Netherlands",
     "nz" -> "New Zealand",
     "ng" -> "Nigeria",
     "no" -> "Norway",
     "ph" -> "Philippines",
     "pl" -> "Poland",
     "pt" -> "Portugal",
     "ro" -> "Romania",
     "sa" -> "Saudi Arabia",
     "rs" -> "Serbia",
     "sg" -> "Singapore",
     "sk" -> "Slovakia",
     "si" -> "Slovenia",
     "za" -> "South Africa",
     "kr" -> "South Korea",
     "se" -> "Sweden",
     "ch" -> "Switzerland",
     "tw" -> "Taiwan",
     "th" -> "Thailand",
     "tr" -> "Turkey",
     "ae" -> "United Arab Emirates",
     "ua" -> "Ukraine",
     "gb" -> "United Kingdom",
     "us" -> "United States",
     "ve" -> "Venuzuela"

    )

    val languageMap = Map(
      "ar" -> "Arabic",
      "de" -> "German",
      "en" -> "English", 
      "es" -> "Spanish",
      "fr" -> "French",
      "he" -> "Hebrew",
      "it" -> "Italian",
      "nl" -> "Dutch",
      "no" -> "Norwegian",
      "pt" -> "Portugese",
      "ru" -> "Russian",
      "se" -> "Swedish",
      "zh" -> "Chinese"
 )


  def createTable(){
    println()
    println()

    var tableName = "temp"
    stmt.execute(s"use $db" )
    val filePath = "/user/maria_dev/APIdata"
    val columnNames = "(title string, category string, language string, country string, source string, url string)"   
    stmt.execute(s"create external table IF NOT EXISTS temp (title string, category string, language string, country string, source string, url string) row format delimited fields terminated by '|' location '/user/maria_dev/APIdata'"    )
  }//End of createTable

  def additionalQueryPrompt(){
    println()
    var answer = scala.io.StdIn.readLine("Would you like to perform an Additional Query?: ").trim.toLowerCase()
    answer match {
      case "y"|"yes" => query()
      case "n"|"no" =>
      case _ => {
        println()
        println("I'm sorry, that's not an option")
        additionalQueryPrompt()
      }//End of 'case _' 
    }//End of 'answer match'
  }//End of additionalQueryPrompt()



  def query(){


    firstGateRecurse()
    def firstGateRecurse(){
      println() 
      println("Would you like to group by?:\n[1] Category\n[2] Country\n[3] Language\n[4] Filtered Query ")
      
      var firstGate = scala.io.StdIn.readLine().trim.toLowerCase()
      firstGate match {
        case "exit" => //Exit to directory
        case "1" => groupByCategory()
        case "2" => groupByCountry()         
        case "3" => groupByLanguage()                 
        case "4" => filteredQueryPrompt()
        case _ => {
          allOtherRecurse()
          def allOtherRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, that's not an option. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => firstGateRecurse()
              case "no"|"n" =>//Exit to directory
              case _ => {
                //println("I'm sorry, that's not an option")
                allOtherRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of allOtherRecurse()
        }//End of 'case_'
      }//End of 'firstGate match'
    }//End of firstGateRecurse()
  }//End of query}


  def groupByCountry(){
    println("Grouping by Country")
    var res = stmt.executeQuery("select count (title) as count, country from temp group by country order by count desc")

    while (res.next()) {
      println(s"${res.getString(2)}, ${res.getString(1)}")
    }//End of 'while (res.next())'
    additionalQueryPrompt()
  }//End of groupByCountry()

  def groupByCategory(){
    println("Grouping by Category")
    var res = stmt.executeQuery("select count (title) as count, category from temp group by category order by count desc")

    while (res.next()) {
      println(s"${res.getString(2)}, ${res.getString(1)}")
    }//End of 'while (res.next())'
    additionalQueryPrompt()
  }//End of groupByCategory()

  def groupByLanguage(){
    println("Grouping by Language")
    var res = stmt.executeQuery("select count (title) as count, language from temp group by language order by count desc")

    while (res.next()) {
      println(s"${res.getString(2)}, ${res.getString(1)}")
    }//End of 'while (res.next())'
    additionalQueryPrompt()
  }//End of groupByCategory()


    

  def filteredQueryPrompt(){
    var critParam = ""
    var gloCriteria = ""
    

    filterRecurse()
    def filterRecurse(){
      println() 
      //println("Would you like to filter by Country or Language?")
      println()
      var criteria = scala.io.StdIn.readLine("Would you like to filter by Country or Language?: ").trim.toLowerCase()
      criteria match {
        case "exit" => query()
        case "country" => criteriaSelect("Country")
        case "language" => criteriaSelect("Language")         
        case _ => {
          allOtherRecurse()
          def allOtherRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, that's not an option. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => filterRecurse()
              case "no"|"n" =>//Exit to directory
              case _ => {
                //println("I'm sorry, that's not an option")
                allOtherRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of allOtherRecurse()
        }//End of 'case_'
      }//End of 'criteria match'
    }//End of filterRecurse()


    def criteriaSelect(a: String){
        /**
          * Ok, so if the user picks "Country" or "Language" then they're routed here.
          * Depending on their chioce, the local token, in this case 'criteria', is
          * set to pertain to the choice they made. The functionality is identical
          * for most of the function, only bifurcating if a match is made to the
          * respective maps (countryMap and languageMap)
          * 
          * 
          */

        var criteria = a

        criteriaSelectRecurse()
        def criteriaSelectRecurse(){

          //Function Variables
          var criteriaMap = Map(" "->" ")
          

          //Setting token
          if (criteria == "Language"){
            criteriaMap = languageMap
          }
          if (criteria == "Country"){
            criteriaMap = countryMap
          }

          
          //Choice Prompt
          println()
          val criteriaChoice = scala.io.StdIn.readLine(s"Which $criteria would you like to Filter by?: ")
            .trim
            .toLowerCase()
            .split(" ")
            .map(_.capitalize)
            .mkString(" ")   
          var criteriaMatch = false
          
          //Mapping over map
          criteriaMap map {case (k,v) => 

            //If a match is made
            if(criteriaChoice == v){
              criteriaMatch =true
              critParam = v
              gloCriteria = criteria
            }//End of 'if(criteriaChoice == v)' 
          }//End of 'criteriaMap map'
          


          //If your input doesn't match anything
          if(criteriaMatch==false){
            allOtherRecurse()
            def allOtherRecurse(){  
              println()   
              val check = scala.io.StdIn.readLine("I'm sorry, that's not an option. Would you like to try again?: ").trim.toLowerCase()
              check match {
                case "exit" => //Exit to directory
                case "yes"|"y" => criteriaSelectRecurse()
                case "no"|"n" => query
                case _ => allOtherRecurse()
              }//End of 'check match'
            }//End of allOtherRecurse()
          }//End 'if(criteriaMatch==false)'
        }//End of criteriaSelectRecurse()
    }//End of criteriaSelect()

    filteredQueryFirstGateRecurse()
    def filteredQueryFirstGateRecurse(){
      println()  
      println("Would you like to group the Filtered results by:\n[1] Category\n[2] Country\n[3] Language\n")       
      var firstGate = scala.io.StdIn.readLine().trim.toLowerCase()
      firstGate match {
        case "exit" => query()
        case "1" => filtergroupByCategory()
        case "2" => filtergroupByCountry()      
        case "3" => filtergroupByLanguage()                 
        case _ => {
          allOtherRecurse()
          def allOtherRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, that's not an option. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => filteredQueryFirstGateRecurse()
              case "no"|"n" =>//Exit to directory
              case _ => allOtherRecurse()
            }//End of 'check match'
          }//End of allOtherRecurse()
        }//End of 'case_'
      }//End of 'firstGate match'
    }//End of firstGateRecurse()


    def filtergroupByCountry(){
      println("Grouping by Country")
      
      var res = stmt.executeQuery(s"select count (title) as count, country from temp where $gloCriteria='$critParam' group by country order by count desc")
      //var res = stmt.executeQuery("select count (title) as count, country from temp where language='English' group by country order by count desc")

      while (res.next()) {
        println(s"${res.getString(2)}, ${res.getString(1)}")               
      }//End of 'while (res.next())'
      additionalQueryPrompt()
    }//End of filtergroupByCountry()

    def filtergroupByCategory(){
      println("Grouping by Category")
      var res = stmt.executeQuery(s"select count (title) as count, category from temp where $gloCriteria='$critParam' group by category order by count desc")

      while (res.next()) {
        println(s"${res.getString(2)}, ${res.getString(1)}")          
      }//End of 'while (res.next())'
      additionalQueryPrompt()
    }//End of filtergroupByCategory()

    def filtergroupByLanguage(){
      println("Grouping by Language")
      var res = stmt.executeQuery(s"select count (title) as count, language from temp where $gloCriteria='$critParam' group by language order by count desc")

      while (res.next()) {
        println(s"${res.getString(2)}, ${res.getString(1)}")          
      }//End of 'while (res.next())'
      additionalQueryPrompt()
    }//End of filtergroupByLanguage()
  }//End of filteredQueryPrompt()
}//End of class