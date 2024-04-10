package org.bank.view;

import org.bank.domain.Customer;

import java.util.Scanner;

public class Input {

    public int initLevelMessages(Scanner scanner) {
        System.out.println("안녕하세요! 무엇을 도와드릴까요?");
        System.out.println("서비스 소개를 원하시면 1번을 입력해주세요.");
        System.out.println("계좌 개설을 원하시면 2번을 입력해주세요.");
        System.out.println("계좌 접속을 원하시면 3번을 입력해주세요.");
        System.out.print("입력 : ");
        return scanner.nextInt();
    }

    public String openAccount(Scanner scanner) {
        System.out.println("계좌를 개설하기 위해 이름을 입력해주세요.");
        System.out.print("입력 : ");
        return scanner.next();
    }

    public String accessAccount(Scanner scanner) {
        System.out.println("계좌에 접근하기 위해 이름을 입력해주세요.");
        System.out.print("입력 : ");
        return scanner.next();
    }

    public int inputAccountLevelMessage(Scanner scanner, Customer customer) {
        System.out.println("계좌에 접속했습니다.");
        System.out.println(customer.getName() + "님 반갑습니다!!");
        System.out.println("무엇을 도와드릴까요?");
        System.out.println("송금을 원하시면 1번을 입력해주세요.");
        System.out.print("입력 : ");
        return scanner.nextInt();
    }

    public int inputRemittanceMessage(Scanner scanner) {
        System.out.println("얼마를 송금하시겠습니까?");
        System.out.print("입력 : ");
        return scanner.nextInt();
    }

    public int inputLastLevelMessage(Scanner scanner) {
        System.out.println("서비스를 종료하시겠습니까?");
        System.out.println("서비스를 종료하고 싶으시면 -1을 입력해주세요.");
        System.out.println("서비스를 계속하고 싶으시면 0을 입력해주세요.");
        System.out.print("입력 : ");
        return scanner.nextInt();
    }
}
