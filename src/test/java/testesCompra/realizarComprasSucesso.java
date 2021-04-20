package testesCompra;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;
import org.openqa.selenium.By;

public class realizarComprasSucesso extends AbrirNavegador {
	
	String email = "marciliojunio.borges@hotmail.com";
	String firstname = "marcilio";
	String lastname = "junio";
	String passwd = "12345";
	String address1 = "Qs 16 conjunto 01";
	String city = "Brasilia";
	String state = "Alabama";
	String postcode = "71825";
	String country = "United States";
	String mobile = "61984604627";
	String alias = "Qs 2 conjunto 01";
	
	public void criarConta() throws InterruptedException{
		//Criar conta de usuario
		//adicionar e-mail
		sendText("email_create", email);
		
		//clicar em Create
		clickByID("SubmitCreate");
		Thread.sleep(3000);
		
		if(isElementPresentVisibleEnabled(By.xpath("//*[@id='account-creation_form']/div[1]/h3"))){
			//Preencher dados
			clickByID("id_gender1");
			sendText("customer_firstname", firstname);
			sendText("customer_lastname", lastname);
			sendText("passwd", passwd);
			sendText("address1", address1);
			sendText("city", city);
			selectCombo("id_state", state);
			sendText("postcode", postcode);
			selectCombo("id_country", country);
			sendText("phone_mobile", mobile);
			sendText("alias", alias);
			
			//Clicar em Register
			clickByID("submitAccount");
		}else{
			sendText("email", email);
			sendText("passwd", passwd);
			clickByID("SubmitLogin");
		}
		


	}

	@Test
	public void realizarCompras() throws ParseException, org.json.simple.parser.ParseException, InterruptedException {

		// Clicar no tipo produto
		clickByXPath("//*[@id='block_top_menu']/ul/li[3]");

		// Clicar no produto
		clickByXPath("//*[@class='product-image-container']");
		Thread.sleep(3000);

		// Pegar valor da Peça e nome
		String produto = driver.findElement(By.xpath("//h1")).getText();
		System.out.println(produto);

		String valor = driver.findElement(By.xpath("//*[@id='our_price_display']")).getText().replace("$", "");
		System.out.println(valor);

		// Clicar para add no card
		clickByID("add_to_cart");
		Thread.sleep(3000);

		// continuar
		clickByXPath("//*[@title='Proceed to checkout']");

		// validar que produto foi adicionado
		assertEquals(produto, driver.findElement(By.xpath("//*[@class='cart_description']/p")).getText());
		assertEquals(valor, driver.findElement(By.xpath("//*[@id='total_product']")).getText().replace("$", ""));
		
		//Clicar em Proceed
		clickByXPath("//*[@id='center_column']/p[2]/a[1]");
		
		//clicar para realizar conta
		clickByID("SubmitCreate");
		
		//Criar Conta
		criarConta();
		
		//Validar dados de endereço
		assertEquals(address1, driver.findElement(By.xpath("//*[@id='address_delivery']/li[3]")).getText());
		
		//Continuar Compra
		clickByXPath("//*[@name='processAddress']");
		
		//Clicar em aceiar termos e pegar valor de envio
		clickByID("cgv");
		String envio = driver.findElement(By.xpath("//*[@class='resume table table-bordered']//td[4]")).getText().replace("$", "");
		System.out.println(envio);
		clickByXPath("//*[@name='processCarrier']");
		
		//Validar total da Compra
		int valor1 = Integer.parseInt(valor);
		int envio2 = Integer.parseInt(envio);
		int total = valor1 + envio2;
		assertEquals(total, driver.findElement(By.id("total_price")).getText());
		
		//Metodo de Pagamento
		clickByXPath("//*[@id='HOOK_PAYMENT']/div[2]//a");
		
		//Confirmar Compra
		clickByXPath("//*[@type='submit'][contains(.,'I confirm my order')]");
		
		//Validar texto esperado
		String textoElement = driver.findElement(By.xpath("//*[@class='alert alert-success']")).getText();
        assertEquals("Your order on My Store is complete.", textoElement);
	}

}
