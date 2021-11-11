package app.domain.model;

import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientsStoreTest {
    @Test(expected = IllegalArgumentException.class)
    public void createClientNullv1() throws ParseException {
        ClientsStore store = new ClientsStore();
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        store.createClient(null, null, date, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientNullv2() throws ParseException {
        ClientsStore store = new ClientsStore();
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        store.createClient("1111111111111111", null, date, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClientNullv3() throws ParseException {
        ClientsStore test = new ClientsStore();
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        test.createClient("1234511111111111", "11111111111", date, null, null, null, null);
    }

    @Test
    public void CheckCreateClientStore() {
        ClientsStore clientList;

        clientList = new ClientsStore();
    }

    @Test
    public void CheckSaveClient() throws ParseException {
        ClientsStore clientList;
        clientList = new ClientsStore();
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        Client client = new Client("1234511111111111", "1111111111", date, "1111111111", "aaaa", "11111111111", "male");

        clientList.saveClient(client);
    }

    @Test
    public void checkCreateClient() throws ParseException {
        ClientsStore clientList;
        clientList = new ClientsStore();
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        clientList.createClient("1111111111112345", "1111111111", date, "1111111111", "aaaaa", "11111111111", "male");
    }

    @Test
    public void checkValidateClient() throws ParseException {
        ClientsStore clientList;
        clientList = new ClientsStore();

        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        Client client = clientList.createClient("1111111111112345", "1111111111", date, "1111111111", "aaaaa", "11111111111", "male");


        clientList.validateClient(client);
    }

    @Test
    public void checkInvalidSaveClient() throws ParseException {
        ClientsStore clientList;
        clientList = new ClientsStore();

        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);
        Client client = new Client("1234511111111111", "1111111111", date, "1111111111", "aaaa", "11111111111", "male");

        clientList.saveClient(client);

        Client client1 = new Client("1234511111111111", "1111111111", date, "1111111111", "aaaa", "11111111111", "male");

        clientList.saveClient(client1);
    }

    @Test
    public void saveClientTest1() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        Client client = new Client("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");
        ClientsStore store = new ClientsStore();
        Client client1 = store.createClient("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");

        Assert.assertTrue(store.saveClient(client1));
    }

    @Test
    public void saveClientTest2() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");

        store.saveClient(client);
        Client clientTest = store.createClient("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");

        Assert.assertFalse(store.saveClient(clientTest));
    }

    @Test
    public void saveClientTest3() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");

        store.saveClient(client);


        Assert.assertNotEquals(true, store.validateClient(client));
    }

    @Test
    public void validateClinicalTest1() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        Client client = new Client("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");
        ClientsStore store = new ClientsStore();

        Assert.assertTrue(store.validateClient(client));
    }

    @Test
    public void validateClientTest2() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");

        store.saveClient(client);

        Assert.assertFalse(store.validateClient(client));
    }

    @Test
    public void validateClientTest3() {
        ClientsStore store = new ClientsStore();
        Assert.assertNotEquals(true, store.validateClient(null));
    }

    @Test
    public void validateClientTest4() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111112345", "1111111111", date, "2222222222", "aaaaa", "11111111111", "male");


        Assert.assertNotNull(client);
    }

    @Test
    public void validateClientTest5() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        store.saveClient(client);
        Client client1 = store.createClient("1111111111111111", "2222222222", date, "2222222222", "cliente", "22222222222", "male");

        Assert.assertNotEquals(true, store.validateClient(client1));
    }

    @Test
    public void validateClientTest6() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        store.saveClient(client);
        Client client1 = store.createClient("1111111111111115", "1111111111", date, "2222222222", "cliente", "22222222222", "male");

        Assert.assertNotEquals(true, store.validateClient(client1));
    }

    @Test
    public void validateClientTest7() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "22222222222", "male");
        store.saveClient(client);
        Client client1 = store.createClient("1111111111111115", "2222222225", date, "2222222222", "cliente", "22222222222", "male");

        Assert.assertNotEquals(true, store.validateClient(client1));
    }

    @Test
    public void validateClientTest8() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        store.saveClient(client);
        Client client1 = store.createClient("1111111111111115", "2222222222", date, "1111111111", "cliente", "22222222222", "male");

        Assert.assertNotEquals(true, store.validateClient(client1));
    }

    @Test
    public void getClientFromClientEmail() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        client.setEmail("yesyes@gmail.com");
        store.saveClient(client);

        Assert.assertEquals(client, store.getClientFromClientEmail("yesyes@gmail.com"));
    }

    @Test
    public void getNonClientFromClientEmail() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        client.setEmail("yesyes@gmail.com");
        store.saveClient(client);

        Assert.assertEquals(null, store.getClientFromClientEmail("yeses@gmail.com"));
    }

    @Test
    public void getClientList() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        client.setEmail("yesyes@gmail.com");
        store.saveClient(client);

        store.getClientList();

    }

    @Test
    public void verifyExistingClient() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        client.setEmail("yesyes@gmail.com");
        store.saveClient(client);

        Assert.assertEquals(true, store.verifyExistingClient("1111111111"));

    }

    @Test
    public void verifyNonExistingClient() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        client.setEmail("yesyes@gmail.com");
        store.saveClient(client);

        Assert.assertEquals(false, store.verifyExistingClient("1113211111"));

    }

    @Test
    public void getClientOrderTin() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        client.setEmail("yesyes@gmail.com");
        store.saveClient(client);
        store.getClientOrderTin();
    }

    @Test
    public void getClientOrderName() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientsStore store = new ClientsStore();
        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        client.setEmail("yesyes@gmail.com");
        store.saveClient(client);
        store.getClientOrderName();
    }

    @Test
    public void checkValidatePhoneNumber() {
        ClientsStore store = new ClientsStore();

        Client client = null;
        Assert.assertEquals(false,store.validatePhoneNumber(client));
    }

    @Test
    public void checkValidatePhoneNumber2() throws ParseException {
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);
        ClientsStore store = new ClientsStore();

        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        store.saveClient(client);

        Client client1 = store.createClient("1111111111111112", "1111111112", date, "1111111112", "client", "11111111112", "male");

        Assert.assertEquals(true,store.validatePhoneNumber(client1));
    }

    @Test
    public void checkGetClientOrderTin() throws ParseException{
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);
        ClientsStore store = new ClientsStore();
        List<Client> orderList = new ArrayList<>();

        Client client2 = store.createClient("2222222222222222", "2222222222", date, "2222222222", "dliente", "22222222222", "male");
        store.saveClient(client2);

        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        store.saveClient(client);

        Client client3 = store.createClient("3333333333333333", "3333333333", date, "3333333333", "eliente", "33333333333", "male");
        store.saveClient(client3);

        orderList.add(client);
        orderList.add(client2);
        orderList.add(client3);

        Assert.assertEquals(orderList,store.getClientOrderTin());
    }

    @Test
    public void checkGetClientOrderName() throws ParseException{
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);
        ClientsStore store = new ClientsStore();
        List<Client> orderList = new ArrayList<>();

        Client client2 = store.createClient("2222222222222222", "2222222222", date, "2222222222", "dliente", "22222222222", "male");
        store.saveClient(client2);

        Client client = store.createClient("1111111111111111", "1111111111", date, "1111111111", "cliente", "11111111111", "male");
        store.saveClient(client);

        Client client3 = store.createClient("3333333333333333", "3333333333", date, "3333333333", "eliente", "33333333333", "male");
        store.saveClient(client3);

        orderList.add(client);
        orderList.add(client2);
        orderList.add(client3);

        Assert.assertEquals(orderList,store.getClientOrderName());
    }
}