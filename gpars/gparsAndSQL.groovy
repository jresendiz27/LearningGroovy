@Grab(group='org.codehaus.gpars', module='gpars', version='1.0.0')
@Grab(group='mysql', module='mysql-connector-java', version='5.1.6')
@GrabConfig(systemClassLoader = true)
//
import groovy.sql.Sql
import static groovyx.gpars.GParsPool.withPool


def sql = Sql.newInstance(
                        "jdbc:mysql://localhost:3306/test",
                        "root",
                        "n0m3l0s3",
                        "com.mysql.jdbc.Driver");
                        
def sampleTable = sql.dataSet("example")
def numberOfNewRows = 0..5000

def startTime = System.currentTimeMillis()
def resultParallel = withPool(Runtime.getRuntime().availableProcessors()){
    numberOfNewRows.eachParallel{
         if(it % 2 == 0){
             sampleTable.add(to_id:1,from_id:2,message:"Test [2->1] "+it,sent_date:new Date())       
         } else {
             sampleTable.add(to_id:2,from_id:1,message:"Test [1->2] "+it,sent_date:new Date())       
         }
    }
}
def endTime = System.currentTimeMillis()
println "------------------${endTime-startTime} - milliseconds --------------------"
starTime = System.currentTimeMillis()
for(index in numberOfNewRows){
     if(index % 2 == 0){
         sampleTable.add(to_id:1,from_id:2,message:"Test [2->1] "+index+" "+Math.random(),sent_date:new Date())       
     } else {
         sampleTable.add(to_id:2,from_id:1,message:"Test [1->2] "+index+" "+Math.random(),sent_date:new Date())       
     }
}
endTime = System.currentTimeMillis()
println "------------------${endTime-startTime} - milliseconds --------------------"