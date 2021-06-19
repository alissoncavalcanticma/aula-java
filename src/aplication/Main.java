package aplication;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import entities.Funcionario;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		int qtd;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-- Quantos funcionários você deseja cadastrar? --");
		qtd = sc.nextInt() - 1;
		
		
		ArrayList<Funcionario> list = new ArrayList<>();
		
		for(int x = 0; x <= qtd; x++) {
				
			System.out.println("\nFuncionário #" + x);
			System.out.print("Id: ");
				int id = sc.nextInt();
			while(hasId(list, id)) {
				System.out.println("Usuário já está cadastrado! Digite outro.");
				id = sc.nextInt();
				
			}
			System.out.print("Nome: ");
				String nome = sc.next();
			System.out.print("Salário: ");
				double salario = sc.nextDouble();
			
			Funcionario func = new Funcionario(id, nome, salario);
			
			list.add(func);
			
		}
		
		System.out.println("\n\n Deseja alterar o salário de algum funcionário? S/N?");
		char response  = sc.next().charAt(0);
		if(response == 's') {
			
			System.out.println("\n\nQual o id do funcionário que você deseja alterar o salário? ");
			int idSal  = sc.nextInt();
			
			Funcionario fnc = list.stream().filter(x -> x.getId() == idSal).findFirst().orElse(null);
			
			if(fnc == null) {
				System.out.println("Funcionário não existe!!!");
			}else {
				System.out.print("Digite a porcentagem a alterar:");
				double percent = sc.nextDouble();
				fnc.ajustaSalario(percent);
			}
		}
		
		
		
		System.out.println("\n\nFuncionários cadastrados:");
		
		for(Funcionario a : list) {
			
			System.out.println("\nID: " + a.getId());
			System.out.println("Name: " + a.getName());
			System.out.println("Salário: " + a.getSalario());
		}
		
		sc.close();
	}
	
	public Integer position(ArrayList<Funcionario> list, int id) {
		for(int x = 0; x < list.size(); x++) {
			if(list.get(x).getId() == id) {
				return x;
			}
		}
		return null;
	}
	
	public static boolean hasId(ArrayList<Funcionario> list, int id) {
		Funcionario fc = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return fc != null;
	}
}
