@GrabConfig(systemClassLoader=true)
@Grab(group='commons-logging', module='commons-logging', version='1.1.1')
@Grab(group='commons-beanutils', module='commons-beanutils', version='1.8.0')
@Grab(group='commons-lang', module='commons-lang', version='2.4')
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')

import groovyx.net.http.HTTPBuilder
 
// https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=AIzaSyCo72iVxPewCLtXKmoeiWSyNAAVTqIiVvs
// "215251","1021746"
def http = new HTTPBuilder( 'https://maps.googleapis.com/maps/api/geocode/' )
 
http.get( path: 'json', query: [latlng: '21.5251,-102.1746', key:'AIzaSyCo72iVxPewCLtXKmoeiWSyNAAVTqIiVvs'] ) { resp, json ->
    println resp.status
    println "results: "
    println json.results[-2].formatted_address
}