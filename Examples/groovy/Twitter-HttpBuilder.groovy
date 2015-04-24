@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
@Grab(group='oauth.signpost', module='signpost-core', version='1.2.1.2')
@Grab(group='oauth.signpost', module='signpost-commonshttp4', version='1.2.1.2')

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

def consumerKey = ""
def consumerSecret = ""
def accessToken = ""
def secretToken = ""
def twitter = new RESTClient( 'https://api.twitter.com/1.1/statuses/' )
twitter.auth.oauth(consumerKey, consumerSecret, accessToken, secretToken)
def postBody=[status: "Tweet from HttpBuilder and Twitter API!!! ${new Date()}"]
def response = twitter.post( path : 'update.json', body: postBody, requestContentType: URLENC )
println response.body.id
println response.body.url