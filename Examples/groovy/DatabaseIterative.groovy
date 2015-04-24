@GrabConfig(systemClassLoader=true)
@Grab(group='org.codehaus.gpars', module='gpars', version='1.2.1')
@Grab(group='mysql', module='mysql-connector-java', version='5.1.35')

import groovy.sql.Sql
import groovyx.gpars.*;

def randomLimits = [500,700,800,750]
// Forma iterativa
def inicio = System.currentTimeMillis()
for(j in 0..3){
    def sqlInstance = Sql.newInstance('jdbc:mysql://localhost:3306/randomInsert','root','n0m3l0s3','com.mysql.jdbc.Driver')
    int value = (randomLimits[j])*Math.random()
    def sum = (1..value).sum()
    for(i in 1..50000){
        sqlInstance.execute("""insert into container(randomNumber,originThread, sum) values ($value, $j, $sum)""")   
    }
}
def fin = System.currentTimeMillis()
println ("[Iterative] Tiempo de ejecución: ${fin-inicio} ms")