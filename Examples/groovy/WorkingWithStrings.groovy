/*
@author: Juan Alberto Reséndiz Arteaga
Simple example of string management with Groovy!
*/
def cadena="Una cadena de caracteres"
// Inferencia automática del tipo de variable!!!
println cadena.class.name

def unaCadenaMasCompleja = """
Una cadena multilinea
yay!
 """
//Automaticamente infiere también el primitivo de Java 
println unaCadenaMasCompleja.class.name

/*
Ahora intentemos con una cadena compuesta, se les conoce como GStrings
*/
def nombre = "Pepo"
def otraCadena = "Mi nombre es ${nombre}"
println otraCadena.class.name
println otraCadena

/*Belleza de Groovy, metaClass*/
String.metaClass.esPalindromo = {
	delegate == delegate.reverse()
}

def otraPalabra = "oso"
println "oso".esPalindromo()