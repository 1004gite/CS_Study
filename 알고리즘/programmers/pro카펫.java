class Solution {
    public int[] solution(int brown, int yellow) {
        int half = brown/2;
        for(int w = 3; w <= brown-2; w++){
            int h = half-w+2;
            if(yellow == (w-2)*(h-2)) return new int[] {h,w};
        }

        return new int[]{};
    }
}