@GrabConfig(systemClassLoader=true)
@Grab(group='commons-logging', module='commons-logging', version='1.1.1')
@Grab(group='commons-beanutils', module='commons-beanutils', version='1.8.0')
@Grab(group='commons-lang', module='commons-lang', version='2.4')
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
@Grab(group='mysql', module='mysql-connector-java', version='5.1.35')
@Grab(group='org.codehaus.gpars', module='gpars', version='1.2.1')

import groovyx.net.http.HTTPBuilder
import groovy.sql.Sql
import groovyx.gpars.*;
 
def sqlInstance = Sql.newInstance('jdbc:mysql://localhost:3306/consulta?useUnicode=true&characterEncoding=UTF-8','root','n0m3l0s3','com.mysql.jdbc.Driver')
def consulta = sqlInstance.rows("""
select 
    concat('', localidades.latitud) as latitud, 
    concat('', localidades.longitud) as longitud, 
    concat('', localidades.altitud) as altitud,
    localidades.nombre as localidad,
    municipios.nombre as municipio,
    estados.nombre as estado,
    estados.clave as clave_estado
from 
    localidades 
inner join
    municipios on municipios.id = localidades.municipio_id
inner join
    estados on estados.id = municipios.estado_id;
""")    

def localidades = consulta.collate(149819)
//def localidades = consulta.collate(20)
def resolveLatLon = { rows ->
    for(row in rows){
        def localSQLInstance = Sql.newInstance('jdbc:mysql://localhost:3306/consulta?useUnicode=true&characterEncoding=UTF-8','root','n0m3l0s3','com.mysql.jdbc.Driver')
        def http = new HTTPBuilder( 'http://mapserver.inegi.org.mx/traninv/' )
        //
        row.latitud = "$row.latitud"
        row.longitud = "$row.longitud"
        def latitud = 0.0
        latitud += Double.parseDouble("${row.latitud.substring(0,row.latitud.size()-4)}") 
        latitud += Double.parseDouble("${row.latitud.substring(row.latitud.size()-4, row.latitud.size()-2)}")/60 
        latitud += Double.parseDouble("${row.latitud.substring(row.latitud.size()-2,)}")/3600
        //
        def longitud = 0.0
        longitud += Double.parseDouble("${row.longitud.substring(0,row.longitud.size()-4)}") 
        longitud += Double.parseDouble("${row.longitud.substring(row.longitud.size()-4, row.longitud.size()-2)}")/60 
        longitud += Double.parseDouble("${row.latitud.substring(row.longitud.size()-2,)}")/3600
        //
        http.get( 
            path: 'transcoord.do', 
            query: [datos: """{"degX":$longitud,"degY":$latitud,"utmX":"","utmY":"","utmZona":"11","cclX":"","cclY":"","datum":"itrf92","proy":"dms"}"""] 
            ) { resp, json ->
                println row
                def query = """insert into places(
                                hex_lat,
                                hex_lon,
                                itrf_lat,
                                itrf_lon,
                                nad27_lat,
                                nad27_lon,
                                height,
                                localidad,
                                estado,
                                municipio,
                                clave
                            ) 
                        values 
                            (
                                '$row.latitud', 
                                '$row.longitud', 
                                '$json.degX_ITRF',
                                '$json.degY_ITRF',
                                '$json.degX_NAD',
                                '$json.degY_NAD',
                                '$row.altitud',
                                '$row.localidad',
                                '$row.estado',
                                '$row.municipio',
                                '$row.clave_estado'
                            )"""
                localSQLInstance.execute(query)   
            }
    }
}
def inicio = System.currentTimeMillis()
GParsPool.withPool{
    localidades.eachParallel{
        resolveLatLon(it)
    }
}
def fin = System.currentTimeMillis()
println ("[INEGI Solver] Tiempo de ejecución: ${fin-inicio} ms")