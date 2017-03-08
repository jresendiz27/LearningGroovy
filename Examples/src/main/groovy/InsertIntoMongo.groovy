@GrabConfig(systemClassLoader = true)
@Grab(group = 'mysql', module = 'mysql-connector-java', version = '5.1.35')
@Grab(group = 'com.gmongo', module = 'gmongo', version = '1.3')
//
import groovy.sql.Sql

//
import com.gmongo.GMongo

//
def mongo = new GMongo()
def ambienta2MXDB = mongo.getDB("Ambienta2MX-places")
//
def sqlInstance = Sql.newInstance('jdbc:mysql://localhost:3306/consulta', 'root', 'n0m3l0s3', 'com.mysql.jdbc.Driver')
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
    places;
    """)
ambienta2MXDB.places.drop()
lista.each { it ->
    def mapaLugar = [
            'location'               : [
                    'type'       : "Point",
                    'coordinates': [
                            Double.parseDouble(it.itrf_lat.replaceAll("'", "")),
                            Double.parseDouble(it.itrf_lon.replaceAll("'", ""))
                    ]
            ],
            'sexagesimal_coordinates': [
                    Double.parseDouble(it.hex_lat.replaceAll("'", "")),
                    Double.parseDouble(it.hex_lon.replaceAll("'", ""))
            ],
            'itrf_coordinates'       : [
                    Double.parseDouble(it.itrf_lat.replaceAll("'", "")),
                    Double.parseDouble(it.itrf_lon.replaceAll("'", ""))
            ],
            'nad27_coordinates'      : [
                    Double.parseDouble(it.nad27_lat.replaceAll("'", "")),
                    Double.parseDouble(it.nad27_lon.replaceAll("'", ""))
            ],
            'height'                 : Double.parseDouble(it.height.replaceAll("'", "")),
            'town'                   : it.localidad.replaceAll("'", ""),
            'state'                  : it.estado.replaceAll("'", ""),
            'city'                   : it.municipio.replaceAll("'", ""),
            'fullName'               : (it.estado.replaceAll("'", "") + ", " + it.municipio.replaceAll("'", "") + ", " + it.localidad.replaceAll("'", "")),
            'zipCode'                : '',
            'extraInfo'              : [""]
    ];
    ambienta2MXDB.places << mapaLugar;

}
/*
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
            "nad27_lat":${Double.parseDouble(it.nad27_lat.replaceAll("'",""))},
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
*/
return true