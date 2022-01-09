package hello2.core.singleton;

public class StatefulService {
    private int price; // 상태를 유지하는 필드 (공유 field)

    public void order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        this.price = price; // 넘어온 값을 객체에 보관하게 되기 때문에 문제가 발생 가능!
    }

    public int getPrice() {
        return price;
    }
}
