@Grab(group='javax.mail', module='mail', version='1.4.7')

import javax.mail.*
import javax.mail.internet.*;
import javax.mail.search.*;

def host = "imap.gmail.com"
def port = "993"
def username = "yourmail@gmail.com"
def password = "yourpass"

def OneMB = 1024000
def FiveMB = 5120000

Properties props = new Properties()
props.setProperty("mail.store.protocol", "imaps")
props.setProperty("mail.imap.host", host)
props.setProperty("mail.imap.port", port)
props.setProperty("mail.imap.ssl.enable", "true");

def session = Session.getDefaultInstance(props, null)
def store = session.getStore("imaps")

store.connect(host, username, password)
def folder = store.getFolder("INBOX")

folder.open(Folder.READ_ONLY)

Flags seen = new Flags(Flags.Flag.SEEN);
FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
Message[] msgs = folder.search(unseenFlagTerm);
//
FetchProfile fetchProfile = new FetchProfile()
fetchProfile.add(FetchProfile.Item.ENVELOPE)
//fetchProfile.add("X-mailer")
folder.fetch(msgs,fetchProfile)
//
for ( i in 0..(msgs.length -1) ) {
	println "***************************************************"
	println "***************************************************"
	println"${msgs[i].receivedDate}"
	println "${msgs[i].sender}"
	println "${msgs[i].from}"
    println "${msgs[i].subject}"
    //println "${msgs[i].text}"
    msgs[i].writeTo(System.out)
    println "***************************************************"
    println "***************************************************"
}
/*folder.messages.each { msg ->
	try {
	    def content = msg.content
	    if (content instanceof Multipart) {
	        for (i in 0..(content.count - 1)) {
	            BodyPart bodyPart = content.getBodyPart(i)
	            if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.disposition)
	                    && bodyPart.size > OneMB) {
	                println "big attachment: ${bodyPart.fileName} = ${bodyPart.size}"
	                folder.copyMessages([msg] as Message[], bigAttachmentFolder);
	                if (bodyPart.size > FiveMB) {
	                  folder.copyMessages([msg] as Message[], hugeAttachmentFolder);
	                }
	            }
	        }
	    }
	} catch (Exception ignore) {
	    println "error processing message, no big deal, moving on"
	}
}*/
println "-----------done------------"