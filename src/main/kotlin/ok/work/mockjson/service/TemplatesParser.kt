package ok.work.mockjson.service


fun matchesTemplateUrl(template: String, url: String): Boolean {
    val templateSplit = template.split("/")
    val urlSplit = url.split("/")


    for (i in (urlSplit.indices)) {
        if (templateSplit[i].startsWith("{{") && templateSplit[i].endsWith("}}"))
            continue
        if (templateSplit[i] != urlSplit[i])
            return false
    }

    return true
}

fun getValuesFromUrl(template: String, url: String): Map<String, String> {
    val templateSplit = template.split("/")
    val urlSplit = url.split("/")
    val values = HashMap<String, String>()

    for (i in (urlSplit.indices)) {
        if (templateSplit[i].startsWith("{{") && templateSplit[i].endsWith("}}")) {
            values[templateSplit[i]] = urlSplit[i]
        }
    }
    return values
}

fun putValuesInPayload(payload: String, values: Map<String, String>): String {
    var result = payload
    values.forEach { (k, v) ->
        result = payload.replace(k, v)
    }
    return result
}

fun getWildcardsFromJsonTemplate(template: String): List<String> {
    return template.split(" ").filter { s -> s.startsWith("{{") && s.endsWith("}}") }.toList()
}

