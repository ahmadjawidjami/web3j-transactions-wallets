package com.example.controllers;

import com.demo1.HelloWorld;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

/**
 * Created by ahmadjawid on 5/7/17.
 */
@RestController
public class MainController {
    @RequestMapping("/")
    public String index() throws ExecutionException, InterruptedException, IOException, CipherException, TransactionTimeoutException {


        Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));  // defaults to http://localhost:8545/


        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();

       // String walletFile = "/Users/ahmadjawid/Library/Ethereum/testnet/keystore/UTC--2017-05-06T12-45-50.933546441Z--37fa3b5124987df3eb8dbacf27db8c2a7e65655d";
        String walletFile = "/Users/ahmadjawid/Library/Ethereum/testnet/keystore/UTC--2017-05-07T17-43-26.894790124Z--a0086de63efe926a3725bb4d3d27aff0d41a8e15";

        Credentials credentials = WalletUtils.loadCredentials("test", walletFile);


      //  HelloWorld helloWorld = HelloWorld.deploy(web3, credentials, GAS_PRICE, GAS_LIMIT, BigInteger.valueOf(0)).get();

        HelloWorld contract = HelloWorld.load(
                "0xA12ce20D06E6647702Eb5D5a7347d1f0268E9C70", web3, credentials, GAS_PRICE, GAS_LIMIT);
       // Parity parity = Parity.build(new HttpService()); // defaults to http://localhost:8545/


     //  TransactionReceipt transactionReceipt = Transfer.sendFunds(
         //       web3, credentials, "0x9e3d69305Da51f34eE29BfB52721e3A824d59e69", BigDecimal.valueOf(0.0001), Convert.Unit.ETHER);

//        Transaction transaction = Transaction.createEthCallTransaction("0xbF56534A556e61d67DEcc0c86797fe5dDedD9C22", "data");
//
//      RawTransaction rawTransaction =  RawTransaction.createTransaction(BigInteger.ONE, GAS_PRICE, GAS_LIMIT, "0xa0086de63efE926A3725BB4d3d27Aff0D41a8e15",
//BigInteger.valueOf(1), "0xffe444");
//        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials); String hexValue =
//                Numeric.toHexString(signedMessage);
//        EthSendTransaction ethSendTransaction = parity.ethSendRawTransaction(hexValue).send();
//        String transactionHash = ethSendTransaction.getTransactionHash();
//        System.out.println(transactionHash);


        System.out.println("Transaction finished");


        return "Greeting from Jawid " + credentials.getAddress() + " " + contract.message().get();
    }



}
