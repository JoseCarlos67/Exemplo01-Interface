package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    Scanner scanner = new Scanner(System.in);

    System.out.println("Entre com os dados do aluguel.");
    System.out.print("Modelo do carro: ");
    String carModel = scanner.nextLine();

    System.out.print("Retirada (dd/mm/yyyy hh:mm): ");
    LocalDateTime start = LocalDateTime.parse(scanner.nextLine(), fmt);

    System.out.print("Retorno (dd/mm/yyyy hh:mm): ");
    LocalDateTime finish = LocalDateTime.parse(scanner.nextLine(), fmt);

    CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));

    System.out.print("Entre com o preço por hora: ");
    double pricePerHour = scanner.nextDouble();

    System.out.print("Entre com o preço por dia: ");
    double pricePerDay = scanner.nextDouble();


    BrazilTaxService brazilTaxService = new BrazilTaxService();
    RentalService rentalService = new RentalService(pricePerHour, pricePerDay, brazilTaxService);

    rentalService.processInvoice(carRental);

    System.out.println("FATURA:");
    System.out.println("Pagamento básico: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
    System.out.println("Imposto: " + String.format("%.2f", carRental.getInvoice().getTax()));
    System.out.println("Pagamento total: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));
  }
}
