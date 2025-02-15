package Notes;

import java.util.Arrays;

public class Note<T extends Notes> {
    private T[] size= (T[]) new Notes[4];
    Notes note2000;
    Notes note500;
    Notes note200;
    Notes note100;

    int index =0;

    public Note() {
        note2000 = new Notes(2000,0);
        note200=new Notes(200,0);
        note500=new Notes(500,0);
        note100=new Notes(100,0);

    }

    public void addNotes(T note) {
        if (size.length < 4) {
            this.size[index] = note;
            index++;
        }
    }

    public void setNotes(T[] size) {
        this.size = size;
    }

    public <T> T getNotes(T note) {
        T size1 = note;
        return size1;
    }
//    public T oneNote(T oneNote){
//        return oneNote;
//    }
    public T[] getALLNotes(){
        return size;
    }
}
