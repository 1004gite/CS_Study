package etc

fun main() {

//    solve1()
    solve2()
}

fun solve2() { // 절반의 모양 기록 후 이용하기 - 132ms
    val n = readln().toInt()
    val maxStar = 1+2*(n-1)
    // 왼쪽 공간의 크기
    val leftSpace = maxStar/2
    val starList = mutableListOf<String>()


    fun getEmptySpaceCount(star: Int): Int {
        // star개의 별을 그릴 때 먼저 찍어야 하는 공백의 개수 반환
        return leftSpace - star/2
    }
    fun recordStar(star: Int) {
        val space = getEmptySpaceCount(star)
        val sb = StringBuilder()
        repeat(space) {
            sb.append(' ')
        }
        repeat(star) {
            sb.append('*')
        }
        sb.append('\n')
        starList.add(sb.toString())
    }

    // 1~n번째줄까지의 별모양을 기록함
    for(line in 0 until n) {
        // 이번 라인에 그려야 하는 별의 수
        val star = 1 + 2*line
        recordStar(star)
    }

    val sb = StringBuilder()
    // 저장된 별모양을 1 ~ n순서대로 그림
    for(i in 0 until n) {
        sb.append(starList[i]).append('\n')
    }
    // 저장된 별모양을 n-1 ~ 0 순서로 그림
    for(i in n-2 downTo 0) {
        sb.append(starList[i]).append('\n')
    }

    print(sb.toString())
}

fun solve1() { // brute force - 140ms
    val n = readln().toInt()
    val maxStar = 1+2*(n-1)
    // 왼쪽 공간의 크기
    val leftSpace = maxStar/2
    val sb = StringBuilder()


    fun getEmptySpaceCount(star: Int): Int {
        // star개의 별을 그릴 때 먼저 찍어야 하는 공백의 개수 반환
        return leftSpace - star/2
    }
    fun drawStar(star: Int) {
        val space = getEmptySpaceCount(star)
        repeat(space) {
            sb.append(' ')
        }
        repeat(star) {
            sb.append('*')
        }
        sb.append('\n')
    }

    // 별이 증가하는 구간
    for(line in 0 until n-1) {
        // 이번 라인에 그려야 하는 별의 수
        val star = 1 + 2*line
        drawStar(star)
    }
    // n번째줄
    drawStar(maxStar)
    // 별이 감소하는 구간
    for(line in n-2 downTo 0) {
        // 이번 라인에 그려야 하는 별의 수
        val star = 1 + 2*line
        drawStar(star)
    }

    // 마지막 개행문자 제거
    sb.dropLast(1)
    print(sb.toString())
}
