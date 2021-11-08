
package example


import java.io.File
import java.io.PrintWriter
import scala.io.Source
import java.io.FileOutputStream
import java.nio.file.{Files, Paths, StandardCopyOption}

//HDFS Imports
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.PrintWriter;




//API imports
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import net.liftweb.json.DefaultFormats
import net.liftweb.json._



class apiManipulation {
    //def main (args: Array[String]){

    implicit val formats = DefaultFormats
    case class Information (author: String, title: String, description: String, url: String, source: String, image: String, category: String, language: String, country: String, published_at: String)
    case class Pagination (limit: Int, offset: Int, count: Int, total: Int)
    case class apiResponse (meta: List[Pagination], data: Array[Information])

    def apiRequestFunction(url: String): String = {
        val httpClient = new DefaultHttpClient() //Creates client object
        val httpResponse = httpClient.execute(new HttpGet(url)) //executes get url from the string that's passed
        val entity = httpResponse.getEntity() //'The stream comes in as an entity'
        var content = ""
        if (entity != null) { //If it's not null...
          val inputStream = entity.getContent() //...extract the content
          content = scala.io.Source.fromInputStream(inputStream).getLines.mkString //and convert that into a proper string
          inputStream.close
        }//End of 'apiRequestPrompt ()'
        httpClient.getConnectionManager().shutdown()
        return content
      }// End of apiRequestPrompt()


    var requestCategory=""
    var requestKeywords=""
    var requestLanguage=""
    var requestCountry=""
    //var requestOffset= 0
    var languageFirstIt = true
    var countryFirstIt = true
    var categoryFirstIt = true
    var keywordFirstIt = true
    var catParam = ""
    var contParam = ""
    var langParam = "" 
    var keyParam = ""

    


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


    def requestParameters(){
      if(catParam != "") println(catParam)
      if(contParam != "") println(contParam)
      if(langParam != "") println(langParam)
      if(keyParam != "") println(keyParam)

    }

    def filterRecurse(){  
        println() 
        println("Current Request Paramters:")         
        requestParameters()       
        println() 
        val check = scala.io.StdIn.readLine("Would you like to specify further?: ").trim.toLowerCase()
        check match {
          case "exit" => //Exit to directory
          case "yes"|"y" => specify()
          case "no"|"n" => requestToCSV()
          case _ => {
            println("I'm sorry, that's not an option")
            filterRecurse()
          }//End of 'case_'
        }//End of 'check match'
      }//End of filterRecurse()



    def firstGateRecurse(){
      println() 
      println("Would you like to request all articles or specify request?:\n[1] Request All\n[2] Specify ")
      println()
      var firstGate = scala.io.StdIn.readLine().trim.toLowerCase()
      firstGate match {
        case "exit" => //Exit to directory
        case "1" => requestToCSV()
        case "2" => specify()
        case _ => {
          allOtherRecurse()
          def allOtherRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, that's not an option. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => firstGateRecurse()
              case "no"|"n" => //Exit to directory
              case _ => {
                //println("I'm sorry, that's not an option")
                allOtherRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of allOtherRecurse()
        }//End of 'case_'
      }//End of 'firstGate match'
    }//End of 'firstGateRecurse()'
 


    def specify(){

      println()
      println("Select a criteria:\n[1] Category\n[2] Country \n[3] Language\n[4] Keyword\n")
      var secondGate = scala.io.StdIn.readLine()
      secondGate match {
        case "exit" => //Exit to directory
        case "1" => categorySelect()   
        case "2" => criteriaSelect("Country")
        case "3" => criteriaSelect("Language")
        case "4" => keywordSelect()
        case _ => {
          allOtherRecurse()
          def allOtherRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, that's not an option. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => specify()
              case "no"|"n" => firstGateRecurse()
              case _ => {
                //println("I'm sorry, that's not an option")
                allOtherRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of allOtherRecurse()
        }//End of 'case_'
      }//End of 'secondGate match'
    }//End of specify()





    def categorySelect(){
      println()
      println("Please select a Category:")
      println("[1] General\n[2] Business\n[3] Entertainment\n[4] Health\n" +
        "[5] Science\n[6] Sports\n[7] Technology\n")
      var catChoice = scala.io.StdIn.readLine()
      catChoice match{
        case "exit" => //Exit to directory
        case "1" => categoryApplication("general")
        case "2" => categoryApplication("business")         
        case "3" => categoryApplication("entertainment")                 
        case "4" => categoryApplication("health")       
        case "5" => categoryApplication("science")                 
        case "6" => categoryApplication("sports")              
        case "7" => categoryApplication("technology")
        case _ => {
          allOtherRecurse()
          def allOtherRecurse(){  
            println()   
            val check = scala.io.StdIn.readLine("I'm sorry, that's not an option. Would you like to try again?: ").trim.toLowerCase()
            check match {
              case "exit" => //Exit to directory
              case "yes"|"y" => categorySelect()
              case "no"|"n" => specify()
              case _ => {
                //println("I'm sorry, that's not an option")
                allOtherRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of allOtherRecurse()
        }//End of 'case_'
      }//End of 'catChoice match'


      def categoryApplication(a: String){
        var category = a

        //If this is your first Iteration of a category search...
        if(categoryFirstIt == true){
          requestCategory = ("="+category)
          categoryFirstIt = false

          category = category.capitalize
          catParam += (s"Category: $category ")
        }

        //...if it's not
        else{
          requestCategory += (","+category) 

          category = category.capitalize
          catParam += (s", $category ")   
        }      

        filterRecurse()
      }//End of categoryApplication()

    }//End of categorySelect()







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
        var requestCriteria = ""

        //Setting token
        if (criteria == "Language"){
          criteriaMap = languageMap
        }
        if (criteria == "Country"){
          criteriaMap = countryMap
        }

        

        //Choice Prompt
        println()
        val criteriaChoice = scala.io.StdIn.readLine(s"Which $criteria would you like to investigate?: ")
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
            requestCriteria = (k)


            //If the token is Language
            if (criteria == "Language"){

              //If this is the first iteration of a language search...
              if (languageFirstIt == true){
                requestLanguage=("="+requestCriteria)
                languageFirstIt = false 
                
                criteria = criteria.capitalize
                langParam += (s"Language: $v")               
              }

              //...if it's not
              else{
                requestLanguage +=(","+requestCriteria)
                criteria = criteria.capitalize
                langParam += (s", $v")
              }
            
              filterRecurse()
            }//End of 'if (criteria == "Language")'


            //If the token is Country (Same structure as Language)
            if (criteria == "Country"){
              if(countryFirstIt == true){
                requestCountry=("="+requestCriteria)
                countryFirstIt = false 
                
                criteria = criteria.capitalize
                contParam += (s"Country: $v")
              }
              else{
              requestCountry +=(","+requestCriteria)

              criteria = criteria.capitalize
              contParam += (s", $v")
              }
              filterRecurse()
            }//End of 'if (criteria == "Country")'
          }//End of 'if(k == criteria)'           
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
              case "no"|"n" => specify()
              case _ => {
                //println("I'm sorry, that's not an option")
                allOtherRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of allOtherRecurse()
        }//End 'if(criteriaMatch==false)'
      }//End of criteriaSelectRecurse()

    }//End of criteriaSelect()





    def keywordSelect(){

      //Prompt
      var keyword = scala.io.StdIn.readLine("Which Keyword would you like to search for?: ").trim
      println()

      //Confirm the Keyword
      keywordCheckRecurse()
      def keywordCheckRecurse(){
        println()
        var keywordcheck = scala.io.StdIn.readLine(s"Confirm Keyword: $keyword\n\nIs this Correct?: ").trim.toLowerCase()
        keywordcheck match {
          case "exit" => //Exit to directory
          case "yes"|"y" => keywordApplication(keyword)
          case "no"|"n" => keywordSelect()
          case _ => {
              println("I'm sorry, that's not an option.")   
              keywordCheckRecurse()         
          }//End of 'case_'
        }//End of 'keywordcheck match'
      }//End of keywordCheckRecurse()


      def keywordApplication(a: String){
        var keyword = a.toLowerCase()

        //If it's the first Iteration of the Keyword search...
        if(keywordFirstIt == true){
          requestKeywords = ("="+keyword)
          keywordFirstIt = false

          keyword = keyword.capitalize
          keyParam += (s"Keywords: $keyword")
        }

        //...If it's not
        else{
          requestKeywords += ("+"+keyword)

          keyword = keyword.capitalize
          keyParam += (s", $keyword")
        }
        filterRecurse()
      }//End of keywordApplication()     
    }//End of keywordSelect()











    //REQUEST TO CSV/////////////////////////////////////////////////////////////////////////////// 

    def requestToCSV(){  

      //Reset Search variable
      languageFirstIt = true
      countryFirstIt = true  
      categoryFirstIt = true
      keywordFirstIt = true
      catParam = ""
      contParam = ""
      langParam = "" 
      keyParam = ""






      //Construct API URL
      val outputCSV = new PrintWriter(new File("output.csv"))
      println()
      println()

        var data = apiRequestFunction("https://api.mediastack.com/v1/news?categories"+ requestCategory +"&keywords"+ requestKeywords +"&languages"+ requestLanguage +"&countries"+ requestCountry +"&limit=100&access_key=27e0516aaff4ec80385482051a6a71c7")
        var jValue = parse(data)
        val total = (jValue \ "pagination"\ "total").extract[Int]

        if(total == 0){
          println()
          println("No results!")
          println()
          firstGateRecurse()
        }
        else{

          


          var i = 0
          var requestOffset = 0

          while (i < total){
            var offsetString = requestOffset.toString
            //var requestAPI = "https://api.mediastack.com/v1/news?categories"+ requestCategory +"&keywords"+ requestKeywords +"&languages"+ requestLanguage +"&countries"+ requestCountry +"&offset"+ offsetString +"&limit=100&access_key=27e0516aaff4ec80385482051a6a71c7"
            //var data = apiRequestFunction("https://api.mediastack.com/v1/news?categories=sports&keywords=Brady&languages&offset="+offsetString+"&limit=100&access_key=27e0516aaff4ec80385482051a6a71c7")
            var loopdata = apiRequestFunction("https://api.mediastack.com/v1/news?categories"+ requestCategory +"&keywords"+ requestKeywords +"&languages"+ requestLanguage +"&countries"+ requestCountry +"&offset="+ offsetString +"&limit=100&access_key=27e0516aaff4ec80385482051a6a71c7")
            var loopjValue = parse(loopdata)
            val loopresults = loopjValue.extract[apiResponse]






            for(data <- loopresults.data){

              
              var title = data.title.trim()

              var category = data.category.trim()
              category match {
                case "general"|"business"|"entertainment"|"health"|"science"|"sports"|"technology" => category = category.capitalize   
                case _  => category = "Other"
              }//End of 'category match'



              var language = data.language.trim()
              var languageMatch = false
              var languagei = 0
              while(languageMatch == false && languagei <= languageMap.size){
                languageMap map {case (k,v) => 
                  if(k == language){
                    languageMatch =true
                    language = v
                  }//End of 'if(k == language)' 
                  languagei+=1
                }//End of 'languageMap map'
              }//End of 'while(languageMatch == false)'
              if(languageMatch==false){
                language ="Other"
              }




            var country = (data.country).trim()
            var countryMatch = false
            var countryi = 0 
            while(countryMatch == false && countryi <= countryMap.size){
              countryMap map {case (k,v) => 
                if(k == country){
                  countryMatch =true
                  country = v
                }//End of 'if(k == country)' 
                countryi+=1
              }//End of 'countryMap map'
            }//End of 'while(countryMatch == false)'
            if(countryMatch==false){
              country ="Other"
            }

              var source = data.source.trim()
              var url = data.url.replace("'\'", " ").trim()
              

              title = title.replace("|", " ")
              category = category.replace("|", " ")
              language = language.replace("|", " ")
              source = source.replace("|", " ")
              url = url.replace("|", " ")


              
              outputCSV.write(title + "|"+ category + "|" + language + "|" + country + "|" + source + "|" + url + "\n")
              
              i += 1
              
            }// End of 'for(data <- results.data)'
            requestOffset += 100
            println("TOTAL EQUALS= "+ i)
          }//End of While loop
          

          


          //**************************
          outputCSV.close
          //**********************

          saveRecurse
          def saveRecurse(){

            val save = scala.io.StdIn.readLine("Would you like to save the result to a file?: ").trim.toLowerCase()
            save match {
              case "exit" => //Exit to directory
              case "yes"|"y" => saveResults()
              case "no"|"n" => 
              case _ => {
                println("I'm sorry, that's not an option")
                filterRecurse()
              }//End of 'case_'
            }//End of 'check match'
          }//End of 'saveRecurse()'

          def saveResults(){

            var filename = scala.io.StdIn.readLine("What would you like to name the file?: ").trim.toLowerCase()
            val path = Files.copy(
              Paths.get("output.csv"),
              Paths.get(s"APIRequests/$filename"),
              StandardCopyOption.REPLACE_EXISTING
            )
 
            if (path != null) {
                println(s"moved the file successfully!")
            } else {
                println(s"could NOT move the file")
            }
          }//End of saveResults()




      }//End of 'else'   
      //offset += 100
    

      //Reset Request Paramteres
        requestCategory=""
        requestKeywords=""
        requestLanguage=""
        requestCountry=""

    }//End of requestToCSV()
    

    


    //firstGateRecurse()
    //countryFirstIt()
    //criteriaSelect()
    //requestToCSV()
    //specify()


  //}//End of Main
}//End of class