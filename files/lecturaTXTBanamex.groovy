/*
Created by Juan Alberto Resendiz Arteaga

Script de groovy para la lectura de archivos de banamex (Sistema de flujo de fondos)
*/
//Variables auxiliares
def contadorLineas = 1
Calendar calendar = GregorianCalendar.getInstance();
//Variables declaradas en progress
def iFecha
def iDia
def iMes
def iAnio
def iSuc
def iCta
def iBan
def iMov
def resp
try{
    def archivoTxt = new File("banamex.txt").eachLine { lineaDeArchivo -> 
        if(contadorLineas == 1){
            if(lineaDeArchivo.length() != 6 ){
                throw new Exception("El archivo de concentracion de Banamex es erroneo, error en la linea ${contadorLineas}") 
                return
            } else {
                iDia = Integer.parseInt(lineaDeArchivo.substring(0,2))
                iMes = Integer.parseInt(lineaDeArchivo.substring(2,4))
                iAnio = 2000 + Integer.parseInt(lineaDeArchivo.substring(4))
                calendar.set(iAnio,iMes-1,iDia)
                iFecha = calendar.getTime()
                //println "---> ${iDia} - ${iMes} - ${iAnio} == ${iFecha}"
            }
        } else if( contadorLineas == 2 ){
            if(lineaDeArchivo.length() != 29 ){
                throw new Exception("El archivo de concentracion de Banamex es erroneo, error en la linea ${contadorLineas}") 
                return
            } else {
                println lineaDeArchivo
                iSuc = lineaDeArchivo.substring(0,4)
                iCta = lineaDeArchivo.substring(4,23)
                iMov = lineaDeArchivo.substring(23)
                iCta = iSuc + "-" + (String.format("%07d", Integer.parseInt(iCta)))
                //println "---> ${iSuc} - ${iCta} - ${iMov}"
                def chequera
                /*
                def chequera = Chequera.findByCuentaCasa(iCta)            
                */
                if(!chequera){
                    throw new Exception("No se encuentra la chequera con cuenta ${iCta}") 
                    return
                }
            }
        }
        contadorLineas++ 
    }
}catch(Exception e){
    println e
}