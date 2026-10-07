fun main(){
    val chambres = arrayOf(101, 102, 103, 201, 202)

    val typesChambres = mapOf(
        101 to "Simple",
        102 to "Double",
        103 to "Suite",
        201 to "Simple",
        202 to "Double"
    )

    val reservations = mutableMapOf(
        101 to "Ali",
        202 to "Adam"
    )

    val clients = arrayOf("Ali", "Adam", "Sara", "Hajar")
    val prixTypes = mapOf("Simple" to 300.0, "Double" to 450.0, "Suite" to 900.0)


    //1
    val disponaible = chambres.filter { it !in reservations.keys }
    println(disponaible)

    //2
    for (chambres in chambres) {
        println("$chambres : types ${typesChambres[chambres]}")
    }
    //3
    for (reservation in reservations) {
        println("$reservation")
    }
    //4
    for (chambre in chambres) {
        if (chambre !in reservations) {
            println("La chambre n'est pas réservée : $chambre")
        }
    }
    //5
    fun reserverChambre(numChambres: Int,client: String) {
        if (numChambres !in reservations){
            reservations[numChambres] = client
            print("le chambre $numChambres est reservee par $client\n")
        }
        else{
            println("le chambre est deja reservee")
        }


    }
    reserverChambre(103,"Sara")
    reserverChambre(101,"Adam")


    //6
    fun annuleReservation(numChambres: Int) {
        if (numChambres in reservations){
            reservations.remove(numChambres)
            println("le chambre $numChambres est deja reservee")
        }
        else{
            println("cette chambre n est pas reservee")
        }
    }
    annuleReservation(103)

    //7
    fun trouverReservation(numChambre: Int) {
        if (numChambre in reservations) {
            println("Réservation trouvée : ${reservations[numChambre]}")
        } else {
            println("Aucune réservation pour cette chambre")
        }
    }
    trouverReservation(101)

    //8
    fun Clientsansreservation() {
        for (client in clients){
            if(client !in reservations.values){
                println("le client sans reservation est $client")

            }
        }

    }
    Clientsansreservation()
    //9
    val c = typesChambres.values
    val attendue = c.groupingBy { it }.eachCount()
    println("Attendue: $attendue")

    //10
    val parEtage = chambres.groupBy { it / 100 }

    for ((etage, chambresEtage) in parEtage) {
        println("Étage $etage : $chambresEtage (${chambresEtage.size} chambres)")
    }
    //11
    val taux = (reservations.size.toDouble() / chambres.size) * 100

    println(String.format("%.2f %%", taux))

    //12
    fun revenuJournalier(): Pair<Double, Double> {

        var revenuTotal = 0.0

        for ((chambre, client) in reservations) {

            val type = typesChambres[chambre]!!

            val prix = prixTypes[type]!!

            revenuTotal += prix
        }

        val nombreReservations = reservations.size

        val revenuMoyen = revenuTotal / nombreReservations

        return Pair(revenuTotal, revenuMoyen)
    }

    val resultat = revenuJournalier()

    println(resultat.first)
    println(resultat.second)

    //13
    fun etatHotel(): Triple<Int, Int, Int> {
        val total = chambres.size

        val reservees = reservations.size

        val disponibles = total - reservees

        return Triple(total, reservees, disponibles)



    }
    val r = etatHotel()

    println(r)

    //14
    val liste = reservations.map { (chambre, client) ->

        val type = typesChambres[chambre]!!
        val prix = prixTypes[type]!!

        Triple(client, chambre, prix)
    }

    for ((client, chambre, prix) in liste) {
        println("$client -> chambre $chambre : ${"%.2f".format(prix)} DH")
    }
    //14
    val revenus = chambres
        .groupBy { typesChambres[it]!! }
        .map { (type, chambresType) ->
            Pair(type, chambresType.size * prixTypes[type]!!)
        }
        .sortedByDescending { it.second }

    println(revenus)
    //16
    fun <T> maxHotel(liste: List<T>): T? {

        if (liste.isEmpty()) return null

        return when (liste[0]) {

            is Int -> liste.maxOf { it as Int } as T

            is String -> liste.maxByOrNull { (it as String).length }

            else -> null
        }
    }
    println(maxHotel(chambres.toList()))
    println(maxHotel(clients.toList()))











    }
