package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드 (필드에 저장하지말고 메소드에서 리턴)

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
