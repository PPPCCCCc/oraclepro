package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		PhoneDao phoneDao = new PhoneDao();
		Scanner sc = new Scanner(System.in);

		System.out.println("***************************************");
		System.out.println("*          전화번호 관리 프로그램            *");
		System.out.println("***************************************");

		while (true) {
			System.out.println("1.리스트 2.등록 3.수정 4.삭제 5.검색 6.종료");
			System.out.println("---------------------------------------");
			System.out.print(">메뉴번호 : ");
			int num = sc.nextInt();

			if (num == 1) {
				System.out.println("<1. 리스트>");
				List<PersonVo> personList = phoneDao.phoneSelect();

				for (int i = 0; i < personList.size(); i++) {

					int personId = personList.get(i).getPersonId();
					String name = personList.get(i).getName();
					String hp = personList.get(i).getHp();
					String company = personList.get(i).getCompany();

					System.out.println(personId + ". " + name + "  " + hp + "  " + company);
				}
			} else if (num == 2) {
				System.out.println("<2. 등록>");

				System.out.print("<이름 : ");
				String name = sc.next();

				System.out.print("<휴대전화 : ");
				String hp = sc.next();

				System.out.print("<회사전화 : ");
				String company = sc.next();

				phoneDao.personInsert(name, hp, company);
			} else if (num == 3) {
				System.out.println("<3. 수정>");

				System.out.print("<번호 : ");
				int phoneId = sc.nextInt();

				System.out.print("<이름 : ");
				String name = sc.next();

				System.out.print("<휴대전화 : ");
				String hp = sc.next();

				System.out.print("<회사전화 : ");
				String company = sc.next();

				phoneDao.personUpdate(name, hp, company, phoneId);
			} else if (num == 4) {
				System.out.println("<4. 삭제>");

				System.out.print("<번호 : ");
				int phoneId = sc.nextInt();

				phoneDao.personDelete(phoneId);
			} else if (num == 5) {
				System.out.println("<5. 검색>");

				System.out.println("검색어 : ");
				String sch = sc.next();

				List<PersonVo> personList = phoneDao.phoneSelect();

				for (int i = 0; i < personList.size(); i++) {

					int personId = personList.get(i).getPersonId();
					String name = personList.get(i).getName();
					String hp = personList.get(i).getHp();
					String company = personList.get(i).getCompany();
					PersonVo personVo = personList.get(i);
					
					if (personVo.getName().contains(sch) || personVo.getHp().contains(sch)
							|| personVo.getCompany().contains(sch)) {
						System.out.println(personId + ".\t" + name + "\t" + hp + "\t" + company);
					}
				}

			} else if (num == 6) {
				System.out.println("***************************************");
				System.out.println("*              감사합니다                *");
				System.out.println("***************************************");
				break;
			}else {
				System.out.println("[다시 입력해주세요.]");
			}
		}
		sc.close();
	}
}