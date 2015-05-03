import org.vertx.groovy.core.Vertx;
import org.vertx.groovy.core.http.RouteMatcher
import groovy.json.*


def eventBus = vertx.eventBus

def server = vertx.createHttpServer()
def routeMatcher = new RouteMatcher();
routeMatcher.get("/places/:latitude/:longitude/:maxDistance") { request ->
	def query = [
		action:'find', 
		collection:'places', 
		matcher: [
			location:[
				'$near':[
					'$geometry':[
						type: "Point", coordinates:[Double.parseDouble(request.params.latitude ?: 0), Double.parseDouble(request.params.longitude ?: 0)]
						],
					'$maxDistance': Double.parseDouble(request.params.maxDistance ?: 100)
					]
				]
			]
		]
	eventBus.send('ambienta2mx.places',query){ resp -> 
		request.response.putHeader("Content-Type", "application/json")		
		request.response.end "${JsonOutput.toJson(resp.body.results)}"
	}
}
server.requestHandler(routeMatcher.asClosure()).listen(7777,"localhost");