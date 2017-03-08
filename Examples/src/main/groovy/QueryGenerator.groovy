import groovy.json.JsonSlurper

def final operators = [
        'equal'             : '=',
        'lessThan'          : '<',
        'greaterThan'       : '>',
        'lessOrEqualThan'   : '<=',
        'greaterOrEqualThan': '>=',
        'like'              : ' like '
]
def jsonString = '''
    {
     "conditions":{
                "and":{
                    "equal":{
                    "id":1
                },
                "like":{
                    "lastName":"Alberto"
                }    
            }
        }   
    }
'''
def conditionsAnd = new JsonSlurper().parseText(jsonString)
def query = "select * from bird where "
/*def conditionsAnd = [
    and:[
        like:[cadena:'%una cadena de texto'],
        equal:[valorDec:10.5, otroDato:"""cadenagrande usando GString"""]
    ]
]*/
def conditionsOr = [
        or: [
                lessThan   : [id: 27],
                greaterThan: [id: 50]
        ]
]
result = conditionsAnd.conditions.collect { logic ->
    println("-----------------------------------------------------")
    println(logic.key)
    return logic.value.collect { sqlOperator ->
        println("\t" + sqlOperator.key)
        return sqlOperator.value.collect { condition ->
            println("\t\t" + condition.key)
            if (condition.value.class.getSimpleName() in ['String', 'GStringImp']) {
                return "${condition.key}${operators[sqlOperator.key ?: 'like']}'${condition.value}'"
            } else {
                return "${condition.key}${operators[sqlOperator.key]}${condition.value}"
            }
        }.join(' ' + logic.key + ' ')
    }.join(' ' + logic.key + ' ')
}.join(' or ')
println(query + result)