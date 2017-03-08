@Grab(group = 'com.xlson.groovycsv', module = 'groovycsv', version = '1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

/*
* Let's consider the next headers
* "CLAVE","CVE_GEOEST","CVE_AGEB","LATITUD","LONGITUD","ALTITUD","CARTA_TOPO","TIPO","NOMBRE_EDO","NOMBRE_MUN"
* Needed ones: "LATITUD","LONGITUD"
* Desired: "LATITUD","LONGITUD","ALTITUD","NOMBRE_EDO","NOMBRE_MUN"
* */

def csv = new File("/home/alberto/Documents/Github/Ambienta2MX/FastEagle/src/main/resources/AHL01Maestro.csv").text
def data = parseCsv(csv)
def counter = 0
for (line in data) {
    println "${line.CLAVE} \t ${line.LATITUD.class.getSimpleName()} \t ${line.LONGITUD} \t ${line.ALTITUD}"
    if (counter == 20) {
        break
    }
    counter++
}
/*
// Most left point, near Tijuana
    def final Double maxLongitude = -117.083333
    def final Double maxAltitude = 32.533333
    // Most right point, near Cancun
    def final Double minLongitude = 21.133333
    def final Double minAltitude = -86.733333
*/