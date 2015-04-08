@Grab(group='io.vertx', module='vertx-core', version='2.1.4')
@Grab(group='io.vertx', module='vertx-platform', version='2.1.4')
@Grab(group='io.vertx', module='lang-groovy', version='2.1.1-final')

import org.vertx.groovy.core.Vertx;

class TestingVertx{
  static void main(String[] args){
    def server = Vertx.newVertx().createHttpServer()
    def port = System.getenv('PORT') != null ? System.getenv('PORT') as int : 8080

    server.requestHandler { request ->
        request.response.putHeader("Content-Type", "text/plain")
        request.response.end("hello, world")
    }
    
    server.listen(port, '0.0.0.0')
    println "Server created at 8080"
    System.in.read();
  }
}