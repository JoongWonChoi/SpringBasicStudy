package hello2.core.singleton;

public class StatelessService {
    //stateful 하지 않게 공유 변수 없애고, 로컬 변수로 return
    public int price(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        return price;
    }
}
