public class SwitchCase {
    public static void main(String args[]){
        char signal='g';
        String result;
        switch(signal){
            case'r':
                result="Stop traffic";
                break;
            case'g':
                result="Go traffic";
                break;
            case'y':
                result="Get ready traffic";
                break;
            default:
                result="Wrong signal";
        }

    }
}
