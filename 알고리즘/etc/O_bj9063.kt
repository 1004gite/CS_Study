fun main() {

    var maxX = -100000
    var minX = 100000
    var maxY = -100000
    var minY = 100000

    System.`in`.bufferedReader().use { br ->
        repeat(br.readLine().toInt()) {
            br.readLine().split(' ').map { it.toInt() }
                .let { (a,b) ->
                    maxX = maxOf(maxX, a)
                    minX = minOf(minX, a)
                    maxY = maxOf(maxY, b)
                    minY = minOf(minY, b)
                }
        }
    }

    print((maxX-minX)*(maxY-minY))

}