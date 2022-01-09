package hello2.core.singleton;

public class SingletonService {
    //java 실행 시 static에 의하여 class 영역에 하나가 생성
    private static final SingletonService instance = new SingletonService();

    //오직 이 메서드를 통해서만 객체 조회 가능 . . 항상 같은 instance 반환!
    public static SingletonService getInstance() {
        return instance;
    }
    //생성자를 private으로 막아 새로운 객체 생성을 제한!v
    private SingletonService() {

    }
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }



}
