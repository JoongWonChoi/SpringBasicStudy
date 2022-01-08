package hello2.core.order;

public interface OrderService {

    //주문 서비스의 역할 ===> client에게 회원id, 상품이름, 상품 가격, 할인 가격을 return해줌.

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
