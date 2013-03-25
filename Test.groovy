//Multi Dimensional Array in Groovy:
// one way
list = [[2, 0, 1], [1, 5, 2], [1, 0, 3]]
def sublist = []
list.each {
  sublist = list*.first()
}
print sublist

// 2nd way
list = [[2, 0, 1], [1, 5, 2], [1, 0, 3]]
def sublist = []
list.each {
  sublist = list.collect {it[0]}
}
print sublist


bigListIter = bigList.iterator()
firstOnlyIter = [
    hasNext: { -> bigListIter.hasNext() },
    next: { -> bigListIter.next().first() }
] as Iterator

for (it in firstOnlyIter) {
    println it
}




// Regular Expression in Groovy
//def s= 'horse house accountId'
def JsonFileContents = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\test.txt")
def s = JsonFileContents.text
def l= []
s.eachMatch( /ho.se/ ){ l << it } 
l.each{ print it }

\\ For AccountId I used this: 
s.eachMatch( /\"accountId\": \"[0-9]{3}\"/ ){ l << it } 
\\ For accountId & Category I used:  /\"account(Id|Category)\":\"[\w\s]+\"/


// REGEX References for Groovy-

note- Brackets in Groovy Regex will repeat the searches , Also Groovy has Match Flags like:  (?i) or (?ix)- extended and case-insensitive modes
&& - intersection, ^ is subtract 


def s= 'horse house'
assert s =~ /ho.se/ //to check for the first occurence only
def m= (s =~ /ho.se/)
assert m.size() == 2 && m[0] == 'horse' && m[1] == 'house' //to retrieve all occurences

def l= []
s.eachMatch( /ho.se/ ){ l << it } //alternative syntax
assert l == ['horse', 'house']
def l2= []
s.eachMatch( /abc/ ){ l2 << it } //no matches
assert l2 == []
def l3= []
s.eachMatch( /hor./ ){ l3 << it } //one match only
assert l3 == ['hors']



// MY REGULAR EXPRESSION- JAVA

 String longString = " Derek Banas CA 12345 PA 1-(412)555-1212 johnsmith@hotmail.com 412-555-1234 412 555-1234 Drackosian accountId : 100 rexd098avid@mail.com "; 
 My Regex to find email : ("(\\w+@\\w+\\.[A-Za-z]{2,4})", longString)
 My Regex to find various types of phone number: regexChecker("( [0-9]( |-)?)?(\\(?[0-9]{3}\\)?)(( |-)?[0-9]{3})(( |-)?[0-9]{4})", longString);


Code:
    
      public static void regexChecker(String theRegex, String str2Check){
        
        // You define your regular expression (REGEX) using Pattern
        
        Pattern checkRegex = Pattern.compile(theRegex);
        
        // Creates a Matcher object that searches the String for
        // anything that matches the REGEX
        
        Matcher regexMatcher = checkRegex.matcher( str2Check );
        
        // Cycle through the positive matches and print them to screen
        // Make sure string isn't empty and trim off any whitespace
        
        while ( regexMatcher.find() ){
            if (regexMatcher.group().length() != 0){
                System.out.println( regexMatcher.group().trim() );
                
                // You can get the starting and ending indexs
                
                System.out.println( "Start Index: " + regexMatcher.start());
                System.out.println( "Start Index: " + regexMatcher.end());
            }
        }
        
        System.out.println();
    } 



// SOURCE CODE : TWO IMPORTANT METHODS TO WRITE FILES IN GROOVY

//FIRST
def list = ['rex','rex','jim','Tim']
def com =['rex','rex','jim','Kim']
def i = 0

File lstFile = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\file.txt")
lstFile.withWriter{ it.println ("Hello")



while (i < list.size())
{
  if (list[i] == com[i] ) {
 
   it.println 'Match'
   }
    else { it.println 'no Match'}
    i++ 
 }
 
       }


// From Derek- has code to replace as well

import java.util.regex.*;

public class LessonNineteen{
    
    public static void main(String[] args){
        
        String longString = " Derek Banas CA 12345 PA (412)555-1212 johnsmith@hotmail.com 412-555-1234 412 555-1234 "; 
        String strangeString = " 1Z aaa **** *** {{{ {{ { ";
        
        /*
        [ ]  Insert characters that are valid
        [^ ]  Insert characters that are not valid
        \\s  Any white space
        \\S  Any non white space
        {n,m}  Whatever proceeds must occur between n and m times
        */
        
        // Word must contain letters that are 2 to 20 characters in length
        // [A-Za-z]{2,20} 0r \w{2,20}
        
        regexChecker("\\s[A-Za-z]{2,20}\\s", longString);
        
        /*
        \\d  Any digits 0-9
         \\D  Anything not a number
         {n}  Whatever proceeds must occur n times
         */
        
        // Only 5 digits
        // \\s[0-9]{5}\\s or \\d{5}
        
        regexChecker("\\s\\d{5}\\s", longString);
        
        /*
        |  Is used for OR clause situations
        */
        
        // Must start with a A or C, followed by 1 letter in brackets
        // Must be a maximum of 2 characters in length
        // A[KLRZ]|C[AOT]
        
        regexChecker("A[KLRZ]|C[AOT]", longString);
        
        /*
        {n,}  Whatever proceeds must occur at least n times
        +  Whatever proceeds must occur one or more times
        . ^ * + ? { } [ ] \ | ( )  Characters that must be escaped or backslashed
        */
        
        // Grab any string that contains 1 or more !
        
        regexChecker("(\\{{1,})", strangeString);
        regexChecker("(\\{+)", strangeString);
        
        // Get anything that occurs 3 times except newline
        // .  Anything but newline
        
        regexChecker(".{3}", strangeString);
        
        /*
        \\w  Any word type character A-Z, a-z, 0-9, _
        \\W  Any non word character
        *  Occurs zero or more times
        */
        
        regexChecker("\\w*", strangeString);
        
        regexChecker("[A-Za-z0-9._\\%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", longString);
        
        
        // ?  0 or 1 of what proceeds it
        
        regexChecker("([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})", longString);
        
        regexReplace(longString);
        
    }
    
    public static void regexChecker(String theRegex, String str2Check){
        
        // You define your regular expression (REGEX) using Pattern
        
        Pattern checkRegex = Pattern.compile(theRegex);
        
        // Creates a Matcher object that searches the String for
        // anything that matches the REGEX
        
        Matcher regexMatcher = checkRegex.matcher( str2Check );
        
        // Cycle through the positive matches and print them to screen
        // Make sure string isn't empty and trim off any whitespace
        
        while ( regexMatcher.find() ){
            if (regexMatcher.group().length() != 0){
                System.out.println( regexMatcher.group().trim() );
                
                // You can get the starting and ending indexs
                
                System.out.println( "Start Index: " + regexMatcher.start());
                System.out.println( "Start Index: " + regexMatcher.end());
            }
        }
        
        System.out.println();
    }
    
    public static void regexReplace(String str2Replace){
        
        // REGEX that matches 1 or more white space
        
        Pattern replace = Pattern.compile("\\s+");
        
        // This doesn't really apply, but this is how you ignore case
        // Pattern replace = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        
        // trim the string t prepare it for a replace
        
        Matcher regexMatcher = replace.matcher(str2Replace.trim());
        
        // replaceAll replaces all white space with commas
        
        System.out.println(regexMatcher.replaceAll(", "));
        
    }
    
}




//SECOND

def list = ['rex','rex','jim','Tim']
def com =['rex','rex','jim','Kim']
def i = 0

File lstFile = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\file.txt")
lstFile.withWriter{ out ->
  list.each {out.println it}; out.println ("Hello")



while (i < list.size())
{
  if (list[i] == com[i] ) {
 
   out.println 'Match'
   }
    else { out.println 'no Match'}
    i++ 
 }
 
         }







// SOAPUI SOURCE CODE TO READ JSON
import groovy.json.*
import java.util.*
def slurper = new JsonSlurper()
def JsonFileContents = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\response.JSON").text
def result = slurper.parseText(JsonFileContents)
def requestSet = testRunner.testCase.getTestStepByName( "accountDetails" );
requestSet.getProperty("request").setValue(JsonFileContents);




// SOURCE CODE TO MANIPULATE (  iPad Profile)
import groovy.json.*
import java.util.*

def slurper = new JsonSlurper()
File meat = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\meat.txt")
def JsonFileContents = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\response.JSON").text
def result = slurper.parseText(JsonFileContents)

def cat = []
cat = result.accountSummary.data.accounts
def acctCat = []
cat.each {acctCat << it.accountCategory}
acctCat.unique()


def list =[]
def bug = []
list = result.accountSummary.data.accounts
bug =  result.accountSummary.data.accounts.accountCategory

def array = [] 
def j = 0
def b = 0
def p =1
def yaynay = ['true', 'false']
def x = [0,1]

meat.withWriter{ out ->

out.println('{ \n "profile":  [')

while (j < acctCat.size()) { 


while (acctCat[j] == bug[b])  {

array[b] = list[b].accountId;
b++
}

out.println('\t{')
out.println ('"accountCategory":'+'"'+ acctCat[j]+'",\n "accounts": [')

array.removeAll{it == null}
Collections.shuffle(array)


def i = 1

def last = array.size()
array.each() {Collections.shuffle(x); out.print( '{ "accountId": "'+it + '", "hideAcct" : '+ yaynay[x[0]]+'}'); while (i < last) {out.print ',\n'; i ++ ; break}} ;
j++
out.println('\n\t]')
out.print('}'); while ( p <  acctCat.size() ) {out.print( ',\n'); p++ ; break} ;

array.removeAll{it}
}

out.println('\n\t]\n}')
}

def makepretty = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\meat.txt").text
File formatted = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\formatted.JSON")

def serious = JsonOutput.prettyPrint(makepretty)

formatted.withWriter{ out ->
out.println(serious) }




// SOAPUI SOURCE CODE TO PRINT RESPONSE TO FILE
def myOutFile = "C:/Users/rdavid/Desktop/Groovy Test/response.txt"
def response = context.expand('${testStepName#Response}' )
def f = new File(myOutFile)
f.write(response, "UTF-8")
 

CR** BELOW for reference 

http://stackoverflow.com/questions/600733/using-java-to-find-substring-of-a-bigger-string-using-regular-expression
 
 

import groovy.json.*

def jsonText = '''
{
    "message": {
        "header": {
            "from": "mrhaki",
            "to": ["Groovy Users", "Java Users"]
        },
        "body": "Check out Groovy's gr8 JSON support."
    }
}       
'''

def json = new JsonSlurper().parseText(jsonText)

def header = json.message.header
assert header.from == 'mrhaki'
assert header.to[0] == 'Groovy Users'
assert header.to[1] == 'Java Users'
assert json.message.body == "Check out Groovy's gr8 JSON support."



class groovyXmlParsing 
{
static main(args) 
{
def xmlFileContents = new File("C:\\code\\groovyXmlParsing\\movies.xml").text; 
def movies = new XmlSlurper().parseText(xmlFileContents);
  
/*
* Show how many moves we have in the document
*/
println "Number of movies: ${movies.movie.size()}\n";
  
/*
* Display the titles and plot for each movie.
*/
movies.movie.each { movie ->
  
println "Movie: ${movie.@name}";
println "Plot: ${movie.plotSummary.text()}";
println "";
  
};
}
  
}


import groovy.json.*
def slurper = new JsonSlurper()
def xmlFileContents = new File("C:\\Documents and Settings\\94966\\Desktop\\Groovy\\response.JSON").text
def result = slurper.parseText(xmlFileContents)

log.info result

