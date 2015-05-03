import org.vertx.groovy.core.Vertx;

def mongoConfig = [
	"address":"ambienta2mx.places",
	"host":'127.0.0.1',
	"pool_size":17,
	"db_name":"Ambienta2MX-places"]

container.deployModule('io.vertx~mod-mongo-persistor~2.1.0', mongoConfig)
container.deployVerticle('VertxServerVerticle.groovy')