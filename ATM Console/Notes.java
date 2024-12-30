public class Notes {
    private int note;
    private int count;

    public Notes(int note,int count){
        this.note= note;
        this.count=count;
    }

    public Notes() {
        return;
    }

    public  int getNote(){
        return note;
    }

    public int setNote(int note){
        this.note=note;
        return note;
    }

    public  int getCount(){
        return count;
    }

    public int setCount(int count){
        this.count=count;
        return count;
    }
}
