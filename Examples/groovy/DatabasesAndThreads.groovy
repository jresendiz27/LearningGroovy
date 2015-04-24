@GrabConfig(systemClassLoader=true)
@Grab(group='org.codehaus.gpars', module='gpars', version='1.2.1')
@Grab(group='mysql', module='mysql-connector-java', version='5.1.35')

import groovy.sql.Sql
//import groovyx.gpars.GParsPool;
//import groovyx.gpars.GParsExecutorsPool;
import groovyx.gpars.*;

sqlInstance = Sql.newInstance('jdbc:mysql://localhost:3306/randomInsert','root','n0m3l0s3','com.mysql.jdbc.Driver')

def randomLimits = [250,17,100,117]
def insert(def randomValue){
    def currentThread = ""+Thread.currentThread()+""
    for(i in 1..50000){
         sqlInstance.execute("""insert into container(randomNumber,originThread) values (${(randomValue)*Math.random()}, $currentThread)""")   
    }
    println currentThread
}
/*
GParsPool.withPool{
    randomLimits.eachParallel{
        insert(it)
    }
}*/
GParsExecutorsPool.withPool(4){
	/*GParsExecutorsPool.executeAsyncAndWait(
		{insert(250)},
		{insert(17)},
		{insert(100)},
		{insert(117)}
		)*/
    randomLimits.eachParallel{
        insert(it)
    }
}

/*
println "***************************************************************************************"
println "RandomNumber, originThread"
sqlInstance.eachRow("select * from container"){
    println "$it.randomNumber, $it.originThread"
}
println "***************************************************************************************"
*/