package org.bank.view;

public class Output {

    public void introduceService() {
        System.out.println("안녕하세요 궁찬 은행입니다!");
        System.out.println("저희 은행은 송금만 가능합니다!");
        System.out.println("다른 기능이요?");
        System.out.println("그렇게 됐습니다^^");
    }

    public void successOpenAccount() {
        System.out.println("성공적으로 계좌를 개설했습니다!");
        System.out.println("이벤트로 5000원으로 지급하였습니다!");
    }

    public void successAccessAccount() {
        System.out.println("성공적으로 계좌에 접근하였습니다!");
    }

    public void failAccessAccount() {
        System.out.println("계좌 접근에 실패했습니다.");
        System.out.println("서비스에 다시 접속해주세요.");
    }

    public void successRemit() {
        System.out.println("송금을 성공했습니다!");
    }
}
