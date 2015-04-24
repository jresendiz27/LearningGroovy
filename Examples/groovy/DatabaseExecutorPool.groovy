@GrabConfig(systemClassLoader=true)
@Grab(group='org.codehaus.gpars', module='gpars', version='1.2.1')
@Grab(group='mysql', module='mysql-connector-java', version='5.1.35')

import groovy.sql.Sql
import groovyx.gpars.*;

def randomLimits = [500,700,800,750]
def insert(def randomValue){
    def sqlInstance = Sql.newInstance('jdbc:mysql://localhost:3306/randomInsert','root','n0m3l0s3','com.mysql.jdbc.Driver')
    def currentThread = ""+Thread.currentThread()+""
    def random = (int) randomValue*Math.random()
    def sum = (1..random).sum()
    for(i in 1..50000){
        sqlInstance.execute("""insert into container(randomNumber,originThread, sum) values ($random, $currentThread, $sum)""")   
    }
}
// Workers/Executor Pool
def inicio = System.currentTimeMillis()
GParsExecutorsPool.withPool{
    randomLimits.eachParallel{
        insert(it)
    }
}
def fin = System.currentTimeMillis()
println ("[GParsExecutorsPool] Tiempo de ejecución: ${fin-inicio} ms")

/*
println "***************************************************************************************"
println "RandomNumber, originThread"
sqlInstance.eachRow("select * from container"){
    println "$it.randomNumber, $it.originThread"
}
println "***************************************************************************************"
*/