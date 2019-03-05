package com.iot.iotblockchain.activities;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class Inbox extends Contract {
    private static final String BINARY = "6080604052600060015534801561001557600080fd5b506040516104783803806104788339810180604052602081101561003857600080fd5b81019080805164010000000081111561005057600080fd5b8201602081018481111561006357600080fd5b815164010000000081118282018710171561007d57600080fd5b50508051909350610097925060009150602084019061009e565b5050610139565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100df57805160ff191683800117855561010c565b8280016001018555821561010c579182015b8281111561010c5782518255916020019190600101906100f1565b5061011892915061011c565b5090565b61013691905b808211156101185760008155600101610122565b90565b610330806101486000396000f3fe608060405234801561001057600080fd5b5060043610610068577c01000000000000000000000000000000000000000000000000000000006000350463368b8772811461006d57806361bc221a14610115578063ce6d41de1461012f578063e21f37ce14610137575b600080fd5b6101136004803603602081101561008357600080fd5b81019060208101813564010000000081111561009e57600080fd5b8201836020820111156100b057600080fd5b803590602001918460018302840111640100000000831117156100d257600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506101b4945050505050565b005b61011d6101cb565b60408051918252519081900360200190f35b6101136101d1565b61013f6101db565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610179578181015183820152602001610161565b50505050905090810190601f1680156101a65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b80516101c7906000906020840190610269565b5050565b60015481565b6001805481019055565b6000805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102615780601f1061023657610100808354040283529160200191610261565b820191906000526020600020905b81548152906001019060200180831161024457829003601f168201915b505050505081565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102aa57805160ff19168380011785556102d7565b828001600101855582156102d7579182015b828111156102d75782518255916020019190600101906102bc565b506102e39291506102e7565b5090565b61030191905b808211156102e357600081556001016102ed565b9056fea165627a7a72305820c382160a671b51e44df56918b02dd84684fb4c3d80c2646267f2a6e53027c6830029";

    public static final String FUNC_SETMESSAGE = "setMessage";

    public static final String FUNC_COUNTER = "counter";

    public static final String FUNC_GETMESSAGE = "getMessage";

    public static final String FUNC_MESSAGE = "message";

    @Deprecated
    protected Inbox(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Inbox(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Inbox(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Inbox(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setMessage(String newMessage) {
        final Function function = new Function(
                FUNC_SETMESSAGE, 
                Arrays.<Type>asList(new Utf8String(newMessage)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> counter() {
        final Function function = new Function(FUNC_COUNTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> getMessage() {
        final Function function = new Function(
                FUNC_GETMESSAGE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> message() {
        final Function function = new Function(FUNC_MESSAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<Inbox> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String initialMessage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(initialMessage)));
        return deployRemoteCall(Inbox.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Inbox> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String initialMessage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(initialMessage)));
        return deployRemoteCall(Inbox.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Inbox> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String initialMessage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(initialMessage)));
        return deployRemoteCall(Inbox.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }


    @Deprecated
    public static Inbox load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Inbox(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Inbox load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Inbox(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Inbox load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Inbox(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Inbox load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Inbox(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
