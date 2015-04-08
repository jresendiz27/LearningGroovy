def results = [];


File[] files = new File("/media/ADATA HD710/IEEE/Candidatos").listFiles();

for (File file : files) {
    if (file.isFile()) {
        if(!(file.getName()=~'DS_')){
            results.add(file.getName());
        }
    }
}
def gama = []
def pepo = [] 
def alex = []
for(file in results){
    def who = (int)Math.random()*17;
    if(who%3 == 0){
       gama.add(file)
    }
    if(who%3 == 1){
        pepo.add(file)
    }
    if(who%3 == 2){
        alex.add(file)
    }
}
println "-----------------------------------------------------------"
println "Gama [${gama.size()}]: " + gama
println "-----------------------------------------------------------"
println "Pepo [${pepo.size()}]: " + pepo 
println "-----------------------------------------------------------"
println "Alex [${alex.size()}]: " + alex
println "-----------------------------------------------------------"