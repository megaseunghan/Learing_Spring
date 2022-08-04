package hello.core.order;import hello.core.discount.DiscountPolicy;import hello.core.member.Member;import hello.core.member.MemberRepository;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Component;@Component//@RequiredArgsConstructorpublic class OrderServiceImpl implements OrderService {    private final MemberRepository memberRepository;    private final DiscountPolicy discountPolicy;//    final 키워드가 붙은 필드를 생성자로 만들 때 롬복의 @RequiredArgsConstructor가 생성해준다.    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {        this.memberRepository = memberRepository;        this.discountPolicy = discountPolicy;    }    @Override    public Order createOrder(Long memberId, String itemName, int itemPrice) {        Member member = memberRepository.findMyId(memberId);        int discountPrice = discountPolicy.discount(member, itemPrice);        return new Order(memberId, itemName, itemPrice, discountPrice);    }    // 테스트 용도    public MemberRepository getMemberRepository() {        return memberRepository;    }}