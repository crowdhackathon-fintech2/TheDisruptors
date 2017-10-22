package com.ngbfintech2.api.controllers;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.ngbfintech2.api.dao.Order;
import com.ngbfintech2.api.dto.OrderService;
import com.ngbfintech2.api.dto.ResponseProductDetails;
import com.simplify.payments.PaymentsMap;
import com.simplify.payments.domain.CardToken;
import com.simplify.payments.domain.Payment;
import okhttp3.*;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class ProductControllerImpl implements ProductController{

    private static final Logger logger = LoggerFactory.getLogger(ProductControllerImpl.class);


    @Override
    public ResponseProductDetails getProductDetails(String id) {
        return new ResponseProductDetails("1","TV","TV description","http://www.samsung.com/hk_en/tv/KS9800/","10","2");
    }

    @Override
    public Boolean payProductNBG(String id) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"to\":{\"iban\":\"norak\"},\"charge_policy\":\"OUR\",\"value\":{\"currency\":\"EUR\",\"amount\":67},\"description\":\"Kef torisib denewera ez gugew hakobupa ubwuggew dohoho pituj rupvaj jeegfe ho nawjadfa dufjowza.\"}");
        Request request = new Request.Builder()
                .url("https://apis.nbg.gr/public/nbgapis/obp/v3.0.1/banks/DB173089-A8FE-43F1-8947-F1B2A8699829/accounts/470105d5-a5ec-4d31-bd36-eb2c996c0f06/owner/transaction-request-types/sepa/transaction-requests")
                .post(body)
                .addHeader("content-type", "text/json")
                .addHeader("accept", "text/json")
                .addHeader("sandbox_id", "sand1")
                .addHeader("user_id", "9d7f2ef4-7262-4429-a487-7979e4a76447")
                .addHeader("username", "User1")
                .addHeader("provider_id", "NBG.gr")
                .addHeader("provider", "NBG")
                .build();

        try {
            Response response = client.newCall(request).execute();
            logger.info("Transaction Completed with NBG");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Autowired
    OrderService orderService;

    static NetworkParameters params;
    static WalletAppKit kit;


    @PostConstruct
    public void init() throws IOException {
        params = TestNet3Params.get();
        kit = new WalletAppKit(params, new File("."), "sendrequest-example");
        if(!kit.isRunning() && !kit.isChainFileLocked()){
            kit.startAsync();
            kit.awaitRunning();
            kit.setAutoStop(true);
        }
    }

    @Override
    public Boolean payMC(@RequestParam("id") String id){
        Payment payment = null;
        try {
            Order order = orderService.getOrder("1", "1");
            CardToken cardToken = CardToken.create(new PaymentsMap()
                    .set("card.addressCity", order.getCardAddressCity())
                    .set("card.addressState", order.getCardAddressState())
                    .set("card.cvc", order.getCardCVC())
                    .set("card.expMonth", order.getCardExpMonth())
                    .set("card.expYear", order.getCardExpYear())
                    .set("card.number", order.getCardNumber())
            );
            payment = Payment.create(new PaymentsMap()
                    .set("currency", order.getCurrency())
                    .set("token", cardToken.get("id")) // returned by JavaScript call
                    .set("amount", order.getAmount()) // In cents e.g. $10.00
                    .set("description", order.getDescription()))
            ;
            if ("APPROVED".equals(payment.get("paymentStatus"))) {
                //todo store payment info in DB
                logger.info("Transaction Completed with MC");
                return true;
            }
            else {
                //todo store payment info in DB
                return false;
            }
        } catch (Exception e) {
            //todo store payment info in DB
            return false;
        }
    }

    @Override
    public Boolean payBC(@RequestParam("id") String id){

        // We use the WalletAppKit that handles all the boilerplate for us. Have a look at the Kit.java example for more details.


        System.out.println("Send money to: " + kit.wallet().currentReceiveAddress().toString());

        // How much coins do we want to send?
        // The Coin class represents a monetary Bitcoin value.
        // We use the parseCoin function to simply get a Coin instance from a simple String.
        Coin value = Coin.parseCoin("0.00009");

        // To which address you want to send the coins?
        // The Address class represents a Bitcoin address.
        org.bitcoinj.core.Address to = org.bitcoinj.core.Address.fromBase58(params, "mupBAFeT63hXfeeT4rnAUcpKHDkz1n4fdw");

        // There are different ways to create and publish a SendRequest. This is probably the easiest one.
        // Have a look at the code of the SendRequest class to see what's happening and what other options you have: https://bitcoinj.github.io/javadoc/0.11/com/google/bitcoin/core/Wallet.SendRequest.html
        //
        // Please note that this might raise a InsufficientMoneyException if your wallet has not enough coins to spend.
        // When using the testnet you can use a faucet (like the http://faucet.xeno-genesis.com/) to get testnet coins.
        // In this example we catch the InsufficientMoneyException and register a BalanceFuture callback that runs once the wallet has enough balance.
        try {
            Wallet.SendResult result = kit.wallet().sendCoins(kit.peerGroup(), to, value);
            System.out.println("coins sent. transaction hash: " + result.tx.getHashAsString());
            logger.info("Transaction Completed with Bitcoin");
            return true;
            // you can use a block explorer like https://www.biteasy.com/ to inspect the transaction with the printed transaction hash.
        } catch (InsufficientMoneyException e) {
            System.out.println("Not enough coins in your wallet. Missing " + e.missing.getValue() + " satoshis are missing (including fees)");
            System.out.println("Send money to: " + kit.wallet().currentReceiveAddress().toString());

            // Bitcoinj allows you to define a BalanceFuture to execute a callback once your wallet has a certain balance.
            // Here we wait until the we have enough balance and display a notice.
            // Bitcoinj is using the ListenableFutures of the Guava library. Have a look here for more information: https://github.com/google/guava/wiki/ListenableFutureExplained
            ListenableFuture<Coin> balanceFuture = kit.wallet().getBalanceFuture(value, Wallet.BalanceType.AVAILABLE);
            FutureCallback<Coin> callback = new FutureCallback<Coin>() {
                @Override
                public void onSuccess(Coin balance) {
                    System.out.println("coins arrived and the wallet now has enough balance");
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("something went wrong");
                }
            };
            Futures.addCallback(balanceFuture, callback);
        }
        return false;

    }

}
