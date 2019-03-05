package com.iot.iotblockchain.activities;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iot.iotblockchain.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import java8.util.concurrent.CompletableFuture;


public class MainActivity extends AppCompatActivity {
    Button buttonGet;
    Button buttonPush;
    RequestQueue queue;
    TextView mTextView;

    //privatekey of My account
    private final static String PRIVATE_KEY = "0A6407678325C88C4358708E60AABC072858D10FF0C068FBF9156A5D672FEF88";
    //address of deployed contract
    private final static String contractAddress = "0x71ed2e300219c0032961ed97e1bf9f3e683addaf";

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    //recipient address of ethereum transfer
    private final static String RECIPIENT = "0xFb74fB933f506F9616fCC8709C8B845F46305328";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //solves the problem of multidexing
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //request queue for http services
        queue = Volley.newRequestQueue(this);
        mTextView = (TextView) findViewById(R.id.text); //dummy texView for showing output
        buttonGet = findViewById(R.id.button);//button for getting data from swarm
        buttonPush = findViewById(R.id.button2);//button for pushing data to the swarm
        //http service for ethereum light client
        HttpService service = new HttpService("https://ropsten.infura.io/v3/4e60e70671cc4c3ebbae1b60d2ed8117");
        Web3j web3j = Web3j.build(service);


        //transaction manager is depreceated at the new versions Credential is used
        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                getCredentialFromPrivateKey()
        );
        try {
            //deploys a contract then return its address.
            //  String deployedAddress = deployContract(web3j,getCredentialFromPrivateKey());

            //retreives the contract
            Inbox inbox = loadContract(contractAddress, web3j, getCredentialFromPrivateKey());
            inbox.getMessage().send();
            mTextView.setText(inbox.getContractAddress());


        } catch (Exception e) {
            e.printStackTrace();
            mTextView.setText(e.getMessage());
        }


        //JSONArray oluşturma
        final JSONArray newJsonArray = new JSONArray();
        final JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        try {
            jsonObject1.put("name", "zeki");
            jsonObject2.put("name", "mehmet");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        newJsonArray.put(jsonObject1);
        newJsonArray.put(jsonObject2);


        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getDataUrl = "http://swarm-gateways.net/bzz:/3f46f7ec7abc6be0befc0355a5b732dce86ff315d58afc90712e781e5d9e99d1";
                JsonArrayRequest getData = new JsonArrayRequest(Request.Method.GET, getDataUrl, null,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                mTextView.setText(response.toString());

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTextView.setText("Error");
                            }
                        }
                );
                queue.add(getData);
            }
        });


        buttonPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pushUrl = "http://swarm-gateways.net/bzz:/";
                StringRequest pushData = new StringRequest(Request.Method.POST, pushUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                mTextView.setText(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                mTextView.setText(error.getMessage());
                            }
                        }
                ) {

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        return newJsonArray.toString().getBytes();
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                queue.add(pushData);
            }
        });

    }


    private Credentials getCredential() throws IOException, CipherException {
        return WalletUtils.loadCredentials("passphrase", "wallet/path");
    }

    //retrieves your credential
    //your account in the ethereum network
    private Credentials getCredentialFromPrivateKey() {
        return Credentials.create("0A6407678325C88C4358708E60AABC072858D10FF0C068FBF9156A5D672FEF88");
    }

    private void printWeb3jVersion() {
        HttpService service = new HttpService("https://ropsten.infura.io/v3/4e60e70671cc4c3ebbae1b60d2ed8117");
        Web3j web3 = Web3j.build(service);
        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            mTextView.setText(clientVersion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //transfermanager for ethereum
    private void transferEthereum(Web3j web3j, TransactionManager transactionManager) {
        Transfer transfer = new Transfer(web3j, transactionManager);
        CompletableFuture<TransactionReceipt> transactionReceipt = null;
        try {
            transactionReceipt = transfer.sendFunds(
                    RECIPIENT,
                    BigDecimal.ONE,
                    Convert.Unit.ETHER,
                    GAS_PRICE,
                    GAS_LIMIT
            ).sendAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mTextView.setText(transactionReceipt.get().getTransactionHash());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private String deployContract(Web3j web3j, Credentials transactionManager) throws Exception {
        return Inbox.deploy(web3j, transactionManager, GAS_PRICE, GAS_LIMIT, "Deployment")
                .send()
                .getContractAddress();
    }

    private Inbox loadContract(String contractAddress, Web3j web3j, Credentials credentials) {
        return Inbox.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }
}
