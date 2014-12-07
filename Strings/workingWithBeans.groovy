import groovy.transform.ToString
//Without verbosing keys!
@ToString
class Student {
    String name
}
def s = new Student(name:'John')
println "Verbosing withouth keys"
println s.toString()

println "\n"
//Verbosing keys!
@ToString(includeNames = true, excludes = 'password')
class OtherStudent {
    String name
    String password
}
def ns = new OtherStudent(name:'Kelly')
println "Verbosing with keys and excluding some information"
println ns.toString()