package Notes;

import ListOfNotes.*;


public class Note<T extends Notes> {
    private T[] size= (T[]) new Notes[4];
    int index =0;

    public Note() {
        // Initialize array with default values to avoid null pointer exception
        size[0] = (T) new TwoThousand(2000, 0);
        size[1] = (T) new FiveHundred(500, 0);
        size[2] = (T) new TwoHundred(200, 0);
        size[3] = (T) new Hundred(100, 0);
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

    public T[] getALLNotes(){
        return size;
    }
}
