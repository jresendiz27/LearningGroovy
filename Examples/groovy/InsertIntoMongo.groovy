@GrabConfig(systemClassLoader=true)
@Grab(group='mysql', module='mysql-connector-java', version='5.1.35')
import groovy.sql.Sql
def sqlInstance = Sql.newInstance('jdbc:mysql://localhost:3306/consulta','root','n0m3l0s3','com.mysql.jdbc.Driver')
def lista = sqlInstance.rows("""
    select 
        hex_lat as hex_lat,
        hex_lon as hex_lon,
        itrf_lat as itrf_lat,
        itrf_lon as itrf_lon,
        nad27_lat as nad27_lat,
        nad27_lon as nad27_lon,
        height as height,
        localidad as localidad,
        estado as estado,
        municipio as municipio
    from
    places limit 200002,100000;
    """)
File jsonMysql =  new File('/home/alberto/places_3.json');
def lista2 = []
lista.each{ it-> 
    lista2.add("""{
        "sexagesimal_coordinates":{
            "hex_lat":${Double.parseDouble(it.hex_lat.replaceAll("'",""))}, 
            "hex_lon":${Double.parseDouble(it.hex_lon.replaceAll("'",""))}
        },
        "itrf_coordinates":{
            "itrf_lat":${Double.parseDouble(it.itrf_lat.replaceAll("'",""))},
            "itrf_lon":${Double.parseDouble(it.itrf_lon.replaceAll("'",""))}    
        },
        "nad27_coordinates":{
            "nad27_lat":${Double.parseDouble(it.nad27_lon.replaceAll("'",""))},
            "nad27_lon":${Double.parseDouble(it.nad27_lon.replaceAll("'",""))}
        },
        "height":${Double.parseDouble(it.height.replaceAll("'",""))},
        "localidad":"${it.localidad.replaceAll("'","")}",
        "estado":"${it.estado.replaceAll("'","")}",
        "municipio":"${it.municipio.replaceAll("'","")}",
        "fullName": "${it.estado.replaceAll("'","")+", "+it.municipio.replaceAll("'","")+", "+it.localidad.replaceAll("'","")}"  
    }""")
}
jsonMysql.text=("[" + lista2.join(",\n") + "]")
return true