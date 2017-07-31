package hodo.hodotalk.Chat;

/**
 * Created by mjk on 2017. 7. 28..
 */

public class Chat_Data {
    public String from;
    public String to;
    public String message;
    public Long time;
    String image;
    private String id;


    public Chat_Data(){

    }

    public Chat_Data(String from_person, String to_person, String message, long nowTime, String image_URL){
        this.from = from_person;
        this.to = to_person;
        this.message = message;
        this.time = nowTime;
        //this.image = image;

    }
    public Long gettime(){
        return time;
    }

    public Object getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmessage() {
        return message;
    }

    public String getfrom() {
        return from;
    }
}
