import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

def JsonContent = new File("/home/alberto/Documents/Github/Ambienta2MX/Ambienta2MX-Docs/Resources/WeatherUndergroundDatabase2.json").text
def object = new JsonSlurper().parseText(JsonContent)

def iterator = 0
def mexico = object['conds'].findAll{
    if(!(it.value.country in ["GUATEMALA","US","Honduras","HONDURAS","BELIZE"])){
        return true
    }
}
mexico.each {
    println it.value.country
}
def map = [:]
map.conds = mexico
new File("/home/alberto/Documents/Github/Ambienta2MX/Ambienta2MX-Docs/Resources/WeatherUndergroundDatabaseFiltered.json").write(new JsonBuilder(map).toPrettyString())
return true