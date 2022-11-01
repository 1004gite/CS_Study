
class Solution {

    /**
     * 각 page에 대해 {기본 점수, 자신에게 링크된 페이지 목록}을 기록한다.
     * content=으로 시작하는 곳이 본인의 url이다.
     * herf= 으로 시작하는 곳이 본인이 연결하는 page이다.
     * <body 다음에서 word를 찾아야 한다.
     *
     * 풀이 2
     * 1. <,>으로 string을 split한다.
     * 2. 가장 앞이 {html, metam head, body, a} + " " 이면 태그가 시작되는 곳이다.
     * 3. 태그가 나온 다음 /으로 끝나는 곳은 태그가 바로 닫힌 곳으로 단어를 검사할 필요가 없다.
     * */

    data class Page(var url: String, var baseScore: Int, var linkScore: Double, var links: MutableList<String>)

    fun solution(word: String, pages: Array<String>): Int {

        val urlToIndex = hashMapOf<String, Int>()
        val myPages = mutableListOf<Page>()
        for(page in pages){
            val tmpPage = divide(page,word)
            println(tmpPage.url)
            if(tmpPage.url == "") continue
            urlToIndex[tmpPage.url] = myPages.size
            myPages.add(tmpPage)
        }

        // 링크점수 계산
        for(page in myPages){
            if(page.baseScore == 0 || page.links.size == 0) continue
            val tmpScore = page.baseScore/page.links.size.toDouble()
            page.links.forEach{
                urlToIndex[it]?.let { index -> myPages[index].linkScore += tmpScore }
            }
        }

        var answer = 0
        for(i in myPages.indices){
            if(myPages[0].baseScore.toDouble()+myPages[0].linkScore < myPages[i].baseScore.toDouble()+myPages[i].linkScore) answer=i
        }
        return answer
    }

    fun divide(page: String, word: String): Page{
        var nowPage = Page("",0,0.0, mutableListOf())
        // url 파싱.
        val regexUrl = "<meta property=\"og:url\" content=\"https://\\S*\"/>".toRegex()
        regexUrl.find(page)?.let{ nowPage.url = it.groupValues[0].split("content=\"")[1].split("\"")[0] }

        // 링크 파싱
        val regexLink = "<a href=\"https://\\S*\"".toRegex()
        regexLink.findAll(page).forEach {
            nowPage.links.add( it.value.split("href=\"")[1].split("\"")[0] )
        }

        // 단어 파싱
//        val regexWord = "[^a-zA-Z]*$word[^a-zA-Z]*".toRegex(RegexOption.IGNORE_CASE)
//        regexWord.findAll(page).forEach { nowPage.baseScore++ }
        var post = -500
        var index = page.indexOf(word,0,true)
        var pre = page.indexOf(word,index+word.length,true)
        while(index != -1){
            // println("url: ${nowPage.url} - $word")
            // 다음 단어가 바로 붙어있는 경우 counting 하지 않는다.
            // 때문에 앞뒤를 검사한다.
            if(post+word.length != index && index+word.length != pre) nowPage.baseScore++;
            post = index
            index = pre
            pre = page.indexOf(word,index+word.length,true)
        }

        return nowPage
    }

}