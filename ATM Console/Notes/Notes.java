package Notes;

public abstract class Notes{
    private int note;//field to store the note
    private int count;//field to store the count of a note

    // Constructor to initialize the Notes.Notes object with note and count
    protected Notes(int note,int count){
        this.note= note;// reassigns the note field
        this.count=count;// reassigns the count field
    }

    //getNote method to get the note
    public  int getNote(){
        return note;//return the note value
    }

    //getCount method to get the count of a each note
    public  int getCount(){
        return count;//return the count of a note
    }

    //setCount method to set the count value for each note
    public int setCount(int count){
        this.count=count;//reassign the count value for each note
        return count;//return the value of a count
    }

    @Override
    public String toString() {
        return "Rs." + note + " - " + count;
    }
}
